package com.example.mobiletechnicaltest.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.floor

@Composable
fun StarRating(
    rating: Double,
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    starColor: Color = Color(0xFFFFB400)
) {
    Row(modifier = modifier) {
        val filledStars = floor(rating).toInt()
        val hasHalfStar = rating - filledStars >= 0.5
        val emptyStars = (maxStars - filledStars - (if (hasHalfStar) 1 else 0)).coerceAtLeast(0)

        repeat(filledStars) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = starColor)
        }
        if (hasHalfStar) {
            Icon(imageVector = Icons.AutoMirrored.Filled.StarHalf, contentDescription = null, tint = starColor)
        }
        repeat(emptyStars) {
            Icon(imageVector = Icons.Outlined.StarOutline, contentDescription = null, tint = starColor)
        }
    }
}
