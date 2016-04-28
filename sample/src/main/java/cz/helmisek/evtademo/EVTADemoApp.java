package cz.helmisek.evtademo;

import android.app.Application;
import android.content.Context;


public class EVTADemoApp extends Application
{

	public EVTADemoApp()
	{
		synchronized(this)
		{
			new App(this);
		}
	}


	public static class App
	{
		private Context mContext;


		public App(Context context)
		{
			mContext = context;
		}


		public Context getContext()
		{
			return mContext;
		}
	}

}
