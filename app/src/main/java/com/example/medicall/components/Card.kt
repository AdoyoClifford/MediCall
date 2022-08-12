package com.example.medicall.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.medicall.ui.theme.Beige1
import com.example.medicall.ui.theme.DeepBlue
import com.example.medicall.ui.theme.TextWhite

@Composable
fun MedicallCard(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    color: Color = TextWhite,
    contentColor: Color = Color.Black,
    border: BorderStroke? = null,
    elevation: Dp = 10.dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.padding(10.dp),
        shape = shape,
        backgroundColor = color,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        content = content
    )
}