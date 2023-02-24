package com.example.quizcomposepoc.homescreenUi

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizcomposepoc.R
import com.example.quizcomposepoc.ui.theme.QuizContentBackground
import com.example.quizcomposepoc.ui.theme.buttonBackground
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen(
        quizTitle = "Anotomy quiz",
        questions = listOf(
            QuestionsUiModel(
                questionId = "1",
                questionName = "What is Your Name ?",
                options = listOf(
                    OptionsUiModel(
                        optionId = "1",
                        text = "option1",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option2",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option3",
                        isSelected = false
                    )
                )
            ),
            QuestionsUiModel(
                questionId = "2",
                questionName = "What is Your Name ?",
                options = listOf(
                    OptionsUiModel(
                        optionId = "1",
                        text = "option1",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option2",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option3",
                        isSelected = false
                    )
                )
            ),
        ),
        onOptionSelected = { questionId, optionsId -> }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuizScreen(
    quizTitle: String,
    questions: List<QuestionsUiModel>,
    onOptionSelected: (String, String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            BottomSheetContent()
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        scrimColor = Color.Black.copy(0.5f),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TopBar(quizTitle)
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(QuizContentBackground)
                    ) {
                        items(questions) { item ->
                            QuizItem(
                                question = item,
                                onOptionSelected = { questionId, selectedOptionId ->
                                    onOptionSelected(questionId, selectedOptionId)
                                }
                            )
                        }
                    }
                }
                BottomBar(sheetState)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview(){
    BottomSheetContent()
}


@Composable
fun BottomSheetContent() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Image(
                painter = painterResource(id = R.drawable.success),
                contentDescription = "Success",
                modifier = Modifier.size(80.dp)
            )
            Text(text = "Quiz \n Submitted Successfully",
                lineHeight = 25.sp,
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "Your answer are viewwable once the Quiz Ends",
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.padding(10.dp))
            Divider(
                color = Color.LightGray,
                thickness = .5.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(.5.dp,buttonBackground),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(text = "Close", color = buttonBackground)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("")
}

@Composable
fun TopBar(quizTitle: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF1A7BDC), Color(0xFF56B8FF)),
                    start = Offset(0f, 0f),
                    end = Offset(1f, 500f)
                )
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.appbarbackground),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back home",
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = quizTitle,
                    color = Color.White, fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(bottom = 5.dp))
                Text(
                    text = "3 Questions* Quiz Timer 04:00",
                    color = Color.White, fontSize = 14.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizListPreview() {
    QuizList(
        questions = listOf(
            QuestionsUiModel(
                questionId = "1",
                questionName = "What is Your Name ?",
                options = listOf(
                    OptionsUiModel(
                        optionId = "1",
                        text = "option1",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option2",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option3",
                        isSelected = false
                    )
                )
            ), QuestionsUiModel(
                questionId = "2",
                questionName = "What is Your Name ?",
                options = listOf(
                    OptionsUiModel(
                        optionId = "1",
                        text = "option1",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option2",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option3",
                        isSelected = false
                    )
                )
            ), QuestionsUiModel(
                questionId = "3",
                questionName = "What is Your Name ?",
                options = listOf(
                    OptionsUiModel(
                        optionId = "1",
                        text = "option1",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option2",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option3",
                        isSelected = false
                    )
                )
            ), QuestionsUiModel(
                questionId = "4",
                questionName = "What is Your Name ?",
                options = listOf(
                    OptionsUiModel(
                        optionId = "1",
                        text = "option1",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option2",
                        isSelected = false
                    ), OptionsUiModel(
                        optionId = "1",
                        text = "option3",
                        isSelected = false
                    )
                )
            )
        ),
        onOptionSelected = { i, j -> },
    )
}

@Composable
fun QuizList(
    questions: List<QuestionsUiModel>,
    onOptionSelected: (String, String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(QuizContentBackground)
    ) {
        items(questions) { item ->
            QuizItem(
                question = item,
                onOptionSelected = { questionId, selectedOptionId ->
                    onOptionSelected(questionId, selectedOptionId)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    val sheetStatePrevew = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    BottomBar(sheetStatePrevew)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBar(sheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .background(buttonBackground)
            .fillMaxWidth()
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    sheetState.show()
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonBackground)
        ) {
            Text(text = "Submit", color = Color.White)
        }
    }
}
