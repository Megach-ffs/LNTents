package com.mad.cw21997.network

import okhttp3.MediaType
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mad.cw21997.data.Tent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TentApiService {

    @GET("records/all?student_id=21997_I_love_snake_case")
    suspend fun getPhotos(): TentResponse

    @POST("records?student_id=21997_I_love_snake_case")
    suspend fun postTent(@Body tentData: TentData)

    @PUT("records/{id}?student_id=21997_I_love_snake_case")
    suspend fun updateTent(
        @Body tentData: TentData,
        @Path("id") id: Int
    )

    @DELETE("records/{id}?student_id=21997_I_love_snake_case")
    suspend fun deleteTent(
        @Path("id") id: Int
    )



}
