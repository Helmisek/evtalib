package cz.helmisek.evtalibrary.adapter.entity;

import android.databinding.ViewDataBinding;

import cz.helmisek.evtalibrary.adapter.viewholder.BaseViewHolder;


/**
 * Created by Jirka Helmich on 04.04.16.
 */
public class AdapterViewTypeValue<T extends Object>
{

	private BaseViewHolder mBaseViewHolder;
	private ViewDataBinding mViewDataBinding;


	private T mObject;


	public AdapterViewTypeValue(T object)
	{
		this.mObject = object;
	}


	public T getDataEntity()
	{
		return mObject;
	}


	public void setDataEntity(T object)
	{
		mObject = object;
	}


	public ViewDataBinding getViewDataBinding()
	{
		return mViewDataBinding;
	}


	public void setViewDataBinding(ViewDataBinding viewDataBinding)
	{
		mViewDataBinding = viewDataBinding;
	}


	public BaseViewHolder getViewHolder()
	{
		return mBaseViewHolder;
	}


	public void setViewHolder(BaseViewHolder viewHolder)
	{
		mBaseViewHolder = viewHolder;
	}


	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		AdapterViewTypeValue<?> that = (AdapterViewTypeValue<?>) o;

		return mObject != null ? mObject.equals(that.mObject) : that.mObject == null;

	}


	@Override
	public int hashCode()
	{
		return mObject != null ? mObject.hashCode() : 0;
	}
}
