package com.example.mvvmretrofitbindview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitbindview.adapters.MainAdapter
import com.example.mvvmretrofitbindview.databinding.ActivityMainBinding
import com.example.mvvmretrofitbindview.repositories.MainRepository
import com.example.mvvmretrofitbindview.rest.RetrofitService
import com.example.mvvmretrofitbindview.viewmodel.main.MainViewModel
import com.example.mvvmretrofitbindview.viewmodel.main.MainViewModelFactory


class MainActivity : AppCompatActivity() {

//Configuração BindView
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel : MainViewModel
//Configuração Retrofit
    private val retrofitService = RetrofitService.getInstance()
//Configuração RecyclerView
    private val adapter = MainAdapter{
        openLink(it.link)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//ViewBinding \/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//Criando ViewModel usando factory e MainViewModelProvider
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
//Setar Adapter
        binding.recyclerview.adapter = adapter


    }


    override fun onStart() {
        super.onStart()
//Observando lista Livedata para quando requisição posta valor na lista
        viewModel.liveList.observe(this, Observer {lives ->
            Log.i("Matheus", "onStart")
        adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, Observer {message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT)
        })


    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}