package com.jommaa.leboncoin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.leboncoin.R
import com.jommaa.leboncoin.view.adapters.AlbumsListAdapter
import com.jommaa.leboncoin.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val vm: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_albumsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = AlbumsListAdapter(this.context)
        }
        retryButton.setOnClickListener {
            vm.fetchAlbums()
        }
        vm.getAlbums().observe(viewLifecycleOwner, Observer<DataResult> {
            when (it) {
                is DataResult.Loading -> {
                    textError.visibility = View.GONE
                    retryButton.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    progress.visibility = View.GONE
                    when(it.albums != null && it.albums.isNotEmpty()){
                        true->{
                            (recycler_albumsList.adapter as AlbumsListAdapter).submitList(it.albums)
                        }
                        else->{
                            textError.visibility = View.VISIBLE
                            retryButton.visibility = View.VISIBLE
                            textError.text = "No data available To Display"
                        }
                    }


                }
                is DataResult.Failure -> {
                    progress.visibility = View.GONE
                    textError.visibility = View.VISIBLE
                    retryButton.visibility = View.VISIBLE
                    textError.text = it.message
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.fetchAlbums()
            }
        }
    }
}