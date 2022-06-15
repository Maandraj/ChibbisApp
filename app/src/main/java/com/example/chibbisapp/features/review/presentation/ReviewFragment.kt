package com.example.chibbisapp.features.review.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.chibbisapp.R
import com.example.chibbisapp.databinding.FragmentReviewBinding
import com.example.chibbisapp.features.restaurants.presentation.RestaurantsVM
import com.example.chibbisapp.features.review.domain.model.Review
import com.example.chibbisapp.features.review.presentation.adapter.ReviewAdapter
import dagger.Lazy
import javax.inject.Inject

class ReviewFragment : Fragment(R.layout.fragment_review) {
    @Inject
    internal lateinit var reviewFactory: Lazy<ReviewVM.Factory>

    private val viewModel: ReviewVM by viewModels {
        reviewFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ReviewComponentViewModel>().reviewComponent.inject(this)
        super.onAttach(context)
    }

    private val binding: FragmentReviewBinding by viewBinding(FragmentReviewBinding::bind)

    private var reviewAdapter: ReviewAdapter? = null
    private val reviewsData = mutableListOf<Review>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
    }

    private fun setUpRecyclerViewClickListener() {
        reviewAdapter?.reviewOnClickListener = { review, position ->
            Toast.makeText(requireContext(),
                "Пользователь ${review.userFIO}, позиция $position",
                Toast.LENGTH_SHORT).show()
        }
    }

    //TODO Сюда бы DiffUtils, но увы времени мало
    @SuppressLint("NotifyDataSetChanged")
    private fun setObservables() {
        viewModel.reviews.observe(viewLifecycleOwner) { reviews ->
            if (reviews == reviewsData)
                return@observe
            reviewsData.clear()
            reviewsData.addAll(reviews)
            binding.includeNotFound.root.visibility =
                if (reviewsData.isNotEmpty()) View.GONE else View.VISIBLE
            if (reviewAdapter == null) {
                reviewAdapter = ReviewAdapter(reviewsData)
                binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
                binding.rvReviews.adapter = reviewAdapter
                setUpRecyclerViewClickListener()
            } else {
                reviewAdapter?.notifyDataSetChanged()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.includeLoading.root.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Log.e(TAG, error.exception.message.toString())
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        reviewAdapter = null
        super.onDestroyView()
    }

}

private const val TAG = "ReviewFragment"