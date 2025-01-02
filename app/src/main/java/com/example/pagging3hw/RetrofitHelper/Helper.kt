package com.example.pagging3hw.RetrofitHelper

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pagging3hw.MainActivity
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

fun AppCompatActivity.getFilms(): List<Doc>{
    val films: MutableList<Doc> = mutableListOf()
    lifecycleScope.launch{
        val response = try {
            RetrofitInstance.api.getFilms(limit = 100)
        } catch (e: IOException){
            Toast.makeText(applicationContext,"app error ${e.message}",
                Toast.LENGTH_SHORT).show()
            return@launch
        } catch (e: HttpException) {
            Toast.makeText(applicationContext, "http error ${e.message}",
                Toast.LENGTH_SHORT).show()
            return@launch
        }
        if (response.isSuccessful && response.body() != null){
            withContext(Dispatchers.Main){
                val data = response.body()
                data?.docs?.forEach{films.add(it)}
            }
        }
    }
    return films
}