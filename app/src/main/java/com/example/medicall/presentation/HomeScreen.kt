package com.example.medicall.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.runtime.R
import androidx.navigation.NavController
import com.example.medicall.data.Services
import com.example.medicall.data.createDataList
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime

@Composable
fun HomeScreen(navController: NavController,auth: FirebaseAuth) {

    Column(modifier = Modifier.fillMaxSize()) {
        val user = auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }



            val current = LocalDateTime.now()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(46.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = current.dayOfWeek.name +
                                " ${current.dayOfMonth} ${current.month.name} " +
                                "${current.year}",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "Hello ${user?.email!!.split("@")[0]}",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(1.dp),
                        fontSize = 30.sp
                    )


                }


                Image(
                    painter = painterResource(id = com.example.medicall.R.drawable.ic_notification),
                    contentDescription = "notification",
                    modifier = Modifier
                        .size(60.dp)
                        .scale(scaleX = 0.5f, scaleY = 0.5f)
                )
            }

        AssistanceCards()
        Text(text = "One Click Emergency Assistance", style = MaterialTheme.typography.h6, modifier = Modifier.padding(10.dp))
        PhotoGrid()
    }
}

@Composable
fun AssistanceCards() {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Color.LightGray),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = com.example.medicall.R.drawable.siren),
                contentDescription = "siren",
                modifier = Modifier
                    .padding(bottom = 8.dp, top = 10.dp, start = 10.dp, end = 10.dp)
            )
            Text(
                text = "Emergency Assistance",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp)
            )
            Icon(
                painterResource(id = com.example.medicall.R.drawable.foward),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun PhotoGrid() {
    val services = createDataList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(services.size) { photo ->
            Cards(services = services[photo])
        }
    }
}

@Composable
fun Cards(services: Services) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Color.LightGray),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column (modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = services.image),
                contentDescription = services.name,
                modifier = Modifier
                    .size(180.dp)
                    .scale(scaleX = 0.5f, scaleY = 0.5f)
            )
            Text(
                text = services.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}