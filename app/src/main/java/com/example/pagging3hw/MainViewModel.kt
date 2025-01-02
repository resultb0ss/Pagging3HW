package com.example.pagging3hw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel(
    private val dao: ItemDao
) : ViewModel() {
    val data =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false, initialLoadSize = 20),
            ) {
            MainPagingSource(dao)
        }.flow.cachedIn(viewModelScope)
}

class MainViewModelFactory(
    private val dao: ItemDao
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }
 }