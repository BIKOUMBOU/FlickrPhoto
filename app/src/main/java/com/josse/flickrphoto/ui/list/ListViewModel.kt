package com.josse.flickrphoto.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josse.flickrphoto.model.Photo
import com.josse.flickrphoto.model.SearchResult
import com.josse.flickrphoto.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {
    val photos = MutableLiveData<List<Photo>>()
    private val photosList = ArrayList<Photo>()

    init {
        val repo = Repository()
        repo.getPhotos(object: Callback<SearchResult> {
            override fun onFailure(calll: Call<SearchResult>, t: Throwable) {
                Log.v("flickrlog", "Callback error with flickr")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<
                    SearchResult>) {
                val res = response.body()?.photos
                for (photo in res?.photo!!) {
                    photosList.add(photo)
                }
                photos.value = photosList
            }
        })
    }
}