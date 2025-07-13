package com.example.androidsprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.androidsprint.CategoriesListAdapter.OnItemClickListener
import com.example.androidsprint.Constants.ARG_CATEGORY_ID
import com.example.androidsprint.Constants.ARG_CATEGORY_IMAGE_URL
import com.example.androidsprint.Constants.ARG_CATEGORY_NAME
import com.example.androidsprint.databinding.FragmentListCategoriesBinding
import com.example.androidsprint.entity.Category

class CategoriesListFragment : Fragment() {

    private var _binding: FragmentListCategoriesBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding can't be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycler() {
        val adapter = CategoriesListAdapter(STUB.getCategories())

        binding.rvCategories.adapter = adapter

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(id: Int) {
                openRecipesByCategoryId(id)
            }
        })
    }

    private fun openRecipesByCategoryId(id: Int) {
        val categoryId: Int = id
        val categoryName: String =
            STUB.getCategories().find { category: Category -> id == category.id }?.title
                ?: ""
        val categoryImageUrl: String =
            STUB.getCategories().find { category: Category -> id == category.id }?.imageUrl
                ?: ""

        val bundle = bundleOf(
            Pair(ARG_CATEGORY_ID, categoryId),
            Pair(ARG_CATEGORY_NAME, categoryName),
            Pair(ARG_CATEGORY_IMAGE_URL, categoryImageUrl)
        )

        parentFragmentManager.commit {
            replace<RecipesListFragment>(R.id.mainContainer, args = bundle)
        }
    }
}