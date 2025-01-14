package com.example.pagging3hw


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.example.pagging3hw.RetrofitHelper.ApiRepository
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import jakarta.inject.Inject
import jakarta.inject.Singleton
import retrofit2.HttpException

@Singleton
class MainPagingSource @Inject constructor(

    private val repository: ApiRepository,

    ) : PagingSource<Int, Doc>() {

    override fun getRefreshKey(state: PagingState<Int, Doc>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } as Int?
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doc> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        return try {
            val response = repository.getFilms(pageSize, page)
            if (response.isSuccessful) {
                val films = checkNotNull(response.body()).docs
                val nextKey = if (films.size < pageSize) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                return LoadResult.Page(films, prevKey, nextKey)

            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }
}