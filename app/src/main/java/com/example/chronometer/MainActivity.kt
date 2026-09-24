package com.example.chronometer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chronometer.ui.theme.ChronometerTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChronometerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ChronometerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ChronometerScreen(
    modifier: Modifier = Modifier
) {
    var elapsedTime by remember {
        mutableLongStateOf(0L)
    }
    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000.milliseconds)
            elapsedTime++
        }
    }




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = formatTime(elapsedTime),
            fontSize = 48.sp
        )

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        Button(
            onClick = {
                isRunning = true
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(100.dp)
        ) {
            Text("Start")
        }

        Button(
            onClick = {
                isRunning = false
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(100.dp)
        ) {
            Text("Pause")
        }

        Button(
            onClick = {
                isRunning = false
                elapsedTime = 0L
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(100.dp)
        ) {
            Text("Reset")
        }
    }
}

fun formatTime(seconds: Long): String{
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format(
        locale = null,
        "%02d:%02d",
        minutes,
        remainingSeconds
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChronometerTheme {
        ChronometerScreen()
    }
}