package com.jommaa.leboncoin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.jommaa.domain.model.Album
import com.jommaa.leboncoin.databinding.ListItemAlbumBinding
import com.jommaa.leboncoin.utils.ObservableRecyclerViewAdapter
import com.squareup.picasso.Picasso

class AlbumsListAdapter(albums: ObservableList<Album>) : ObservableRecyclerViewAdapter<Album, AlbumsListAdapter.Holder>(albums) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ListItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class Holder(
        private val binding: ListItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var album: Album

        fun bind(album: Album) {
            this.album = album

            Picasso.get().load(album.thumbnailUrl).into(binding.image)
            binding.name.text = album.title
        }
    }
}