package com.example.pagging3hw

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import kotlinx.coroutines.delay
import java.lang.Exception

class MainPagingSource(
    private val dao: ItemDao
): PagingSource<Int, Doc>() {

    override fun getRefreshKey(state: PagingState<Int, Doc>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.plus(1)?:anchorPage?.nextKey?.minus(1)
        } as Int?
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Doc> {
        val page = params.key?: 0
        return try {
            val entities = dao.getPagedList(params.loadSize, page * params.loadSize)
            if (page != 0) delay(1000)
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1,
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}