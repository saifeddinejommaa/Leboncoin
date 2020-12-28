package com.jommaa.leboncoin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jommaa.leboncoin.LeboncoinApp
import com.jommaa.leboncoin.R
import com.jommaa.leboncoin.viewmodel.MainViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.jommaa.leboncoin.databinding.ActivityMainBinding
import com.jommaa.leboncoin.view.adapter.AlbumsListAdapter
import org.jetbrains.annotations.NotNull

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // (application as LeboncoinApp).component.inject(this)
       // val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
      //  binding.viewModel = viewModel

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAlbumsList()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun bindAlbumsList(@NonNull recyclerView: RecyclerView, viewModel: MainViewModel) {
            val adapter = AlbumsListAdapter(viewModel.albumsList)
            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unbound()
    }

    override fun onPause() {
        viewModel.clear()
        super.onPause()
    }


}