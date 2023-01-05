package com.example.mvvmhiltjetpackcompose.model.remote

import com.example.mvvmhiltjetpackcompose.model.remote.Constants.API_KEY
import com.example.mvvmhiltjetpackcompose.model.remote.Constants.FILTER_LANGUAGE
import com.example.mvvmhiltjetpackcompose.model.remote.Constants.FILTER_SEARCH
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: $API_KEY")
    @GET("${FILTER_SEARCH}$FILTER_LANGUAGE")
    fun getImageUsingNewsApi(
        @Query("q") search: String,
//        @Query("key") key: String = API_KEY
    ): Observable<News>
}