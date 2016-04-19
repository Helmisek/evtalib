package cz.helmisek.evtalibrary.viewmodel;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;


import cz.kinst.jakub.viewmodelbinding.ViewModel;


/**
 * Base view model class which handles data entity object and also items data binding.
 * Every item's viewmodel should extend this viewmodel.
 */
public abstract class BaseAdapterViewModel<O extends Object, X extends ViewDataBinding> extends ViewModel<X>
{

	/**
	 * Basically call for click on layout's root view.
	 */
	public abstract void onItemClick(View v);


	private O mObject;
	private X mBinding;
	private int mPosition;


	public BaseAdapterViewModel(O object, X binding, int position)
	{
		this.mObject = object;
		this.mBinding = binding;
		this.mPosition = position;

		binding.getRoot().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onItemClick(v);
			}
		});
	}


	@Override
	public X getBinding()
	{
		return mBinding;
	}


	@Override
	public Context getContext()
	{
		return getBinding().getRoot().getContext();
	}


	public O getDataObject()
	{
		return mObject;
	}


	public int getPosition()
	{
		return mPosition;
	}
}
