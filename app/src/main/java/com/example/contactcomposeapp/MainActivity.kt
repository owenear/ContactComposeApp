package com.example.contactcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
            )
            if (contact.surname.isNullOrEmpty()) Text(contact.name.take(1))
            else Text(contact.name.take(1) + contact.surname.take(1))
        }
        Text(contact.name + " " + contact.familyName)
        Text(contact.surname.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview() {
    val contact1 = Contact(
        name = "Вася",
        surname = "Пупкин",
        familyName = "Алибабаевич",
        imageRes= null,
        isFavorite = false,
        phone = "+7 949 000 00 01",
        address = "г.Донецк, ул.Артема, 1",
        email = null,
    )
    ContactDetails(contact1)
}