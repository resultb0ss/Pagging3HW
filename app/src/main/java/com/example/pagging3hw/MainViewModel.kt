package com.example.pagging3hw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagging3hw.RetrofitHelper.ApiInterface
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : ViewModel() {
    val data =
        Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20),
        ) {
            MainPagingSource(apiInterface)
        }.flow.cachedIn(viewModelScope)
}

