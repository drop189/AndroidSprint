package com.example.androidsprint

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.androidsprint.Constants.ARG_CATEGORY_ID
import com.example.androidsprint.Constants.ARG_CATEGORY_IMAGE_URL
import com.example.androidsprint.Constants.ARG_CATEGORY_NAME
import com.example.androidsprint.RecipesListAdapter.OnItemRecipeClickListener
import com.example.androidsprint.databinding.FragmentListRecipesBinding

class RecipesListFragment : Fragment() {

    private var _binding: FragmentListRecipesBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding can't be null")

    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryImageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryId = requireArguments().getInt(ARG_CATEGORY_ID)
        categoryName = requireArguments().getString(ARG_CATEGORY_NAME)
        categoryImageUrl = requireArguments().getString(ARG_CATEGORY_IMAGE_URL)

        initTitle()
        initRecycler()
    }

    private fun initTitle() {

        val drawable = try {
            categoryImageUrl?.let {
                context?.assets?.open(it).use { stream ->
                    Drawable.createFromStream(stream, it)
                }
            }
        } catch (e: Exception) {
            Log.i("Id", categoryId.toString())
            Log.e("Error", e.stackTraceToString())
            null
        }

        binding.ivTitleListRecipe.setImageDrawable(drawable)
        binding.tvTitleListRecipe.text = categoryName
    }

    private fun initRecycler() {
        val adapter = RecipesListAdapter(STUB.getRecipesByCategoryId(categoryId))
        binding.rvRecipe.adapter = adapter

        adapter.setOnItemClickListener(object : OnItemRecipeClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        })
    }

    private fun openRecipeByRecipeId(recipeId: Int) {
        parentFragmentManager.commit {
            replace<RecipeFragment>(R.id.mainContainer)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}