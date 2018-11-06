package com.ricky.fliktos.network

import com.ricky.fliktos.model.PhotoFeed
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrServiceAPI {
    @GET("?format=json&nojsoncallback=1")
    fun getPublicPhotoFeed(
        @Query("tags") action: List<String>
    ): Observable<PhotoFeed>

    companion object {
        fun create(): FlickrServiceAPI {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.flickr.com/services/feeds/photos_public.gne/")
                .build()

            return retrofit.create(FlickrServiceAPI::class.java)
        }
    }

}