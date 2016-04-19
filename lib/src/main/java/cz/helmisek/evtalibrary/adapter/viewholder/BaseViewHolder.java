package cz.helmisek.evtalibrary.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Jirka Helmich on 04.04.16.
 * <p/>
 * Abstract view holder class which describes how any new view holders should look like.
 * Every new view holder which extends this class should implement entity for view holder to hold.
 * Also it is required to provide class type which extends ViewDataBinding for correct behavior.
 */
public abstract class BaseViewHolder<T extends Object, VB extends ViewDataBinding> extends RecyclerView.ViewHolder
{

	/**
	 * Method which describes how to handle data change for specific view
	 * and also it is required for this method to implement setting of viewmodel.
	 */
	public abstract void bindData(T entity, int position);


	private VB mBinding;


	public BaseViewHolder(View itemView, VB binding)
	{
		super(itemView);

		this.mBinding = binding;
	}


	/***
	 * Returns specific dataBinding model.
	 */
	public VB getBinding()
	{
		return mBinding;
	}


}
