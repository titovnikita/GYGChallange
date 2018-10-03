package mykyta.titov.codechallange.ui.features.reviews

import mykyta.titov.codechallange.domain.ReviewList

data class ReviewsUiModel(
        val showProgress: Boolean = false,
        val showError: Boolean = false,
        val reviews: ReviewList = emptyList()
)