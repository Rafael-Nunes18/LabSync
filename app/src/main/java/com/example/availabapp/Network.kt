package com.example.available

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

data class Laboratorio(
    val id: Int,
    val nome: String,
    val status: String,
    val computadores: Int,
    val projetor: String,
    val tomadas: String,
    val arCondicionado: String,
    val lousa: String
)

data class ReservaRequest(
    val labId: Int,
    val data: String
)

interface AvaiLabApiService {
    @GET("lab")
    suspend fun getLaboratorios(): List<Laboratorio>

    @GET("lab/{id}")
    suspend fun getLaboratorioDetalhes(@Path("id") id: Int): Laboratorio

    @POST("res")
    suspend fun cadastrarReserva(@Body request: ReservaRequest): retrofit2.Response<Unit>
}

object RetrofitClient {
    // ATENÇÃO: Se o seu Node.js rodar em outra porta (ex: 5000), mude o 3000 ali no final
    private const val BASE_URL = "http://10.0.2.2:3000/"

    val apiService: AvaiLabApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AvaiLabApiService::class.java)
    }
}