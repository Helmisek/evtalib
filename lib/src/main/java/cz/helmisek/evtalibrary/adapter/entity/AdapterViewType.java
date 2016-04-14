package cz.helmisek.evtalibrary.adapter.entity;

import android.databinding.ViewDataBinding;

import cz.helmisek.evtalibrary.adapter.viewholder.BaseViewHolder;


/**
 * Created by Jirka Helmich on 04.04.16.
 */
public class AdapterViewType
{
	private final int viewTypeId;
	private final int layoutId;
	private final Class<? extends BaseViewHolder> clazz;
	private final Class<? extends ViewDataBinding> bindingClazz;
	private AdapterViewTypeValue mAdapterViewTypeValue;


	public AdapterViewType(int viewTypeId, int layoutId, Class<? extends BaseViewHolder> viewHolderClass, Class<? extends ViewDataBinding> bindingClazz)
	{
		this.viewTypeId = viewTypeId;
		this.layoutId = layoutId;
		this.clazz = viewHolderClass;
		this.bindingClazz = bindingClazz;
	}


	public AdapterViewType(AdapterViewType adapterViewType, AdapterViewTypeValue adapterViewTypeValue)
	{
		this.viewTypeId = adapterViewType.viewTypeId;
		this.layoutId = adapterViewType.layoutId;
		this.clazz = adapterViewType.clazz;
		this.bindingClazz = adapterViewType.bindingClazz;
		this.mAdapterViewTypeValue = adapterViewTypeValue;
	}


	public AdapterViewTypeValue getAdapterViewTypeValue()
	{
		return mAdapterViewTypeValue;
	}


	public void setAdapterViewTypeValue(AdapterViewTypeValue adapterViewTypeValue)
	{
		mAdapterViewTypeValue = adapterViewTypeValue;
	}


	public Class<? extends ViewDataBinding> getBindingClazz()
	{
		return bindingClazz;
	}


	public Class getViewHolderClass()
	{
		return clazz;
	}


	public int getLayoutId()
	{
		return layoutId;
	}


	public int getViewTypeId()
	{
		return viewTypeId;
	}

}