package cz.helmisek.evtademo.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import cz.helmisek.evtademo.R;
import cz.helmisek.evtademo.adapter.ViewType;
import cz.helmisek.evtademo.adapter.viewholder.UserItemViewHolder;
import cz.helmisek.evtademo.api.RequestFactory;
import cz.helmisek.evtademo.api.SimpleSubscriber;
import cz.helmisek.evtademo.databinding.ActivityMainBinding;
import cz.helmisek.evtademo.databinding.UserItemBinding;
import cz.helmisek.evtademo.entity.UserEntity;
import cz.helmisek.evtalibrary.EVTASetup;
import cz.helmisek.evtalibrary.builder.AdapterDataBuilder;
import cz.kinst.jakub.viewmodelbinding.ViewModel;
import retrofit2.Response;


public class MainActivityViewModel extends ViewModel<ActivityMainBinding>
{
	@Override
	public void onViewModelCreated()
	{
		super.onViewModelCreated();

		setupRecycler();
	}


	private void setupRecycler()
	{
		RequestFactory.getInstance().getUsers().subscribe(new SimpleSubscriber<Response<List<UserEntity>>>()
		{
			@Override
			public void onNext(Response<List<UserEntity>> listResponse)
			{
				EVTASetup.getInstance(createAdapterDataBuilder(listResponse.body()))
						.with(getBinding().recycler, new LinearLayoutManager(getContext()));
				getBinding().fab.show();
			}
		});

	}


	private AdapterDataBuilder createAdapterDataBuilder(List<UserEntity> userEntities)
	{
		AdapterDataBuilder builder = new AdapterDataBuilder();

		builder.addViewType(ViewType.USER.ordinal(), R.layout.user_item, UserItemViewHolder.class, UserItemBinding.class);

		builder.addViewTypeItemList(ViewType.USER.ordinal(), userEntities);

		return builder;
	}


	public void onFabClick(View v)
	{
		EVTASetup.getInstance().getAdapter().create(new UserEntity(0, "Jirka", "jirka", "email"), ViewType.USER.ordinal());
	}
}
