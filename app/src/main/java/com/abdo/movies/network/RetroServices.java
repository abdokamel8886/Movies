package com.abdo.movies.network;

import com.abdo.movies.models.MovieDetailsModel;
import com.abdo.movies.models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroServices {


    @GET("discover/movie")
    Call<MovieModel> getAllMovies(@Query("api_key") String api_key);


    @GET("movie/{movie_id}")
    Call<MovieDetailsModel> getMovieDetails(@Path("movie_id") int MovieId , @Query("api_key") String api_key);


}
