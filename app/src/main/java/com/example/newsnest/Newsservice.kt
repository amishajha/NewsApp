package com.example.newsnest
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



const val Base_URL="https://newsapi.org/"
const val API_KEY="610aa56640124effb62074d59d089698"
interface NewsInterface{

    @GET("v2/top-headlines?apikey=$API_KEY")
    fun getHeadline(@Query("country")country:String,@Query("page")page:Int):Call<News>

}
object newservice{
    val newsInstance:NewsInterface
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}