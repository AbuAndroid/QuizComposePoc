package com.example.quizcomposepoc.model

import com.google.gson.annotations.SerializedName

data class QuizItemModel(
    val statusCode:Int?,
    val status:Boolean?,
    val message:String?,
    val questions:List<Questions>?
)

data class Questions(
    @SerializedName("question_id")
    val questionId:String?,
    @SerializedName("question_name")
    val questionName:String?,
    val options : List<Options>?
)

data class Options(
    @SerializedName("option_id")
    val optionId:String?,
    val text:String?,
    val answer:Boolean?,
    var isSelected : Boolean = false,
)