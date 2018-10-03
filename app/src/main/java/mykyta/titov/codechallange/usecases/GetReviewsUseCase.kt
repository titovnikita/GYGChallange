package mykyta.titov.codechallange.usecases

import io.reactivex.Single
import mykyta.titov.codechallange.domain.ReviewsPage
import mykyta.titov.codechallange.network.ReviewsRepository
import mykyta.titov.codechallange.network.mappers.map
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
        private var reviewsRepository: ReviewsRepository
) {
    fun getReviews(count: Int, page: Int): Single<ReviewsPage> {
        return reviewsRepository.getReviews(count, page)
                .flatMap { response ->
                    with(response.body()) {
                        when (this) {
                            null -> Single.error(IllegalStateException("Failed to retrieve reviews!"))
                            else -> when (status) {
                                null -> Single.error(IllegalStateException("Error while retrieving reviews, status null!"))
                                false -> Single.error(IllegalStateException("Error while retrieving reviews, status false!"))
                                true -> Single.just(map(this))
                            }
                        }
                    }
                }
    }
}