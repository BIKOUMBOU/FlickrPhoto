package com.josse.flickrphoto.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.josse.flickrphoto.R.layout.main_fragment
import com.bumptech.glide.Glide
import com.josse.flickrphoto.R
import com.josse.flickrphoto.model.Photo


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        var url: String = ""
        val layout =  inflater.inflate(main_fragment, container, false)
        val nextButton = layout.findViewById<Button>(R.id.nextButton)
        val allButton = layout.findViewById<Button>(R.id.allImagesButton)
        val imageTitle = layout.findViewById<TextView>(R.id.imageTitle)
        val imageView = layout.findViewById<ImageView>(R.id.imageView)
        allButton.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.versListFragment)
        }
        val observer = Observer<Photo> { photo ->

            val url = "https://farm" + photo.farm + ".staticflickr.com/" +
                       photo.server + "/" + photo.id+"_"+photo.secret + ".jpg"

            imageTitle.text = photo.title
            imageView.setOnClickListener {
                val action = MainFragmentDirections.versFullFragment(url)
                Navigation.findNavController(layout).navigate(action)
            }
            Glide.with(layout).load(url).into(imageView)
        }
        with(viewModel) {
            photos.observe(viewLifecycleOwner, observer)
        }
        nextButton.setOnClickListener {
            with(viewModel) { nextPhoto() }
        }
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider.(this).get(MainViewModel::class.java)
        ViewModelProvider(this).get(MainViewModel::class.java).also { viewModel = it }

    }

}