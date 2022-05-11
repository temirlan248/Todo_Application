package kz.kamran.todoapplication.presentation.add_edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(

) : ViewModel() {

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    fun cancel() = job.cancel()
    fun saveTodo(
        title: String,
        description: String,
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        minute: Int
    ) {

    }

}