package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue
import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType


import com.example.tp2.ui.theme.TP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var birthYear by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Bienvenue", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(170.dp))
        Text(text = text.text, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(20.dp))
        NameInput(text) { newText -> text = newText }
        Spacer(modifier = Modifier.height(20.dp))
        BirthYearInput(birthYear) { newYear -> birthYear = newYear }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (text.text.isBlank() || birthYear.text.isBlank()) {
                    Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(context, MainActivity2::class.java).apply {
                        putExtra("NOM_UTILISATEUR", text.text)
                        putExtra("ANNEE_NAISSANCE", birthYear.text)
                    }
                    context.startActivity(intent)
                }
            }
        ) {
            Text("Valider")
        }
    }
}

@Composable
fun NameInput(text: TextFieldValue, onTextChange: (TextFieldValue) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text("Saisir votre nom") },
    )
}

@Composable
fun BirthYearInput(birthYear: TextFieldValue, onTextChange: (TextFieldValue) -> Unit) {
    TextField(
        value = birthYear,
        onValueChange = onTextChange,
        label = { Text("Saisir votre année de naissance") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TP2Theme {
        MyApp()
    }
}
