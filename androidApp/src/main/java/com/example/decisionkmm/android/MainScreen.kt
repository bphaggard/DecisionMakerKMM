package com.example.decisionkmm.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionkmm.data.decision.DecisionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    decisionViewModel: DecisionViewModel = koinViewModel()
) {
    val configuration = LocalConfiguration.current
    val heightModifier = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
        Modifier.fillMaxHeight(0.2f)
    } else {
        Modifier.fillMaxHeight(0.4f)
    }

    Scaffold(
        topBar = { CustomTopAppBar() },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Input Value
                    var enteredValue by remember { mutableStateOf("") }
                    InputValue(text = enteredValue) { value -> enteredValue = value}
                    //Add Button
                    Button(
                        modifier = Modifier.height(55.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 6.dp,
                            disabledElevation = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(Color(0xFFB0BFA1)),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
//                    if (enteredValue.isNotBlank()){
//                        homeViewModel.addNote(NoteEntity(text = enteredValue))
//                    } else{
//                        coroutineScope.launch {
//                            Toast.makeText(context,"Enter value cannot be empty!",Toast.LENGTH_LONG).show()
//                        }
//                    }
                        }
                    )
                    {
                        Text(
                            text = "ADD",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                //Values Card
                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.5f)
                ) {
//            LazyColumn{
//                items(noteListState.value.size){index ->
//                    val note = noteListState.value[index]
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 10.dp)
//                            .height(45.dp)
//                    ) {
//                        Row (
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ){
//                            Text(
//                                modifier = Modifier
//                                    .fillMaxWidth(0.85f)
//                                    .horizontalScroll(rememberScrollState()),
//                                text = note.text,
//                                maxLines = 1
//                            )
//                            IconButton(
//                                onClick = {  },
//                                modifier = Modifier.size(30.dp)) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Delete,
//                                    contentDescription = "delete",
//                                    tint = Color.Red)
//                            }
//                        }
//                    }
//                }
//            }
                }
                //Result Card
                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .then(heightModifier),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.3f)
                                .background(Color(0xFFB0BFA1)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "RESULT :",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text =  "",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                        )
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Clear Button
                    Button(
                        modifier = Modifier.height(55.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 6.dp,
                            disabledElevation = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(Color(0xFFB0BFA1)),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
//                    coroutineScope.launch {
//                        homeViewModel.deleteAll()
//                        homeViewModel.chosenNote.value = ""}
                        }) {
                        Text(
                            text = "CLEAR LIST",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    //Choose Button
                    Button(
                        modifier = Modifier.height(55.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 6.dp,
                            disabledElevation = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(Color(0xFFB0BFA1)),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
//                    coroutineScope.launch {
//                        if (noteListState.value.isEmpty()){
//                            homeViewModel.chosenNote.value ?: ""
//                            Toast.makeText(context, "List is Empty", Toast.LENGTH_LONG).show()
//                        } else { homeViewModel.chooseNote() }
//                    }
                        }
                    ) {
                        Text(
                            text = "CHOOSE",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun InputValue(text: String, onValueChange: (String) -> Unit) {
    var inputValue by remember { mutableStateOf(text) }

    TextField(
        value = inputValue,
        modifier = Modifier.fillMaxWidth(0.7f),
        placeholder = { Text("Enter value") },
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            when {
                inputValue.isNotEmpty() -> IconButton(
                    onClick = {
                        inputValue = ""
                        onValueChange("") // Clear the value in the onValueChange callback
                    }
                ) {
                    Icon(Icons.Default.Clear, contentDescription = "clear text")
                }
            }
        },
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        onValueChange = { newInputValue ->
            inputValue = newInputValue
            // Update the value in the onValueChange callback
            onValueChange(newInputValue)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}