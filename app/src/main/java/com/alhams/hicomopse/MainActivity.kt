package com.alhams.hicomopse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alhams.hicomopse.ui.theme.HiComopseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiComopseTheme {
                // A surface container using the 'background' color from the theme
                MyApp(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320)
private fun GreetingsPreview() {
    Greetings()
}

@Composable
private fun Greeting(name: String) {
    var expanded = remember {
        mutableStateOf<Boolean>(false)
    }

    var extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            ElevatedButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(text = if (expanded.value) "See less" else "See more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier, onContinueClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked,
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    HiComopseTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}


@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }

    Surface(
        modifier = modifier
    ) {
        if (shouldShowOnboarding) OnboardingScreen(onContinueClicked = {
            shouldShowOnboarding = false
        })
        else Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 720)
@Composable
fun MyAppPreview() {
    HiComopseTheme {
        MyApp()
    }
}