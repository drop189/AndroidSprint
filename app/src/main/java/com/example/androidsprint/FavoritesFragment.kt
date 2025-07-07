package com.example.androidsprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidsprint.databinding.FragmentFavoritesBinding

class FavoritesFragment: Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }
}