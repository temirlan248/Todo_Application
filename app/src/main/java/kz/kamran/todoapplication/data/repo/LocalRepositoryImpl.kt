package kz.kamran.todoapplication.data.repo

import kz.kamran.todoapplication.data.local.entity.category.CategoryDao
import kz.kamran.todoapplication.data.local.entity.todo.TodoDao
import kz.kamran.todoapplication.data.local.mapper.toCategory
import kz.kamran.todoapplication.data.local.mapper.toTodo
import kz.kamran.todoapplication.data.model.Category
import kz.kamran.todoapplication.data.model.Todo
import kz.kamran.todoapplication.data.local.LocalRepository
import kz.kamran.todoapplication.data.local.mapper.toCategoryEntity
import kz.kamran.todoapplication.data.local.mapper.toTodoEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val todoDao: TodoDao
) : LocalRepository {
    override suspend fun getCategoryList(): List<Category> {
        val fetchedCategoryList = categoryDao.getAll()
        val categoryList = fetchedCategoryList.map { it.toCategory() }
        return categoryList
    }

    override suspend fun getTodoList(): List<Todo> {
        val fetchedTodoList = todoDao.getAll()
        val todoList = fetchedTodoList.map { it.toTodo() }
        return todoList
    }

    override suspend fun saveTodo(todo: Todo): Boolean {
        try {
            val category = categoryDao.getById(todo.id)
            if (category == null) {
                val category = todo.category
                categoryDao.insert(category.toCategoryEntity())
            }
            todoDao.insert(todo.toTodoEntity())
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override suspend fun saveCategory(category: Category): Boolean {
        try {
            categoryDao.insert(category.toCategoryEntity())
        } catch (e: Exception) {
            return false
        }
        return true
    }
}