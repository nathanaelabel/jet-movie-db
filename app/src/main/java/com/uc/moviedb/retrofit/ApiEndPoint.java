package com.uc.moviedb.retrofit;

import com.uc.moviedb.model.Credits;
import com.uc.moviedb.model.Genre;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.model.NowPlaying;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("genre/movie/list")
    Call<Genre> getGenres(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<NowPlaying> getUpComing(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/credits")
    Call<Credits> getCredits(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

}