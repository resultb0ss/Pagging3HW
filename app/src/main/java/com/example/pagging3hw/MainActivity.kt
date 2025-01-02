package com.example.pagging3hw

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import com.example.pagging3hw.RetrofitHelper.RetrofitInstance
import com.example.pagging3hw.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var dao: ItemDao
    private val viewModel: MainViewModel by viewModels{ MainViewModelFactory(dao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = ItemDatabase.getInstance(this).itemDao()

        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())


        lifecycleScope.launch{ viewModel.data.collectLatest {
            adapter.submitData(it)
        }}

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}