package mykyta.titov.codechallange.ui.features.reviews

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_reviews.*
import mykyta.titov.codechallange.R
import mykyta.titov.codechallange.ui.base.BaseActivity
import mykyta.titov.codechallange.utils.extensions.toast
import mykyta.titov.codechallange.utils.views.PaginationScrollListener

class ReviewsActivity : BaseActivity<ReviewsViewModel>() {

    private val feedAdapter by lazy { ReviewsAdapter() }

    private val paginationScrollListener: PaginationScrollListener by lazy {
        PaginationScrollListener { page , _, _ ->
            viewModel.onNextPage(page)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_reviews

    override fun injectViewModel(): ReviewsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ReviewsViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.reviewsUiEvents().observe(this, Observer { uiModel ->
            uiModel?.apply {
                if (showError) {
                    toast("Failed to retrieve reviews!")
                }

                if (reviews.isNotEmpty()) {
                    feedAdapter.add(reviews)
                }

                pbLoadMore.visibility = if (showProgress) VISIBLE else GONE
            }
        })
        viewModel.start()
    }

    override fun initViews() {
        rvReviews.apply {
            paginationScrollListener.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = paginationScrollListener.layoutManager
            adapter = feedAdapter
            addOnScrollListener(paginationScrollListener)
        }
    }
}
