package com.example.marketplacenm.home.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marketplacenm.R
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme

@Composable
fun ItemGroupUI(@DrawableRes img:Int, title:String, modifier: Modifier=Modifier){
    Column() {
        Image(painter = painterResource(id = img), contentDescription = title, modifier=modifier.size(42.dp,38.dp ).clip(
            CircleShape))
        Text(text = title)
    }

}

@Preview(showBackground = true)
@Composable
private fun ItemGroupUIPreivew(){
    MarketPlaceNMTheme() {
        ItemGroupUI(0, "Phones")
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