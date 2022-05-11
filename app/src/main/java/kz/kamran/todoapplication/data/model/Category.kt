package kz.kamran.todoapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val title: String,
) : Parcelable