package com.jommaa.leboncoin.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jommaa.domain.entities.Album
import com.jommaa.leboncoin.R

class AlbumsListAdapter(private val ctx: Context?) : RecyclerView.Adapter<AlbumViewHolder>() {

    private val data = mutableListOf<Album>()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AlbumViewHolder {
        return AlbumViewHolder(LayoutInflater.from(ctx).inflate(R.layout.album, p0, false))
    }

    override fun getItemCount(): Int {
        data?.let {
            return it.size;
        }
        return 0

    }

    fun submitList(newData: List<Album>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: AlbumViewHolder, p1: Int) {
        p0.bindItems(data!![p1])
    }
}