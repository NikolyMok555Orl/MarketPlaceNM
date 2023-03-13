package com.example.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.remote.js.Latest
import com.example.uigeneral.theme.CategoryBack
import com.example.uigeneral.theme.MarketPlaceNMTheme

@Composable
fun ItemLatestUI(latest: com.example.remote.js.Latest, modifier: Modifier=Modifier){
    Box(modifier=modifier.padding(end=12.dp).size(114.dp, 149.dp).clip(RoundedCornerShape(9.dp)).background(Color.White)) {
    AsyncImage(model = latest.image_url, contentDescription = latest.name, contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize())
        Column(modifier = Modifier.padding(start = 7.dp, bottom = 7.dp, top = 91.dp)) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .height(12.dp)
                .background(CategoryBack), contentAlignment = Alignment.Center) {
                Text(text = latest.category, modifier = Modifier.padding(horizontal = 7.dp),
                    style = MaterialTheme.typography.h2, fontSize = 6.sp, fontWeight = FontWeight.W600)
            }
            Text(text = latest.name, modifier = Modifier.width(75.dp).padding(top=7.dp, end = 5.dp),
                style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 9.sp,
                color = Color.White)
            Text(text = "$ " +latest.price, style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600, fontSize = 7.sp ,
                color = Color.White)

        }
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp).clip(
            CircleShape
        ).size(20.dp).background(CategoryBack)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add to cart button latest",
                modifier = Modifier.size(8.dp), tint = MaterialTheme.colors.primary )
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun ItemLatestUIPreivew(){
    MarketPlaceNMTheme() {
        ItemLatestUI(com.example.remote.js.Latest("", "", "", 0))
    }
}