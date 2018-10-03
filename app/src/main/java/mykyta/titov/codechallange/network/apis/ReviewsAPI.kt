package mykyta.titov.codechallange.network.apis

import io.reactivex.Single
import mykyta.titov.codechallange.network.dtos.ReviewsPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ReviewsAPI {
    @Headers("User-Agent: GetYourGuide")
    @GET("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json")
    fun getReviews(@Query("count") count: Int, @Query("page") page: Int): Single<Response<ReviewsPageDto>>
}