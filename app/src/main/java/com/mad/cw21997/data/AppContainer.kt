package com.mad.cw21997.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mad.cw21997.network.TentApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val tentRepository: TentRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://wiutmadcw.uz/api/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()


    private val retrofitService: TentApiService by lazy {
        retrofit.create(TentApiService::class.java)
    }

    override val tentRepository: TentRepository by lazy {
        NetworkTentRepository(retrofitService)
    }

}