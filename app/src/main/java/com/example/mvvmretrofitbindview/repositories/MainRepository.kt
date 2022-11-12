package com.example.mvvmretrofitbindview.repositories

import com.example.mvvmretrofitbindview.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){

    fun getAllLives() = retrofitService.getallLives()

}