package kz.kamran.todoapplication.data.remote.dto

data class TodoRequestDto(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val deadline: Long,
    val categoryId: Int
)
