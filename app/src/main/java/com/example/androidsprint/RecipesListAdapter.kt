package com.example.androidsprint

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsprint.databinding.ItemRecipeBinding
import com.example.androidsprint.entity.Recipe

class RecipesListAdapter(private val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    interface OnItemRecipeClickListener {
        fun onItemClick(recipeId: Int)
    }

    private var itemClickListener: OnItemRecipeClickListener? = null

    fun setOnItemClickListener(listener: OnItemRecipeClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRecipeBinding.bind(view)
        val cardView = binding.cvItemRecipe
        val imageView = binding.ivTitleItemRecipe
        val textView = binding.tvTitleItemRecipe
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recipe, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = dataSet[position]

        val drawable = try {
            val context = holder.itemView.context
            context.assets.open(recipe.imageUrl).use { stream ->
                Drawable.createFromStream(stream, recipe.imageUrl)
            }
        } catch (e: Exception) {
            Log.i("Id", recipe.id.toString())
            Log.e("Error", e.stackTraceToString())
            null
        }

        with(holder) {
            imageView.setImageDrawable(drawable)
            textView.text = recipe.title
            cardView.setOnClickListener { itemClickListener?.onItemClick(recipe.id) }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}