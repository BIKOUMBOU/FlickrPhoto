package com.josse.flickrphoto.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josse.flickrphoto.model.Photo
import com.josse.flickrphoto.model.SearchResult
import com.josse.flickrphoto.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    public val photos = MutableLiveData<Photo>()
    private val photosList = ArrayList<Photo>()

    init {
        val repo = Repository()
        repo.getPhotos(object: Callback<SearchResult> {
            override fun onFailure(calll: Call<SearchResult>, t: Throwable) {
                Log.v("flickrlog", "Callback error with flickr")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val res = response.body()?.photos
                for (photo in res?.photo!!) {
                    photosList.add(photo)
                }
                nextPhoto()
            }
        })
    }

    fun nextPhoto() {
        val currentVal = photos.value
        var nextIndex: Int
        if(currentVal === null) {
            nextIndex = 0
        } else {
            val currentIndex = photosList.indexOf(currentVal)
            nextIndex = currentIndex + 1
            if(currentIndex === photosList.size - 1) {
                nextIndex = 0
            }
        }
        photos.value = photosList[nextIndex]
    }
}