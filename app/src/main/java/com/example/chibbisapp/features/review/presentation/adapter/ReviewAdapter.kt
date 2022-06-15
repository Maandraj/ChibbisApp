package com.example.chibbisapp.features.review.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.chibbisapp.R
import com.example.chibbisapp.databinding.ItemReviewBinding
import com.example.chibbisapp.features.review.domain.model.Review
import java.text.SimpleDateFormat

class ReviewAdapter(
    private val reviews: List<Review>,
    var reviewOnClickListener: ((review: Review, position: Int) -> Unit)? = null,
) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemReviewBinding by viewBinding(ItemReviewBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviews[position]
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val outputDate = parser.parse(review.dateAdded)?.let { formatter.format(it) }

        with(holder.binding) {
            val context = root.context
            ivReactionReview.backgroundTintList = if (review.isPositive)
                ContextCompat.getColorStateList(context, R.color.color_main)
            else  ContextCompat.getColorStateList(context, R.color.color_positive)
            root.setOnClickListener {
                reviewOnClickListener?.invoke(review, position)
            }
            tvDescriptionReview.text = review.message
            tvTitleReview.text =
                context.getString(R.string.review_title_text, review.userFIO, review.restaurantName)
            tvDateReview.text = outputDate

        }
    }

    override fun getItemCount() = reviews.size
}