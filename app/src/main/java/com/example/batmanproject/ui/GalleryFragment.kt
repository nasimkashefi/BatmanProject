package com.example.batmanproject.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.batmanproject.R
import com.example.batmanproject.data.Film
import com.example.batmanproject.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery),GalleryAdapter.OnItemClickListener {
    private val TAG = "GalleryFragment"
   private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)
        val adapter = GalleryAdapter(this)
        Log.d(TAG, "onViewCreated: ")
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
            recyclerView.adapter = adapter

        }
        viewModel.films.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }

    override fun onItemClick(film: Film) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(film)
        findNavController().navigate(action)
    }
}