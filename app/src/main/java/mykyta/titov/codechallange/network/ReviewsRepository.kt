package mykyta.titov.codechallange.network

import io.reactivex.Single
import mykyta.titov.codechallange.network.apis.ReviewsAPI
import mykyta.titov.codechallange.network.base.BaseRemoteDataSource
import mykyta.titov.codechallange.network.dtos.ReviewsPageDto
import retrofit2.Response
import javax.inject.Inject

class ReviewsRepository @Inject constructor(private val remoteDataSource: Remote) {

    fun getReviews(count: Int, page: Int) = remoteDataSource.getReviews(count, page)
}

class Remote @Inject constructor() : BaseRemoteDataSource() {

    fun getReviews(count: Int, page: Int): Single<Response<ReviewsPageDto>> =
            with(createService(ReviewsAPI::class.java)) { getReviews(count, page) }
}