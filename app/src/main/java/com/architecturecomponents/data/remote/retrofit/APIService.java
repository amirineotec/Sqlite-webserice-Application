package com.architecturecomponents.data.remote.retrofit;

import com.architecturecomponents.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("/v2/{id}")
    Call<List<Movie>> getMovies(@Path("id") String id);
}
