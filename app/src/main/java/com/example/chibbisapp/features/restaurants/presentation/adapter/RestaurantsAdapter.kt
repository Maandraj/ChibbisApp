package com.example.chibbisapp.features.restaurants.presentation.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.chibbisapp.R
import com.example.chibbisapp.databinding.ItemRestaurantBinding
import com.example.chibbisapp.features.restaurants.domain.model.Restaurant


class RestaurantsAdapter(
    private val restaurants: List<Restaurant>,
    var restaurantOnClick  : ((restaurant : Restaurant, position:Int) -> Unit)? = null
    ) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemRestaurantBinding by viewBinding(ItemRestaurantBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        val specializationList = mutableListOf<String>()
        restaurant.specializations.forEach { spec -> specializationList.add(spec.name) }
        val specialization = specializationList.joinToString(separator = "/")
        with(holder.binding) {
            val context = root.context
            val glide = Glide.with(context)
            root.setOnClickListener {
                restaurantOnClick?.invoke(restaurant, position)
            }
            tvName.text = restaurant.name
            tvPositiveReviews.text =
                context.getString(R.string.procent, restaurant.positiveReviews)
            tvSpecialization.text = specialization
            tvTimeDelivery.text = context.getString(R.string.restaurant_delivery_time, restaurant.deliveryTime)
            tvMinCost.text = context.getString(R.string.price, restaurant.minCost)

            glide.load(restaurant.logo).into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?,
                ) {
                    ivLogo.setImageDrawable(resource)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                override fun onLoadFailed(@Nullable errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    ivLogo.setImageDrawable(ContextCompat.getDrawable(context,
                        R.drawable.ic_knife_fork_126))
                }
            })


        }
    }

    override fun getItemCount() = restaurants.size

}