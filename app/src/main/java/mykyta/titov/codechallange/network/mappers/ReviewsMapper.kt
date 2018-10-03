package mykyta.titov.codechallange.network.mappers

import mykyta.titov.codechallange.domain.Review
import mykyta.titov.codechallange.domain.ReviewsPage
import mykyta.titov.codechallange.network.dtos.ReviewDto
import mykyta.titov.codechallange.network.dtos.ReviewsPageDto


fun map(reviewsPageDto: ReviewsPageDto): ReviewsPage =
        with(reviewsPageDto) {
            ReviewsPage(
                    totalReviewsCount = totalReviewsComments ?: 0,
                    reviews = data?.map { reviewDto -> map(reviewDto) } ?: emptyList()
            )
        }

fun map(reviewDto: ReviewDto): Review =
        with(reviewDto) {
            Review(
                    id = reviewId ?: 0,
                    rating = rating ?: 0.0f,
                    title = title ?: "",
                    message = message ?: "",
                    author = author ?: "",
                    foreignLanguage = foreignLanguage ?: false,
                    date = date ?: "",
                    languageCode = languageCode ?: "",
                    travelerType = travelerType ?: "",
                    reviewerName = reviewerName ?: "",
                    reviewerCountry = reviewerCountry ?: ""
            )
        }
