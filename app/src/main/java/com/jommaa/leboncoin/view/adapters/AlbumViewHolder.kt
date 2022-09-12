package com.jommaa.leboncoin.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jommaa.domain.entities.Album
import com.jommaa.leboncoin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album.view.*

class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(album: Album) {
        val title = itemView.album_title
        val imag = itemView.album_icon
        title.text = album.title
        Picasso.get().load(album.url).placeholder(R.drawable.ic_launcher_background).into(imag)
    }
}