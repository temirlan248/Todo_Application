package kz.kamran.todoapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.kamran.todoapplication.data.model.Category
import java.util.*

@Parcelize
data class Todo(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean,
    val category: Category,
    val deadline: Date
) : Parcelable