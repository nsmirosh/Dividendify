package com.example.dividendify.data

import com.example.dividendify.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


const val TOKEN = "token"
const val CONNECT_TIME_OUT_SECONDS = 30L
const val READ_TIME_OUT_SECONDS = 30L


abstract class BaseRepository {


    fun buildRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                var request: Request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter(TOKEN, BuildConfig.API_KEY)
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
            .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}