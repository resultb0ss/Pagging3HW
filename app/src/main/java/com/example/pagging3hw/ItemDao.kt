package com.example.pagging3hw

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc

@Dao
interface ItemDao {
    @Query("SELECT * FROM doc ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<Doc>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Doc): Long
}