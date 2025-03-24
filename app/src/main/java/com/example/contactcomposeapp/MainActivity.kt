package com.example.contactcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(12.dp)) {
                if (contact.imageRes == null) {
                    Image(
                        painter = painterResource(id = R.drawable.circle),
                        contentDescription = null,
                    )
                    Text(
                        contact.name.take(1) + contact.familyName.take(1),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Image(
                        painter = painterResource(contact.imageRes),
                        contentDescription = null,
                    )
                }
            }
            val initials = if (contact.surname.isNullOrEmpty()) contact.name
            else contact.name + " " + contact.surname
            Text(initials, style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold)
            Row {
                Text(contact.familyName.toString(),
                    modifier = Modifier.padding(5.dp, 0.dp),
                    style = MaterialTheme.typography.headlineMedium)
                if (contact.isFavorite)
                    Image(
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null,
                        modifier = Modifier.padding(0.dp, 8.dp)
                    )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(0.dp, 24.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top) {
            Row(modifier = Modifier.padding(0.dp,8.dp)) {
                Box(modifier = Modifier.weight(0.5f),
                    contentAlignment = Alignment.TopEnd) {
                    Text(text = stringResource(id = R.string.phone),
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.bodyLarge)
                }
                Box(modifier = Modifier.weight(0.5f).padding(12.dp, 0.dp),
                    contentAlignment = Alignment.TopStart) {
                    Text(contact.phone,style = MaterialTheme.typography.bodyLarge)
                }
            }
            Row(modifier = Modifier.height(48.dp)) {
                Box(modifier = Modifier.weight(0.5f).fillMaxHeight(),
                    contentAlignment = Alignment.CenterEnd) {
                    Text(stringResource(id = R.string.address),
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.bodyLarge)
                }
                Box(modifier = Modifier.weight(0.5f).padding(12.dp, 0.dp),
                    contentAlignment = Alignment.CenterStart) {
                    Text(text = contact.address, maxLines = 2,
                        style = MaterialTheme.typography.bodyLarge)
                }
            }
            Row(modifier = Modifier.padding(0.dp,8.dp)) {
                if (!contact.email.isNullOrEmpty()) {
                    Box(modifier = Modifier.weight(0.5f),
                        contentAlignment = Alignment.CenterEnd) {
                        Text(stringResource(id = R.string.email), fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.bodyLarge)
                    }
                    Box(modifier = Modifier.weight(0.5f).padding(12.dp, 0.dp),
                        contentAlignment = Alignment.CenterStart) {
                        Text(contact.email, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview1() {
    val contact1 = Contact(
        name = "Василий",
        surname = "Алибабаевич",
        familyName = "Пупкин",
        imageRes= R.drawable.picture,
        isFavorite = true,
        phone = "+7 949 000 00 01",
        address = "г.Донецк, ул.Артема, 155",
        email = "pupk_in@yandex.com",
    )
    ContactDetails(contact1)
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview2() {
    val contact1 = Contact(
        name = "Василий",
        surname = null,
        familyName = "Пупкин",
        imageRes= null,
        isFavorite = false,
        phone = "+7 949 000 00 02",
        address = "г.Донецк, ул.Артема, 155",
        email = null,
    )
    ContactDetails(contact1)
}