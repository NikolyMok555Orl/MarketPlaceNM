package com.example.uigeneral.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uigeneral.R

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(Font(R.font.montserat_bold)),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        letterSpacing = -(0.3).sp
    ),
    button = TextStyle(
        fontSize = 14.sp,
        letterSpacing = -(0.3).sp,
        fontWeight = FontWeight.Bold,
    ),
    overline = TextStyle(
        fontSize = 8.sp,
        letterSpacing = -(0.3).sp,
        fontWeight = FontWeight.W500,
        fontFamily = FontFamily(
            Font(R.font.poppins)
        ),
        lineHeight = 13.sp,
        color = onSecondary
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 15.sp,
        letterSpacing = -(0.3).sp,
        fontFamily = FontFamily(
            Font(R.font.poppins)
        ),
        lineHeight = 22.5.sp
    )




)