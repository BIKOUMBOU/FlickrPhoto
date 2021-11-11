package com.josse.flickrphoto.ui.list

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josse.flickrphoto.R


class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.list_fragment, container, false)
        viewModel = ListViewModel()
        viewModel.photos.observe(this, Observer {photos ->
            run {
                val recycler = layout.findViewById<RecyclerView>(R.id.recyclerview);
                recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
                recycler.adapter = MyAdapter(photos as List<Any>) { position -> {
                }
                }
            }
        })
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

    }

}