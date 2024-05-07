package cr.ac.una.controlfinancierocamera.service
import com.google.gson.GsonBuilder
import cr.ac.una.controlfinanciero.AuthInterceptor

import cr.ac.una.jsoncrud.dao.MovimientoDAO
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovimientoService {
  val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor("2F2RgKvgROqpqctLwJ-KYoNB_wNOHG4i1Sk9NZv9qrAt4fGNOg"))
        .build()

    val gson = GsonBuilder().setPrettyPrinting().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://crudapi.co.uk/api/v1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService = retrofit.create(MovimientoDAO::class.java)

}