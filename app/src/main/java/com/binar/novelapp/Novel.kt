package com.binar.novelapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Novel(
    var title: String,
    var author: String,
    var year: Int,
    var image: String,
    var detail: String,
    var sinopsis: String
) : Parcelable
