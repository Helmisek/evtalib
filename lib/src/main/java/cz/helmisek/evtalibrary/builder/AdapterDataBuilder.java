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
 * Data builder class basically allows developer to create custom viewType based list of data using this builder.
 *
 * It is recommended to use enum ordinals as viewTypes.
 */
public class AdapterDataBuilder
{


	private List<AdapterViewType> mViewTypeList = new ArrayList<>();
	private List<AdapterViewType> mAdapterViewTypes = new ArrayList<>();


	/**
	 * Add single viewType item without any provided data.
	 *
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	public AdapterDataBuilder addViewType(int viewTypeId, @LayoutRes int layoutId,
										  Class<? extends BaseViewHolder> viewHolderClazz, Class<? extends ViewDataBinding> bindingClazz)
	{
		mViewTypeList.add(new AdapterViewType(viewTypeId, layoutId, viewHolderClazz, bindingClazz));
		return this;
	}


	/**
	 * Add list of viewType items without provided data entities.
	 *
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 * @param content - List consisting custom type data entities to be provided for adapter
	 */
	public <T extends Object> AdapterDataBuilder addViewTypeItemList(int viewTypeId, List<T> content)
	{
		mAdapterViewTypes.addAll(ViewAdapterTypeItemFactory.addViewTypeItemList(mViewTypeList, viewTypeId, content));
		return this;
	}


	/**
	 * Add single viewType item with custom type provided data entity.
	 *
	 * @param viewTypeId    - ViewType ID to determine which type is going to handle your new item
	 * @param singleContent - Custom type data entity to be provided for this item
	 */
	public <T extends Object> AdapterDataBuilder addViewTypeItem(int viewTypeId, T singleContent)
	{

		mAdapterViewTypes.addAll(ViewAdapterTypeItemFactory.addViewTypeItem(mViewTypeList, viewTypeId, singleContent));
		return this;
	}


	/**
	 * Add single viewType item without any provided data.
	 *
	 * @param viewTypeId - ViewType ID to determine which type is going to handle your new item
	 */
	public <T extends Object> AdapterDataBuilder addViewTypeItem(int viewTypeId)
	{

		mAdapterViewTypes.addAll(ViewAdapterTypeItemFactory.addViewTypeItem(mViewTypeList, viewTypeId));
		return this;
	}


	/**
	 * A build method.
	 *
	 * @return list implementation of type {@link AdapterViewType}.
	 */
	public List<AdapterViewType> build()
	{
		return this.mAdapterViewTypes;
	}
}
