package com.example.decisionkmm.android

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Delete
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
    val decisionsList = decisionViewModel.decisions.collectAsState()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val heightModifier = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
        Modifier.fillMaxHeight(0.2f)
    } else {
        Modifier.fillMaxHeight(0.4f)
    }

    LaunchedEffect(key1 = true) {
        decisionViewModel.loadDecisions()
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
                    val newDecision by decisionViewModel.newDecision.collectAsState()
                    InputValue(text = newDecision) { value -> decisionViewModel.onNewDecisionChange(value)}
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
                    if (newDecision.isNotBlank()){
                        decisionViewModel.addDecision()
                    } else{
                        Toast.makeText(context,"Write some decision!",Toast.LENGTH_LONG).show()
                    }
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
            LazyColumn{
                items(decisionsList.value.size){index ->
                    val decision = decisionsList.value[index]
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .height(45.dp)
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .horizontalScroll(rememberScrollState()),
                                text = decision.title,
                                maxLines = 1
                            )
                            IconButton(
                                onClick = {
                                          decision.id?.let { id ->
                                              decisionViewModel.deleteDecisionById(id)
                                          }
                                },
                                modifier = Modifier.size(30.dp)) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "delete",
                                    tint = Color.Red)
                            }
                        }
                    }
                }
            }
                }
                //Result Card
                val randomDecision by decisionViewModel.randomDecision.collectAsState()
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
                            text =  randomDecision,
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
                            decisionViewModel.deleteAllDecisions()
                        }
                    ) {
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
                            if (decisionsList.value.isEmpty()) {
                                Toast.makeText(context, "No decisions to select", Toast.LENGTH_SHORT).show()
                            } else {
                                decisionViewModel.getRandomDecision()
                            }
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

    TextField(
        value = text,
        modifier = Modifier.fillMaxWidth(0.7f),
        placeholder = { Text("Enter value") },
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onValueChange("")
                    }
                ) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            }
        },
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        onValueChange = { newInputValue ->
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