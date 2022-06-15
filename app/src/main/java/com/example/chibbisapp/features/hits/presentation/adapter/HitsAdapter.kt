package com.example.chibbisapp.features.hits.presentation.adapter


import android.annotation.SuppressLint
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
import com.example.chibbisapp.databinding.ItemHitBinding
import com.example.chibbisapp.features.hits.domain.model.Hit
import com.example.chibbisapp.utils.capitalized


class HitsAdapter(
    private val hits: List<Hit>,
    var hitOnClickListener: ((hit: Hit, position: Int) -> Unit)? = null,
) :
    RecyclerView.Adapter<HitsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemHitBinding by viewBinding(ItemHitBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hit, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hit = hits[position]
        hit.productDescription
        with(holder.binding) {
            val context = root.context
            val glide = Glide.with(context)
            tvIngredients.text ="${context.getString(R.string.ingredients_text_hit_item)}\n${hit.productDescription.replace(", ","\n").replace(". ", "\n").capitalized() }"
            tvPrice.text = context.getString(R.string.price, hit.productPrice)
            tvRestaurantName.text = hit.restaurantName
            tvProductName.text = hit.productName
            root.setOnClickListener {
                hitOnClickListener?.invoke(hit, position)
            }
            glide.load(hit.productImage).into(object : CustomTarget<Drawable?>() {
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
                        R.drawable.ic_cookie_24))
                }
            })

        }
    }

    override fun getItemCount() = hits.size

}