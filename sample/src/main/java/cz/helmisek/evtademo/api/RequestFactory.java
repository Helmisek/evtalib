package cz.helmisek.evtademo.api;

import java.util.List;

import cz.helmisek.evtademo.entity.UserEntity;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public final class RequestFactory
{
	private static final RequestFactory sInstance = new RequestFactory();
	private Retrofit mRetrofit;


	public static RequestFactory getInstance()
	{
		return sInstance;
	}


	private RequestFactory()
	{
		setup();
	}


	public Observable<Response<List<UserEntity>>> getUsers()
	{
		return prepareObservable(mRetrofit.create(ApiRequest.class).getUsers());
	}


	private void setup()
	{
		mRetrofit = new Retrofit.Builder()
				.baseUrl(ApiConfig.BASE_URL)
				.client(new OkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
	}


	private <T> Observable prepareObservable(Observable<T> observable)
	{
		return observable.asObservable()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io());
	}
}
