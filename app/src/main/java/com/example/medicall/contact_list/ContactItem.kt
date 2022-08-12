package com.example.medicall.contact_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicall.components.MedicallCard
import com.example.medicall.data.Contacts

@Composable
fun ContactItem(
    contact: Contacts,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    MedicallCard {
        Row(
            modifier = modifier,
            // verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = contact.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = {
                        onEvent(ContactListEvent.OnDeleteContact(contact))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                }
                contact.phone?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it, color = Color.DarkGray, fontWeight = FontWeight.W100)
                }

                Text(
                    text = contact.email,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

            }
            Checkbox(
                checked = contact.isDone,
                onCheckedChange = { isChecked ->
                    onEvent(ContactListEvent.OnDoneChange(contact, isChecked))
                }
            )
        }
    }
}