package com.example.pagging3hw

import android.content.Context
import android.util.Log
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import com.example.pagging3hw.RetrofitHelper.getFilms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Doc::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun itemDao():ItemDao

    companion object {
        private var INSTANCE: ItemDatabase? = null
        fun getInstance(context: Context): ItemDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    "doc_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        INSTANCE?.let {
                            database -> CoroutineScope(Dispatchers.IO).launch{
                                val films = MainActivity().getFilms()
                            Log.d("@@@","FILMS DATABASE $films")
                            films.forEach {
                                database.itemDao().insert(it)
                            }
                        }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }

}