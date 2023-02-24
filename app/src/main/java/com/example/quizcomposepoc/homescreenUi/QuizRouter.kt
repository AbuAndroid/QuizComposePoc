package com.example.quizcomposepoc.homescreenUi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizcomposepoc.model.Options
import com.example.quizcomposepoc.model.Questions

@Composable
fun QuizRouter(
    quizViewModel: QuizViewModel
){
    val uiState by quizViewModel.uiState.collectAsState()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Quiz_screen"){
        composable("Quiz_screen"){
            QuizScreen(
                quizTitle = uiState.quizTitle.toString(),
                questions = uiState.questions,
                onOptionSelected = {questionId,selectedOptionId->
                    quizViewModel.onOptionSelected(questionId,selectedOptionId)
                }
            )
        }
    }
}