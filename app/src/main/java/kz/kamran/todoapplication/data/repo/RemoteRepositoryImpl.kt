package kz.kamran.todoapplication.data.repo

import kz.kamran.todoapplication.data.model.Category
import kz.kamran.todoapplication.data.model.Todo
import kz.kamran.todoapplication.data.remote.api.AuthApi
import kz.kamran.todoapplication.data.remote.api.TodoApi
import kz.kamran.todoapplication.data.remote.mapper.toCategory
import kz.kamran.todoapplication.data.remote.mapper.toTodo
import kz.kamran.todoapplication.data.remote.RemoteRepository
import kz.kamran.todoapplication.data.remote.dto.CategoryRequestDto
import kz.kamran.todoapplication.data.remote.dto.TodoRequestDto
import kz.kamran.todoapplication.exception.InternalException
import kz.kamran.todoapplication.exception.UnauthorizedException
import okhttp3.internal.http.HTTP_UNAUTHORIZED
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val todoApi: TodoApi
) : RemoteRepository {
    override suspend fun getCategoryList(): List<Category> {
        val response = todoApi.getCategoryList()
        if (!response.isSuccessful) {

            // no token or invalid token
            if (response.code() == HTTP_UNAUTHORIZED) {
                throw UnauthorizedException()
            }

            // some server error
            throw InternalException()
        }

        val body = response.body()!!
        val remoteCategoryList = body.categoryList
        val categoryList = remoteCategoryList.map { it.toCategory() }
        return categoryList
    }

    override suspend fun getTodoList(): List<Todo> {
        val response = todoApi.getTodoList()
        if (!response.isSuccessful) {

            // no token or invalid token
            if (response.code() == HTTP_UNAUTHORIZED) {
                throw UnauthorizedException()
            }

            // some server error
            throw InternalException()
        }

        val body = response.body()!!
        val remoteCategoryList = body.todoList
        val todoList = remoteCategoryList.map { it.toTodo() }
        return todoList
    }

    override suspend fun saveTodo(todoRequestDto: TodoRequestDto): Boolean {
        val response = todoApi.saveTodo(todoRequestDto)
        if (!response.isSuccessful) {

            // no token or invalid token
            if (response.code() == HTTP_UNAUTHORIZED) {
                throw UnauthorizedException()
            }

            // some server error
            throw InternalException()
        }

        val body = response.body()!!
        return body.success
    }

    override suspend fun saveCategory(categoryRequestDto: CategoryRequestDto): Boolean {
        val response = todoApi.saveCategory(categoryRequestDto)
        if (!response.isSuccessful) {

            // no token or invalid token
            if (response.code() == HTTP_UNAUTHORIZED) {
                throw UnauthorizedException()
            }

            // some server error
            throw InternalException()
        }

        val body = response.body()!!
        return body.success
    }
}