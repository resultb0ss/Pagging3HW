package com.example.pagging3hw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagging3hw.RetrofitHelper.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {
    val data =
        Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20),
        ) {
            MainPagingSource(repository)
        }.flow.cachedIn(viewModelScope)
}

