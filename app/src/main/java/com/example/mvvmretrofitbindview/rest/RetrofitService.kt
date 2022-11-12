package com.example.mvvmretrofitbindview.rest
import com.example.mvvmretrofitbindview.model.Live
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("lista-lives.json")
    fun getallLives(): Call<List<Live>>

    companion object {

        private val  retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://d3c0cr0sze1oo6.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }


        fun getInstance(): RetrofitService{
            return  retrofitService
        }

    }

}