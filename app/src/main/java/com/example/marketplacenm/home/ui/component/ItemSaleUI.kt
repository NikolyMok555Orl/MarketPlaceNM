package com.example.marketplacenm.home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marketplacenm.home.data.js.ItemSale
import com.example.marketplacenm.home.data.js.ItemsSale
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme

@Composable
fun ItemSaleUI(sale: ItemSale, onClick:(sale:ItemSale)->Unit, modifier:Modifier=Modifier){
    Box(modifier=modifier.size(174.dp, 221.dp).clip(RoundedCornerShape(11.dp)).clickable {
        onClick(sale)
    }) {
        AsyncImage(model = sale.image_url, contentDescription = sale.name, modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemLatestUIPreivew(){
    MarketPlaceNMTheme() {
        ItemSaleUI(ItemSale("", 0, "", "", 0.0), {})
    }
}