package com.example.pagging3hw

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagging3hw.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var filmsAdapter: MainAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = filmsAdapter
            setHasFixedSize(true)
        }
        binding.recyclerView.adapter = filmsAdapter.withLoadStateFooter(MainLoadStateAdapter())


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