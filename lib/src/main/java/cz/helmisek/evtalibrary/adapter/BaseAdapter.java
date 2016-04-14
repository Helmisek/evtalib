package cz.helmisek.evtalibrary.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cz.helmisek.evtalibrary.EVTASetup;
import cz.helmisek.evtalibrary.adapter.entity.AdapterViewType;
import cz.helmisek.evtalibrary.adapter.entity.AdapterViewTypeValue;
import cz.helmisek.evtalibrary.adapter.viewholder.BaseViewHolder;
import cz.helmisek.evtalibrary.builder.AdapterDataBuilder;
import cz.helmisek.evtalibrary.builder.ViewAdapterTypeItemFactory;


/**
 * Created by Jirka Helmich on 04.04.16.
 */
public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterCRUDBehavior
{

	private final List<AdapterViewType> mViewTypes;
	private RecyclerView mRecyclerView;


	public BaseAdapter(List<AdapterViewType> viewTypes)
	{
		mViewTypes = viewTypes;
	}


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());

		for(int i = 0; i < mViewTypes.size(); i++)
		{
			AdapterViewType adapterViewType = mViewTypes.get(i);
			if(viewType == getViewTypeId(adapterViewType))
			{
				AdapterViewTypeValue viewTypeValue = getAdapterViewTypeValue(adapterViewType);
				int layoutId = adapterViewType.getLayoutId();
				final ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);

				viewTypeValue.setViewDataBinding(binding);
				viewTypeValue.setViewHolder(createViewHolder(adapterViewType.getViewHolderClass(), adapterViewType.getBindingClazz(), binding));
				viewTypeValue.getViewHolder().setIsRecyclable(false);

				adapterViewType.setAdapterViewTypeValue(viewTypeValue);

				mViewTypes.set(i, adapterViewType);

				return viewTypeValue.getViewHolder();
			}
		}
		throw new RuntimeException("There is no view type that matches view type " + viewType + " provided");
	}


	private BaseViewHolder createViewHolder(Class viewHolderClazz, Class bindingClazz, ViewDataBinding binding)
	{
		BaseViewHolder baseViewHolder = null;
		try
		{
			baseViewHolder = (BaseViewHolder) viewHolderClazz.getConstructor(View.class, Class.forName(bindingClazz.getName())).newInstance(binding.getRoot(), binding);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return baseViewHolder;
	}


	private int getViewTypeId(AdapterViewType type)
	{
		return type.getViewTypeId();
	}


	@Override
	public long getItemId(int position)
	{
		return mViewTypes.get(position).getViewTypeId();
	}


	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
	{
		for(AdapterViewType type : mViewTypes)
		{
			if(getItemId(position) == holder.getItemViewType())
			{
				if(type.getAdapterViewTypeValue().getViewHolder() != null)
				{
					type.getAdapterViewTypeValue().getViewHolder().bindData(mViewTypes.get(position).getAdapterViewTypeValue().getDataEntity(), position);
				}
			}
		}
	}


	@Override
	public int getItemViewType(int position)
	{
		return mViewTypes.get(position).getViewTypeId();
	}


	@Override
	public int getItemCount()
	{
		return mViewTypes.size();
	}


	/* ---- CRUD implementation START ---- */


	@Override
	public <T> void create(T entity, int viewTypeId, boolean shouldSmoothScroll)
	{
		if(!mViewTypes.isEmpty())
		{
			boolean viewTypeFound = false;
			for(AdapterViewType viewType : mViewTypes)
			{
				if(viewType.getViewTypeId() == viewTypeId)
				{
					viewTypeFound = true;
					break;
				}
			}
			if(!viewTypeFound)
			{
				throw new RuntimeException("No such viewTypeId as " + viewTypeId + " was found");
			}
		}

		final List<AdapterViewType> viewTypes = ViewAdapterTypeItemFactory.addViewTypeItem(mViewTypes, viewTypeId, entity);

		if(viewTypes.size() == 1)
		{
			mViewTypes.add(viewTypes.get(0));
			notifyDataSetChanged();

		}
		else
		{
			int sizeNow = mViewTypes.size();
			mViewTypes.addAll(viewTypes);
			notifyDataSetChanged();
		}
		if(getItemCount() != 0 && shouldSmoothScroll)
			mRecyclerView.smoothScrollToPosition(getItemCount() - 1);
	}


	@Override
	public <T> void create(T entity, int viewTypeId)
	{
		create(entity, viewTypeId, true);
	}


	@Override
	public <T> T read(int position, int viewTypeId)
	{
		if(isPositionValid(position))
		{
			ListCursorHelper helper = getCursorHelperForPositionById(position, viewTypeId);
			return (T) mViewTypes.get(helper.getPosition()).getAdapterViewTypeValue().getDataEntity();
		}
		throw new RuntimeException("Provided position is not valid");
	}


	@Override
	public <T> void update(T entity, int position, int viewTypeId)
	{
		if(isPositionValid(position))
		{
			if(entity != null)
			{
				ListCursorHelper cursorHelper = getCursorHelperForPositionById(position, viewTypeId);

				AdapterViewType type = cursorHelper.getAdapterViewType();
				type.getAdapterViewTypeValue().setDataEntity(entity);
				mViewTypes.set(cursorHelper.getPosition(), type);

				notifyDataSetChanged();
			}
		}
		else
			throw new RuntimeException("Provided position is not valid");
	}


	@Override
	public <T> void update(int position, int viewTypeId)
	{
		if(isPositionValid(position))
		{
			notifyDataSetChanged();
		}
		else
			throw new RuntimeException("Provided position is not valid");
	}


	@Override
	public void delete(int position, int viewTypeId)
	{
		if(isPositionValid(position))
		{
			ListCursorHelper helper = getCursorHelperForPositionById(position, viewTypeId);
			mViewTypes.remove(helper.getPosition());

			notifyDataSetChanged();
		}
		else
			throw new RuntimeException("Provided position is not valid");
	}


	private boolean isPositionValid(int position)
	{
		return (position < mViewTypes.size() && position >= 0);
	}


	private ListCursorHelper getCursorHelperForPositionById(int position, int viewTypeId)
	{
		List<ListCursorHelper> helpers = new ArrayList<>();
		for(int i = 0; i < mViewTypes.size(); i++)
		{
			if(viewTypeId == mViewTypes.get(i).getViewTypeId())
			{
				helpers.add(new ListCursorHelper(mViewTypes.get(i), i));
			}
		}
		return helpers.get(position);
	}

	/* ---- CRUD implementation END ---- */


	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView)
	{
		super.onAttachedToRecyclerView(recyclerView);
		this.mRecyclerView = recyclerView;
	}

	/* ---- ACCESSORS implementation ---- */


	private AdapterViewTypeValue getAdapterViewTypeValue(AdapterViewType adapterViewType)
	{
		return adapterViewType.getAdapterViewTypeValue();
	}


	/* ---- Helper class implementation ---- */


	private static class ListCursorHelper
	{
		private AdapterViewType mAdapterViewType;
		private int position;


		public ListCursorHelper(AdapterViewType adapterViewType, int position)
		{
			this.mAdapterViewType = adapterViewType;
			this.position = position;
		}


		public AdapterViewType getAdapterViewType()
		{
			return mAdapterViewType;
		}


		public int getPosition()
		{
			return position;
		}
	}

}
