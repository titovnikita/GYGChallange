package mykyta.titov.codechallange.ui.features.reviews

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mykyta.titov.codechallange.ui.base.BaseViewModel
import mykyta.titov.codechallange.usecases.GetReviewsUseCase
import javax.inject.Inject


class ReviewsViewModel @Inject constructor(
        private val getReviewsUseCase: GetReviewsUseCase
) : BaseViewModel() {

    private val reviewsUIModel: MutableLiveData<ReviewsUiModel> = MutableLiveData()

    private var hasMore = false

    fun reviewsUiEvents(): LiveData<ReviewsUiModel> = reviewsUIModel

    fun start() {
        fetchReviews()
    }

    private fun fetchReviews(currentPage: Int = DEFAULT_START_PAGE) {
        reviewsUIModel.value = ReviewsUiModel(showProgress = true)

        compositeDisposable.add(
                getReviewsUseCase.getReviews(page = currentPage, count = DEFAULT_PAGE_SIZE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { page ->
                                    this.hasMore = currentPage <= page.totalReviewsCount / DEFAULT_PAGE_SIZE

                                    reviewsUIModel.value = ReviewsUiModel(reviews = page.reviews)
                                },
                                {
                                    reviewsUIModel.value = ReviewsUiModel(showError = true)
                                }
                        )
        )
    }

    fun onNextPage(currentPage: Int) {
        if (hasMore) {
            fetchReviews(currentPage)
        }
    }
}

private const val DEFAULT_START_PAGE = 0
private const val DEFAULT_PAGE_SIZE = 10