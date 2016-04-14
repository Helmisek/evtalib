package cz.helmisek.evtalibrary.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Jirka Helmich on 04.04.16.
 */
public abstract class BaseViewHolder<T extends Object, VB extends ViewDataBinding> extends RecyclerView.ViewHolder
{

	public abstract void bindData(T entity, int position);


	private VB mBinding;


	public BaseViewHolder(View itemView, VB binding)
	{
		super(itemView);

		this.mBinding = binding;
	}


	public VB getBinding()
	{
		return mBinding;
	}


}
