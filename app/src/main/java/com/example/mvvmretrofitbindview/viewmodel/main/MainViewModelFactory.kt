package com.example.mvvmretrofitbindview.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitbindview.repositories.MainRepository

class MainViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T

        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }

}


//Esse código é sempre utilizado quando a ViewModel tem um construtor com argumentos