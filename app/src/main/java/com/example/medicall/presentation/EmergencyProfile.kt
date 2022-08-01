package com.example.medicall.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medicall.R

@Composable
fun EmergencyProfileScreen() {
    Column {
        Text(text = "Emergency Profile", modifier = Modifier.padding(12.dp))
        CardProfile()
        Text(text = "Emergency Contacts", modifier = Modifier.padding(12.dp))
    }

}

@Composable
fun CardProfile() {
    Card (
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(0.5f),
        border = BorderStroke(2.dp, Color.LightGray),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp),
    ){
        Row (modifier = Modifier
            .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_icons8_hospital),
                contentDescription = "profile"
            )
            Text(text = "Medical Profile")
        }
    }

}