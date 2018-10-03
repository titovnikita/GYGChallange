package mykyta.titov.codechallange.viewModels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import mykyta.titov.codechallange.domain.ReviewsPage
import mykyta.titov.codechallange.ui.features.reviews.ReviewsViewModel
import mykyta.titov.codechallange.usecases.GetReviewsUseCase
import mykyta.titov.codechallange.utils.RxSchedulersOverride
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class ReviewsViewModelTest {
    private val mockGetReviewsUseCase: GetReviewsUseCase = mock()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var schedulersExecutorRule = RxSchedulersOverride()

    private val reviewsViewModel: ReviewsViewModel by lazy {
        ReviewsViewModel(mockGetReviewsUseCase)
    }

    @Before
    fun before() {
        givenResponse()
    }

    @Test
    fun `Default page is used when fetching first time`() {
        reviewsViewModel.start()

        verify(mockGetReviewsUseCase, times(1)).getReviews(anyInt(), eq(DEFAULT_PAGE))
    }

    @Test
    fun `Next page is used when fetching first time`() {
        reviewsViewModel.start()
        verify(mockGetReviewsUseCase, times(1)).getReviews(anyInt(), eq(DEFAULT_PAGE))

        reviewsViewModel.onNextPage(PAGE)
        verify(mockGetReviewsUseCase, times(1)).getReviews(anyInt(), eq(PAGE))
    }

    private fun givenResponse() {
        whenever(mockGetReviewsUseCase.getReviews(anyInt(), anyInt())).thenReturn(Single.just(ReviewsPage(TOTAL_RESPONSE_COUNT, emptyList())))
    }
}

private const val DEFAULT_PAGE = 0

private const val PAGE = 7

private const val TOTAL_RESPONSE_COUNT = 11L