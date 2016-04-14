package cz.helmisek.evtalibrary.builder;

import android.databinding.ViewDataBinding;
import android.databinding.tool.util.L;
import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.helmisek.evtalibrary.adapter.entity.AdapterViewType;
import cz.helmisek.evtalibrary.adapter.entity.AdapterViewTypeValue;
import cz.helmisek.evtalibrary.adapter.viewholder.BaseViewHolder;


/**
 * Created by Jirka Helmich on 04.04.16.
 */
public class AdapterDataBuilder
{


	private List<AdapterViewType> mViewTypeList = new ArrayList<>();
	private List<AdapterViewType> mAdapterViewTypes = new ArrayList<>();


	public AdapterDataBuilder addViewType(int viewTypeId, @LayoutRes int layoutId,
										  Class<? extends BaseViewHolder> viewHolderClazz, Class<? extends ViewDataBinding> bindingClazz)
	{
		mViewTypeList.add(new AdapterViewType(viewTypeId, layoutId, viewHolderClazz, bindingClazz));
		return this;
	}


	public <T extends Object> AdapterDataBuilder addViewTypeItemList(int viewTypeId, List<T> content)
	{
		mAdapterViewTypes.addAll(ViewAdapterTypeItemFactory.addViewTypeItemList(mViewTypeList, viewTypeId, content));
		return this;
	}


	public <T extends Object> AdapterDataBuilder addViewTypeItem(int viewTypeId, T singleContent)
	{

		mAdapterViewTypes.addAll(ViewAdapterTypeItemFactory.addViewTypeItem(mViewTypeList, viewTypeId, singleContent));
		return this;
	}


	public <T extends Object> AdapterDataBuilder addViewTypeItem(int viewTypeId)
	{

		mAdapterViewTypes.addAll(ViewAdapterTypeItemFactory.addViewTypeItem(mViewTypeList, viewTypeId));
		return this;
	}


	public List<AdapterViewType> build()
	{
		return this.mAdapterViewTypes;
	}
}
