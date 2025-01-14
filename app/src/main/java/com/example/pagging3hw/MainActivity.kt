package com.example.pagging3hw

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagging3hw.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmsAdapter = MainAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filmsAdapter.withLoadStateFooter(MainLoadStateAdapter())
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            Log.d("@@@", "Передаем данные в адаптер для отображения")
            viewModel.data.collectLatest {
                Log.d("@@@", "Передали данные в адаптер для отображения")
                filmsAdapter.submitData(it)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}