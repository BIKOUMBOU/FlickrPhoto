package com.josse.flickrphoto.ui.full

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.josse.flickrphoto.R
import com.josse.flickrphoto.R.layout.full_fragment
import com.bumptech.glide.Glide
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;



class FullFragment : Fragment() {

    companion object {
        fun newInstance() = FullFragment()
    }

    private lateinit var viewModel: FullViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(full_fragment, container, false)
        val imageView = layout.findViewById<ImageView>(R.id.imageView)
        //val url = FullFragmentArgs.fromBundle(arguments!!).url
        val url = FullFragmentArgs.fromBundle(requireArguments()).url
        Glide.with(layout).load(url).into(imageView)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ViewModelProvider(this).get(FullViewModel::class.java).also { viewModel = it }

    }

}