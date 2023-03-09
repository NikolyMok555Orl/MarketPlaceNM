package com.example.marketplacenm.home.ui.component

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
import com.example.marketplacenm.home.data.js.Latest
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme

@Composable
fun ItemLatestUI(latest: Latest, modifier: Modifier=Modifier){
    Box(modifier=modifier.size(114.dp, 149.dp).clip(RoundedCornerShape(9.dp))) {
    AsyncImage(model = latest.image_url, contentDescription = latest.name, modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemLatestUIPreivew(){
    MarketPlaceNMTheme() {
        ItemLatestUI(Latest("", "", "", 0))
    }
}