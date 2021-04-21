package com.manav.allinoneandroidapp.services;

import com.manav.allinoneandroidapp.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    //get photos
    @GET("photos")
    Call<List<PhotoModel>> getAllPhotos();

    //get photos
    @GET("photos/{id}")
    Call<List<PhotoModel>> getOnePhoto(@Path("id") int id);

    //.... TBC

    //example
    /*
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

     */

}
