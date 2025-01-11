package com.example.pagging3hw

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagging3hw.RetrofitHelper.ApiInterface
import com.example.pagging3hw.RetrofitHelper.RetrofitInstance
import com.example.pagging3hw.databinding.ActivityMainBinding
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val factory = MainViewModelFactory(RetrofitInstance.provideRetrofitInstance())
//        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        val filmsAdapter = MainAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filmsAdapter
            setHasFixedSize(true)
        }
        binding.recyclerView.adapter = filmsAdapter.withLoadStateFooter(MainLoadStateAdapter())


        lifecycleScope.launch {
            viewModel.data.collectLatest {
                filmsAdapter.submitData(it)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}