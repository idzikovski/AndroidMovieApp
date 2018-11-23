package com.example.inworks2.movieapp

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService
{
    @GET("movie/top_rated")
        fun getTopMovies(@Query("api_key") action: String):Observable<MovieList>
}