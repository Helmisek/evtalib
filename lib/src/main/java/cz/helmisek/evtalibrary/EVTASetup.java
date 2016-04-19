package cz.helmisek.evtalibrary;

import android.support.v7.widget.RecyclerView;

import cz.helmisek.evtalibrary.adapter.BaseAdapter;
import cz.helmisek.evtalibrary.builder.AdapterDataBuilder;


/**
 * Singleton utility created to manage recycler setup with our builder {@link AdapterDataBuilder}.
 */
public class EVTASetup
{

	private static EVTASetup sInstance;

	private BaseAdapter mAdapter;


	public static EVTASetup getInstance(AdapterDataBuilder builder)
	{
		if(sInstance == null)
			sInstance = new EVTASetup(builder);
		return sInstance;
	}


	public static EVTASetup getInstance()
	{
		if(sInstance == null)
		{
			sInstance = new EVTASetup(null);
		}
		return sInstance;
	}


	private AdapterDataBuilder mAdapterDataBuilder;


	private EVTASetup(AdapterDataBuilder builder)
	{
		this.mAdapterDataBuilder = builder;
	}


	public RecyclerView with(RecyclerView recyclerView)
	{
		this.mAdapter = new BaseAdapter(mAdapterDataBuilder.build());
		recyclerView.setAdapter(mAdapter);
		return recyclerView;
	}


	public RecyclerView with(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager)
	{
		recyclerView.setLayoutManager(layoutManager);
		return with(recyclerView);
	}

	/* ----- ACCESSORS ----- */


	public BaseAdapter getAdapter()
	{
		return mAdapter;
	}

}