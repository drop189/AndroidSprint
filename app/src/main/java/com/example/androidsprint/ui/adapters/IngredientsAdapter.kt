package com.example.androidsprint.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsprint.R
import com.example.androidsprint.databinding.ItemIngredientBinding
import com.example.androidsprint.model.entity.Ingredient

class IngredientsAdapter(private val dataSet: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemIngredientBinding.bind(view)
        val tvIngredient = binding.tvIngredient
        val tvQuantity = binding.tvQuantity
        val tvUnit = binding.tvUnit
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_ingredient, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val ingredient = dataSet[position]

        with(holder) {
            tvIngredient.text = ingredient.description
            tvQuantity.text = ingredient.quantity
            tvUnit.text = buildString {
                append(" ")
                append(ingredient.unitOfMeasure)
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}