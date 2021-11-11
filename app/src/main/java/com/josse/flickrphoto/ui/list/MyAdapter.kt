package com.josse.flickrphoto.ui.list

import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.navigation.Navigation
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josse.flickrphoto.R
import com.josse.flickrphoto.model.Photo
import com.josse.flickrphoto.ui.main.MainFragmentDirections
//import kotlinx.android.synthetic.main.photo.view.*
import kotlinx.parcelize.Parcelize

class MyAdapter(
    private val photos: List<Any>,
    val callback: (Int) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val v: GridLayout) : RecyclerView.ViewHolder(v) {

    }

    /**
     *  Un nouveau ViewHolder qui contient une vue du type de vue donné.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.photo, parent, false);
        val holder = MyViewHolder(layout as GridLayout);
        return holder;
    }

    /**
     * @return Le nombre total d'éléments dans cet adaptateur.
     */
    override fun getItemCount(): Int {
        return photos.size
    }

    /**
     * @param holder Le ViewHolder qui doit être mis à jour pour représenter le contenu de l'élément
     * à la position donnée dans l'ensemble de données,élément à la position donnée dans l'ensemble
     * de données @param position La position de l'élément dans l'ensemble de données de l'adaptateur.
     */

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageView = holder.v.findViewById<ImageView>(R.id.imageView)
        val server:String = photos[position].toString()
        val id:String = photos[position].toString()
        val secret:String = photos[position].toString()
        var url:String = "https://live.staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg"
            imageView.setOnClickListener {
            Log.d("IMAGE", "Image on position $position clicked")
            val action = ListFragmentDirections.versFullFragment(url)
            Navigation.findNavController(holder.v).navigate(action)

        }
        Glide.with(holder.v).load(url).into(imageView)
    }
}