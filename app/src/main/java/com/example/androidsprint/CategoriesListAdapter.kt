package com.example.androidsprint

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsprint.databinding.ItemCategoryBinding
import com.example.androidsprint.entity.Category

class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoryBinding.bind(itemView)

        val imageView: ImageView = binding.ivTitleItemCategory
        val titleTextView: TextView = binding.tvTitleItemCategory
        val descriptionTextView: TextView = binding.tvDescriptionItemCategory
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater
            .inflate(R.layout.item_category, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val category = dataSet[position]

        val drawable = try {
            val context = viewHolder.itemView.context
            context.assets.open(category.imageUrl).use { inputStream ->
                Drawable.createFromStream(inputStream, category.imageUrl)
            }
        } catch (e: Exception) {
            Log.i("Id", category.id.toString())
            Log.e("Error", e.stackTraceToString())
            null
        }

        with(viewHolder) {
            titleTextView.text = category.title
            imageView.setImageDrawable(drawable)
            descriptionTextView.text = category.description
        }
    }

    override fun getItemCount() = dataSet.size
}