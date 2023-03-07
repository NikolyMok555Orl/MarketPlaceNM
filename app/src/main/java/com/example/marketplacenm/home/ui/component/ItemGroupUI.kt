package com.example.marketplacenm.home.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme

@Composable
fun ItemGroupUI(img:String, title:String){
    Column() {
        AsyncImage(model = img, contentDescription = "")
        Text(text = title)
    }

}

@Preview(showBackground = true)
@Composable
private fun ItemGroupUIPreivew(){
    MarketPlaceNMTheme() {
        ItemGroupUI("", "Phones")
    }
}