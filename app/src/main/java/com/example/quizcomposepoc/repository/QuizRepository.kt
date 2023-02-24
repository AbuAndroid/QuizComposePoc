package com.example.quizcomposepoc.repository

import com.example.quizcomposepoc.model.QuizItemModel
import com.example.quizcomposepoc.utils.AssertManager

class QuizRepository(val assertManager: AssertManager) {

    fun QuestionData(): QuizItemModel? {
        return assertManager.fetchDataFromAssert("quiz.json")
    }
}