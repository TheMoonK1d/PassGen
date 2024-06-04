package com.themoonk1d.passgen

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.themoonk1d.passgen.screen.PasswordApp
import com.themoonk1d.passgen.ui.theme.PassGenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PassGenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PasswordApp(modifier = Modifier.padding(innerPadding) )
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun PassGenPreview(){
    PassGenTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PasswordApp(modifier = Modifier.padding(innerPadding) )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PassGenPreviewDark(){
    PassGenTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PasswordApp(modifier = Modifier.padding(innerPadding) )
        }
    }
}

