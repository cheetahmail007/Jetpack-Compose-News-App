package com.example.mvvmhiltjetpackcompose.model.repository

import com.example.mvvmhiltjetpackcompose.model.remote.News
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun makeAPICall(search: String): Observable<News>
}