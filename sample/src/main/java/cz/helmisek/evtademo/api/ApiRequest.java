package cz.helmisek.evtademo.api;

import java.util.List;

import cz.helmisek.evtademo.entity.UserEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;


public interface ApiRequest
{
	@GET(ApiConfig.USERS)
	Observable<Response<List<UserEntity>>> getUsers();
}
