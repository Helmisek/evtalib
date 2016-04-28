package cz.helmisek.evtademo.adapter.viewholder;

import android.view.View;

import cz.helmisek.evtademo.adapter.viewmodel.UserItemViewModel;
import cz.helmisek.evtademo.databinding.UserItemBinding;
import cz.helmisek.evtademo.entity.UserEntity;
import cz.helmisek.evtalibrary.adapter.viewholder.BaseViewHolder;


public class UserItemViewHolder extends BaseViewHolder<UserEntity, UserItemBinding>
{

	public UserItemViewHolder(View itemView, UserItemBinding binding)
	{
		super(itemView, binding);
	}


	@Override
	public void bindData(UserEntity entity, int position)
	{
		getBinding().setItemViewModel(new UserItemViewModel(entity, getBinding(), position));
		getBinding().executePendingBindings();
	}
}
