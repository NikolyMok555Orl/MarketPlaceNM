package com.example.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.example.remote.js.ItemSale
import com.example.uigeneral.theme.MarketPlaceNMTheme
import  com.example.home.R
import com.example.uigeneral.theme.AppIconButton
import com.example.uigeneral.theme.CategoryBack
import com.example.uigeneral.theme.Discount


@Composable
fun ItemSaleUI(sale: ItemSale, onClick:(sale: ItemSale)->Unit, modifier:Modifier=Modifier){
    Box(modifier= modifier.padding(end=9.dp)
        .size(174.dp, 221.dp)
        .clip(RoundedCornerShape(11.dp))
        .clickable {
            onClick(sale)
        }) {
        AsyncImage(model = sale.image_url, contentDescription = sale.name, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Column(modifier = Modifier.padding(start = 9.dp, top = 121.dp)) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .height(12.dp)
                .background(CategoryBack), contentAlignment = Alignment.Center) {
                Text(text = sale.category, modifier = Modifier.padding(horizontal = 9.dp),
                    style = MaterialTheme.typography.h2, fontSize = 9.sp, fontWeight = FontWeight.W600)
            }
            Text(text = sale.name, modifier = Modifier
                .width(75.dp)
                .padding(top = 7.dp, bottom = 5.dp), style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600,
                fontSize = 13.sp, color = Color.White)
            Text(text = "$ " +sale.price, style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W600, fontSize = 10.sp , color =  Color.White)

        }
        Row(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 4.dp, bottom = 7.dp), verticalAlignment = Alignment.CenterVertically) {
            Row (modifier = Modifier.padding(bottom = 5.dp)){
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(28.dp)
                        .background(AppIconButton)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "add to favorite",
                        modifier = Modifier.size(12.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(35.dp)
                .background(AppIconButton)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add to cart button latest",
                    modifier = Modifier.size(13.dp), tint = MaterialTheme.colors.primary )
            }
        }

        Box(modifier = Modifier
            .padding(7.dp)
            .clip(CircleShape)
            .size(25.dp), contentAlignment = Alignment.Center) {
            AsyncImage(model =R.drawable.sale_avatar, contentDescription = "saleAvatar", contentScale = ContentScale.Crop)
        }

        Box(modifier = Modifier
            .padding(top = 7.dp, end = 8.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(Discount)
            .width(46.dp)
            .height(18.dp)

            .align(Alignment.TopEnd), contentAlignment = Alignment.Center
        ) {
            Text(text = sale.discount.toString() + "% off", style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600,
                color = Color.White, fontSize = 10.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun saleLatestUIPreivew(){
    MarketPlaceNMTheme() {
        ItemSaleUI(ItemSale("", 0, "", "", 0.0), {})
    }
}