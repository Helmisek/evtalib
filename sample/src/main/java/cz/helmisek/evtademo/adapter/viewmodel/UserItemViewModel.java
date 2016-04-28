package cz.helmisek.evtademo.adapter.viewmodel;

import android.view.View;

import cz.helmisek.evtademo.databinding.UserItemBinding;
import cz.helmisek.evtademo.entity.UserEntity;
import cz.helmisek.evtalibrary.EVTASetup;
import cz.helmisek.evtalibrary.viewmodel.BaseAdapterViewModel;


public class UserItemViewModel extends BaseAdapterViewModel<UserEntity, UserItemBinding> implements View.OnLongClickListener
{
	public UserItemViewModel(UserEntity entity, UserItemBinding binding, int position)
	{
		super(entity, binding, position);

		getBinding().getRoot().setOnLongClickListener(this);

		getBinding().userItemTitle.setText(entity.getName());
	}


	@Override
	public void onItemClick(View v)
	{
		((UserEntity) EVTASetup.getInstance().getAdapter().read(getPosition())).setName("Updated");
		EVTASetup.getInstance().getAdapter().update(getPosition());
	}


	@Override
	public boolean onLongClick(View v)
	{
		EVTASetup.getInstance().getAdapter().delete(getPosition());
		return true;
	}
}
