package com.example.batmanproject.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.batmanproject.R
import com.example.batmanproject.data.FilmDetail
import com.example.batmanproject.databinding.FragmentDetailsBinding
import com.example.batmanproject.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.item_unsplash_photo.*
import kotlinx.android.synthetic.main.item_unsplash_photo.image_view

@AndroidEntryPoint
class DetailsFragment:Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()
    private val TAG = "DetailsFragment"
    private val viewModel by viewModels<DetailViewModel>()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCurrentQuery(args.film.imdbID)
        viewModel.search(args.film.imdbID)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
           val film = args.film
            Glide.with(this@DetailsFragment)
                .load(film.Poster)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                       return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewDescription.isVisible = film.Title != null
                        textViewActors.isVisible = true
                        return false
                    }
                })
                .into(imageView)
            textViewDescription.text = film.Title
            val uri = Uri.parse(film.Year)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            textViewActors.apply {
                text = "photo by ${film.Year} on Unsplash"
                setOnClickListener{
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }
        }


    viewModel.filmDetail.observe(viewLifecycleOwner){
        binding.apply {
            Glide.with(this@DetailsFragment)
                .load(it.Poster)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(imageView)
            genre.isVisible  = true
            genre.text = it.Genre
            textViewFilmDirector.text = it.Director
            textViewFilmTitle.text = it.Title
            textViewFilmYear.text = it.Year
            textViewDescription.text = it.Plot
            textViewActors.text = it.Actors
            textViewWriters.text = it.Writer
            textViewLanguage.text = it.Language
            textViewCountry.text = it.Country
            textViewAwards.text = it.Awards
        }
//        text_view_creator.text = it.Director
    }
    }


}