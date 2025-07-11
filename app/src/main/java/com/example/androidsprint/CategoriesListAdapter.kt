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

        val imageView: ImageView = binding.ivTitle
        val titleTextView: TextView = binding.tvTitle
        val descriptionTextView: TextView = binding.tvDescription
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater
            .inflate(R.layout.item_category, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val id = dataSet[position].id
        val title = dataSet[position].title
        val imageUrl = dataSet[position].imageUrl
        val description = dataSet[position].description

        val drawable = try {
            val context = viewHolder.itemView.context
            val inputStream = context.assets.open(imageUrl)
            Drawable.createFromStream(inputStream, imageUrl)
        } catch (e: Exception) {
            Log.i("Id", id.toString())
            Log.e("Error", e.stackTraceToString())
            null
        }

        viewHolder.titleTextView.text = title
        viewHolder.imageView.setImageDrawable(drawable)
        viewHolder.descriptionTextView.text = description
    }

    override fun getItemCount() = dataSet.size
}
