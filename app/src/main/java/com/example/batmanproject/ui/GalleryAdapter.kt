package com.example.batmanproject.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.batmanproject.R
import com.example.batmanproject.data.Film
import com.example.batmanproject.databinding.ItemUnsplashPhotoBinding

class GalleryAdapter(private val listener:OnItemClickListener)
    :PagingDataAdapter<Film,GalleryAdapter.GalleryViewHolder>(FILM_COMPARATOR) {
    private val TAG = "GalleryAdapter"
    interface OnItemClickListener {
        fun onItemClick(film: Film)
    }
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = getItem(position)
        Log.d(TAG, "onBindViewHolder: item is: $item")
        if (item != null){
            holder.bind(item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GalleryViewHolder(binding)
    }
    inner class GalleryViewHolder(val binding: ItemUnsplashPhotoBinding)
        :RecyclerView.ViewHolder(binding.root){
            init {
                binding.root.setOnClickListener{
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        val item = getItem(position)
                        if (item != null)
                            listener.onItemClick(item)
                    }
                }
            }
            fun bind(currentItem: Film){
           binding.apply {
               Glide.with(itemView)
                   .load(currentItem.Poster)
                   .centerCrop()
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .error(R.drawable.ic_error)
                   .into(imageView)

               textViewFilmTitle.text = currentItem.Title
               textViewFilmYear.text = currentItem.Year
           }

        }
    }
    companion object{
        private val FILM_COMPARATOR = object : DiffUtil.ItemCallback<Film>(){
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(
                oldItem: Film,
                newItem: Film
            ): Boolean =
                oldItem == newItem

        }
    }


}