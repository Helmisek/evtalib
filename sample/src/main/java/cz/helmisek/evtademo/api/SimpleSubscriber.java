package cz.helmisek.evtademo.api;

import rx.Subscriber;


public abstract class SimpleSubscriber<T extends Object> extends Subscriber<T>
{
	@Override
	public void onCompleted()
	{
	}


	@Override
	public void onError(Throwable e)
	{
	}

}
