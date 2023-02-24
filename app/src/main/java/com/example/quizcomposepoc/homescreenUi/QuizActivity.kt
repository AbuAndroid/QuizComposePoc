package com.example.quizcomposepoc.homescreenUi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizcomposepoc.repository.QuizRepository
import com.example.quizcomposepoc.ui.theme.QuizComposePocTheme
import com.example.quizcomposepoc.utils.AssertManager

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizComposePocTheme {
                QuizRouter(QuizViewModel(repository = QuizRepository(assertManager = AssertManager(this))))
            }
        }
    }
}
