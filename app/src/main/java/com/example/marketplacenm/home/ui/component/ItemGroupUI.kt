package com.example.marketplacenm.home.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketplacenm.R
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme

@Composable
fun ItemGroupUI(@DrawableRes img: Int, title: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .width(52.dp)
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = title,
            modifier = modifier
                .size(42.dp, 38.dp)
                .clip(
                    CircleShape
                ).background(color = Color(0xFFEEEFF4))
                .padding(9.dp)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.overline.copy(
                fontSize = 8.sp,
                color = Color(0xFFA6A7AB),
                fontFamily = FontFamily(
                    Font(R.font.poppins)
                )
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun ItemGroupUIPreivew() {
    MarketPlaceNMTheme() {
        Row(modifier = Modifier.padding(8.dp)) {
            ItemGroupUI(R.drawable.c_phones, "Phones")
        }
    }
}


fun getGroup(): List<Group> = listOf(
    Group(R.drawable.c_phones, "Phones"),
    Group(R.drawable.c_headphones, "Headphones"),
    Group(R.drawable.c_games, "Games"),
    Group(R.drawable.c_cars, "Cars"),
    Group(R.drawable.c_furniture, "Furniture"),
    Group(R.drawable.c_kids, "Kids"),
    Group(R.drawable.c_phones, "Gods"),
    Group(R.drawable.c_phones, "RockGroups"),

    )

data class Group(val icon: Int, val title: String)