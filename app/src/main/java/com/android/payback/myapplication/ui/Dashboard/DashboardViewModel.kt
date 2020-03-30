package com.android.payback.myapplication.ui.Dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.payback.myapplication.model.Loading
import com.android.payback.myapplication.model.Response
import com.android.payback.myapplication.model.Success
import com.android.payback.myapplication.repo.MainRepo
import com.android.payback.myapplication.utils.Cons.Companion.DEFAULT_SEARCH_WORD
import com.android.payback.myapplication.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mainRepo: MainRepo
) :
    BaseViewModel<SearchInterface>() {

    val result = MutableLiveData<Response<Any?>>()

    override fun onViewCreated() {
        super.onViewCreated()
        setTag(DEFAULT_SEARCH_WORD)
    }

    //using Coroutines for handle threads
    fun search(word: String) {
        viewModelScope.launch {
            result.value = Loading(null)
            result.value = withContext(Dispatchers.IO) {
                Success(data = mainRepo.newSearch(word))
            }
        }
    }

    //navigator is WeakReference to ViewLayer
    //it's just a sample to showing how to connect presenter/ViewModel layer to view with WeakReference
    fun setTag(tag: String) {
        navigator?.enterSearchWord(tag)
    }

}