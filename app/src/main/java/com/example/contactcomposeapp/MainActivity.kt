package com.example.contactcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.contactcomposeapp.ui.theme.ContactComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val contact = Contact(
            name = "Василий",
            surname = "Алибабаевич",
            familyName = "Пупкин",
            imageRes= R.drawable.picture,
            isFavorite = true,
            phone = "+7 949 000 00 01",
            address = "г.Донецк, ул.Артема, 155",
            email = "pupk_in@yandex.com",
        )

        setContent {
            ContactComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactDetails(contact, modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }
}

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.3f)) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(12.dp).size(96.dp, 96.dp)) {
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
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            val initials = if (contact.surname.isNullOrEmpty()) contact.name
            else "${contact.name} ${contact.surname}"
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
            modifier = Modifier.weight(0.7f).padding(0.dp, 24.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top) {

            RowInfo(stringResource(id = R.string.phone), contact.phone)
            RowInfo(stringResource(id = R.string.address), contact.address)
            if (!contact.email.isNullOrEmpty()) {
                RowInfo(stringResource(id = R.string.email), contact.email)
            }
        }
    }
}

@Composable
fun RowInfo(contactType: String, contactInfo: String) {
    Row(modifier = Modifier.padding(0.dp,8.dp)) {
        Box(modifier = Modifier.weight(0.5f).align(Alignment.CenterVertically),
            contentAlignment = Alignment.CenterEnd) {
            Text(contactType,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodyLarge)
        }
        Box(modifier = Modifier.weight(0.5f).padding(12.dp, 0.dp),
            contentAlignment = Alignment.CenterStart) {
            Text(text = contactInfo,
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview1() {
    val contact = Contact(
        name = "Василий",
        surname = "Алибабаевич",
        familyName = "Пупкин",
        imageRes= R.drawable.picture,
        isFavorite = true,
        phone = "+7 949 000 00 01",
        address = "г.Донецк, ул.Артема, 155",
        email = "pupk_in@yandex.com",
    )
    ContactDetails(contact)
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview2() {
    val contact = Contact(
        name = "Василий",
        surname = null,
        familyName = "Пупкин",
        imageRes= null,
        isFavorite = false,
        phone = "+7 949 000 00 02",
        address = "г.Донецк, ул.Артема, 155",
        email = null,
    )
    ContactDetails(contact)
}