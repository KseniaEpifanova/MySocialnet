package com.example.mysocialnet.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysocialnet.R
import com.example.mysocialnet.models.Image
import com.example.mysocialnet.databinding.ItemImageBinding

class ImageListAdapter(
    private val onClick: (Image) -> Unit
) : ListAdapter<Image, ImageListAdapter.ImageViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image, onClick)
    }

    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image, onClick: (Image) -> Unit) {
            binding.image = image

            Glide.with(binding.root.context)
                .load(image.previewURL)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(binding.imageThumbnail)

            binding.root.setOnClickListener { onClick(image) }
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }
        }
    }
}
