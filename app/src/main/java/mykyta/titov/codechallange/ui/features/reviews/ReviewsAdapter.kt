package mykyta.titov.codechallange.ui.features.reviews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_review.view.*
import mykyta.titov.codechallange.R
import mykyta.titov.codechallange.domain.Review
import mykyta.titov.codechallange.domain.ReviewList

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ReviewItemViewHolder>() {

    private val data: MutableList<Review> = mutableListOf()

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
                    .run { ReviewItemViewHolder(this) }

    fun add(reviews: ReviewList) {
        data.addAll(reviews)
        notifyDataSetChanged()
    }

    class ReviewItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Review) {
            with(itemView) {
                item.apply {
                    tvTitle.text = title
                    tvMessage.text = message
                    tvAuthor.text = author
                    rbRating.rating = rating
                }
            }
        }
    }
}