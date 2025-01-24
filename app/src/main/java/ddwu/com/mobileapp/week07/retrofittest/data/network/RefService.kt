package ddwu.com.mobileapp.week07.retrofittest.data.network

import android.content.Context
import android.util.Log
import ddwu.com.mobileapp.week07.retrofittest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RefService(val context: Context) {
    // test
    val TAG = "RefService"
    val movieService: IBoxOfficeService

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.kobis_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieService = retrofit.create(IBoxOfficeService::class.java)
    }

    suspend fun getMovies(key: String, date: String)  : List<Movie>?   {
//        val movieCallback = object : Callback<Root> {
//            override fun onResponse(call: Call<Root>, response: Response<Root>) {
//                if (response.isSuccessful) {
//                    val boxOfficeRoot = response.body()
//                    val movies = boxOfficeRoot?.movieResult?.movies
//                    movies?.forEach { movie ->
//                        Log.d(TAG, movie.toString())
//                    }
//                }
//            }
//            override fun onFailure(call: Call<   >, t: Throwable) {
//                Log.d(TAG, t.stackTraceToString())
//            }
//        }
//
//        val movieCall : Call <Root> = movieService.getDailyBoxOfficeList("json", key, date)/* IBoxOfficeService 의 함수 호출 */
//        movieCall.enqueue(movieCallback)    // val response = movieCall.execute()\

        val root : Root = movieService.getDailyBoxOffice("json", key, date)

//        return null // response.body()?.boxOfficeResult?.boxOfficeList
        return root.movieResult.movies
    }

}