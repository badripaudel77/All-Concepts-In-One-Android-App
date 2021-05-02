package com.manav.allinoneandroidapp.services;

import com.manav.allinoneandroidapp.model.JSONPostModel;
import com.manav.allinoneandroidapp.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    //get photos
    @GET("photos")
    Call<ArrayList<PhotoModel>> getAllPhotos();

    //get photo details or only one photo
    @GET("photos/{id}")
    Call<ArrayList<PhotoModel>> getOnePhoto(@Path("id") int id);


    //get posts
    @GET("posts")
    Call<ArrayList<JSONPostModel>> getAllPosts();

    //get photo details or only one post
    @GET("posts/{id}")
    Call<ArrayList<JSONPostModel>> getOnePost(@Path("id") int id);


    //.... TBC

    //example
    /*
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

     */

}
