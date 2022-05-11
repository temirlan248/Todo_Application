package kz.kamran.todoapplication.presentation.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kz.kamran.todoapplication.R
import kz.kamran.todoapplication.databinding.TodoFragmentBinding
import java.util.*

@AndroidEntryPoint
class TodoFragment : Fragment() {

    private var _binding: TodoFragmentBinding? = null
    private val binding = _binding!!

    private val viewModel: TodoViewModel by viewModels()

    private val calendar = Calendar.getInstance()
    private var year: Int = calendar.get(Calendar.YEAR)
    private var month: Int = calendar.get(Calendar.MONTH) + 1 // january = 0
    private var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    private var hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
    private var minute: Int = calendar.get(Calendar.MINUTE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        viewModel.cancel()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            backImageView.setOnClickListener {
                findNavController().navigateUp()
            }

            saveButton.setOnClickListener {
                val title = titleEditText.text.toString()
                val description = descriptionEditText.text.toString()
                viewModel.saveTodo(
                    title = title,
                    description = description,
                    year = year,
                    month = month,
                    day = day,
                    hour = hour,
                    minute = minute
                )

            }
        }
    }
}