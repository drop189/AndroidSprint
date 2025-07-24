package com.example.androidsprint.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsprint.data.utils.Constants.ARG_RECIPE
import com.example.androidsprint.databinding.FragmentRecipeBinding
import com.example.androidsprint.model.entity.Recipe
import com.example.androidsprint.ui.adapters.IngredientsAdapter
import com.example.androidsprint.ui.adapters.MethodAdapter
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable<Recipe>(ARG_RECIPE)
        } else {
            arguments?.getParcelable(ARG_RECIPE, Recipe::class.java)
        }

        initUi(recipe)
        initRecycler(recipe)
    }

    private fun initUi(recipe: Recipe?) {
        val drawable = try {
            recipe?.imageUrl?.let {
                context?.assets?.open(it).use { stream ->
                    Drawable.createFromStream(stream, it)
                }
            }
        } catch (e: Exception) {
            Log.i("Id", recipe?.id.toString())
            Log.e("Error", e.stackTraceToString())
            null
        }

        binding.tvRecipeTitle.text = recipe?.title
        binding.ivRecipeTitle.setImageDrawable(drawable)
    }

    private fun initRecycler(recipe: Recipe?) {
        val rvIngredients = binding.rvIngredients
        val rvMethod = binding.rvMethod
        val dividerIngredients = MaterialDividerItemDecoration(
            rvIngredients.context,
            LinearLayoutManager.VERTICAL
        )
        val dividerMethod = MaterialDividerItemDecoration(
            rvMethod.context,
            LinearLayoutManager.VERTICAL
        )

        rvIngredients.adapter = recipe?.ingredients?.let { IngredientsAdapter(it) }
        rvMethod.adapter = recipe?.method?.let { MethodAdapter(it) }
        rvIngredients.addItemDecoration(dividerIngredients)
        rvMethod.addItemDecoration(dividerMethod)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}