package com.example.mvvmhiltjetpackcompose.model.repository

import com.example.mvvmhiltjetpackcompose.model.remote.ApiService
import com.example.mvvmhiltjetpackcompose.model.remote.News
import io.reactivex.rxjava3.core.Observable

class RepositoryImplementation(private val apiService: ApiService) : Repository {

    override fun makeAPICall(search: String): Observable<News> {
        return apiService.getImageUsingNewsApi(search)
    }
}