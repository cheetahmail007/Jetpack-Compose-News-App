package com.example.mvvmhiltjetpackcompose.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmhiltjetpackcompose.model.remote.News
import com.example.mvvmhiltjetpackcompose.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val result = MutableLiveData<News>()
    val error = MutableLiveData<String>()

    fun searchForImage(search: String) {
        repository.makeAPICall(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    result.postValue(it)
                },
                {
                    error(Log.i("tag", it.toString()))
                }
            )
    }
}