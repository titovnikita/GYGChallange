package mykyta.titov.codechallange.network.dtos

import com.google.gson.annotations.SerializedName

data class ReviewsPageDto(
        @SerializedName("status") val status: Boolean? = null,
        @SerializedName("total_reviews_comments") val totalReviewsComments: Long? = null,
        @SerializedName("data") val data: ReviewDtoList? = null
)

typealias ReviewDtoList = List<ReviewDto>

data class ReviewDto(
        @SerializedName("review_id") val reviewId: Long? = null,
        @SerializedName("rating") val rating: Float? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("message") val message: String? = null,
        @SerializedName("author") val author: String? = null,
        @SerializedName("foreignLanguage") val foreignLanguage: Boolean? = null,
        @SerializedName("date") val date: String? = null,
        @SerializedName("languageCode") val languageCode: String? = null,
        @SerializedName("traveler_type") val travelerType: String? = null,
        @SerializedName("reviewerName") val reviewerName: String? = null,
        @SerializedName("reviewerCountry") val reviewerCountry: String? = null
)