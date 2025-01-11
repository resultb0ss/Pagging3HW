package com.example.pagging3hw


import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.example.pagging3hw.RetrofitHelper.ApiInterface
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import retrofit2.HttpException

class MainPagingSource(

    private val apiInterface: ApiInterface,

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

        val response = apiInterface.getFilms(pageSize, page)
        if (response.isSuccessful) {
            val films = checkNotNull(response.body()).docs
            val nextKey = if (films.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(films, prevKey, nextKey)

        } else {
            return LoadResult.Error(HttpException(response))
        }

    }
}