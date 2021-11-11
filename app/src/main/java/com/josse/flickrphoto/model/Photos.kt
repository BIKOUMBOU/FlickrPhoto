package com.josse.flickrphoto.model

import android.provider.ContactsContract

class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String?,
    val photo: List<Photo>) {
}