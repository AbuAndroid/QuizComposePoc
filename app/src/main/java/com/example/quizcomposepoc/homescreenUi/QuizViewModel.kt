package com.example.quizcomposepoc.homescreenUi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizcomposepoc.repository.QuizRepository
import kotlinx.coroutines.flow.*

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {


    private val viewModelState = MutableStateFlow(QuizViewModelState())

    val uiState = viewModelState.map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    init {
        quizList()
    }

    private fun quizList() {
        val response = repository.QuestionData()?.questions
        if (response?.isNotEmpty() == true) {
            viewModelState.update {
                it.copy(
                    isLoading = true,
                    quizTitle = "Anatomy Intro Session",
                    questions = response.map { questions ->
                    QuestionsUiModel(
                        questionId = questions.questionId,
                        questionName = questions.questionName,
                        options = questions.options?.map { option ->
                            OptionsUiModel(
                                text = option.text,
                                optionId = option.optionId,
                                isSelected = option.isSelected,
                            )
                        }
                    )
                }
                )
            }
        }
    }

    fun onOptionSelected(questionId: String, selectedOptionId: String) {
        val questions = uiState.value.questions
        val question = questions.find { question ->
            question.questionId == questionId
        }

        question?.options?.forEach { option ->
            option.isSelected = option.optionId == selectedOptionId
        }

        viewModelState.update {
            it.copy(
                questions = questions,
                lastUpdated = System.currentTimeMillis(),
            )
        }
        Log.e("option",question.toString())
        Log.e("selectedOption", "$questionId $selectedOptionId")
    }
}

data class QuizViewModelState(
    val isLoading: Boolean? = false,
    val quizTitle: String? = "",
    val questions: List<QuestionsUiModel> = mutableListOf(),
    val lastUpdated: Long = System.currentTimeMillis(),
) {
    fun toUiState(): QuizViewModelUi {
        return QuizViewModelUi(
            isLoading = isLoading,
            quizTitle = quizTitle,
            lastUpdated = lastUpdated,
            questions = questions
        )
    }
}

data class QuizViewModelUi(
    val isLoading: Boolean? = false,
    val quizTitle: String?,
    val questions: List<QuestionsUiModel>,
    val lastUpdated: Long,
)

data class QuestionsUiModel(
    val questionId: String?,
    val questionName: String?,
    val options: List<OptionsUiModel>?
)

data class OptionsUiModel(
    val optionId: String?,
    val text: String?,
    var isSelected: Boolean?
)
