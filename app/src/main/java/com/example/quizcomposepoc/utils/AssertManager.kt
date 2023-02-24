package com.example.quizcomposepoc.utils

import android.content.Context
import com.example.quizcomposepoc.model.QuizItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class AssertManager(val context:Context){
    fun fetchDataFromAssert(fileName:String): QuizItemModel? {

        val jsonString:String

        try{
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        }catch (ioException:IOException){
            ioException.printStackTrace()
            return null
        }

        val quizType = object :TypeToken<QuizItemModel>() {}.type

        return Gson().fromJson(jsonString,quizType)
    }
}

