package kz.kamran.todoapplication.data.remote

import kz.kamran.todoapplication.data.model.Category
import kz.kamran.todoapplication.data.model.Todo
import kz.kamran.todoapplication.data.remote.dto.CategoryRequestDto
import kz.kamran.todoapplication.data.remote.dto.TodoRequestDto

interface RemoteRepository {
    suspend fun getCategoryList(): List<Category>

    suspend fun getTodoList(): List<Todo>

    suspend fun saveTodo(todoRequestDto: TodoRequestDto): Boolean

    suspend fun saveCategory(categoryRequestDto: CategoryRequestDto): Boolean
}