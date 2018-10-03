package mykyta.titov.codechallange.domain

data class ReviewsPage(
        val totalReviewsCount: Long,
        val reviews: ReviewList
)

typealias ReviewList = List<Review>

data class Review(
        val id: Long,
        val rating: Float,
        val title: String,
        val message: String,
        val author: String,
        val foreignLanguage: Boolean,
        val date: String,
        val languageCode: String,
        val travelerType: String,
        val reviewerName: String,
        val reviewerCountry: String
)