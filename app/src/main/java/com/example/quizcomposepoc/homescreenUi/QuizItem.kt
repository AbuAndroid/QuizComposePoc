package com.example.quizcomposepoc.homescreenUi


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizcomposepoc.model.Options
import com.example.quizcomposepoc.model.Questions
import com.example.quizcomposepoc.ui.theme.cardBackground


@Preview(showBackground = true)
@Composable
fun QuizItemPreview() {
    QuizItem(
        question = QuestionsUiModel(
            questionId = "1",
            questionName = "what is Your Name?",
            options = listOf(
                OptionsUiModel(
                    optionId = "1",
                    text = "Option1",
                    isSelected = false
                ),
                OptionsUiModel(
                    optionId = "1",
                    text = "Option1",
                    isSelected = false
                ),
                OptionsUiModel(
                    optionId = "1",
                    text = "Option1",
                    isSelected = false
                ),
            )
        ),
        onOptionSelected = { i, j -> }
    )
}

@Composable
fun QuizItem(
    question: QuestionsUiModel,
    onOptionSelected: (String, String) -> Unit
) {
    val context = LocalContext.current
    //val selectedOption = remember { mutableStateOf(radioOptions?.get(1)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = cardBackground,
            elevation = 5.dp,
        ) {
            Column {
                Text(
                    text = "${question.questionId}. ${question.questionName}",
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Divider(
                    color = Color.LightGray,
                    thickness = .5.dp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                question.options?.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onOptionSelected(
                                    question.questionId.toString(),
                                    option.optionId.toString()
                                )
                            }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = CenterVertically
                    ) {
                        RadioButton(
                            selected = option.isSelected ?:false,
                            modifier = Modifier
                                .padding(all = Dp(value = 8F)),
                            onClick = {
                                onOptionSelected(
                                    question.questionId.toString(),
                                    option.optionId.toString()
                                )
                                Toast.makeText(context, option.text, Toast.LENGTH_SHORT).show()
                            }
                        )
                        Text(text = option.text.toString(), fontSize = 13.sp)
                    }
                }
            }
        }
    }
}