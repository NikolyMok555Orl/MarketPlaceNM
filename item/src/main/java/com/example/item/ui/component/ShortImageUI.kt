package com.example.item.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ShortImageUI(index:Int,image:String, isSelect:Boolean, onClick:(index:Int)->Unit){
        val size= remember(isSelect) {
            if(isSelect){
                DpSize(83.dp,45.dp )
            }else{
                DpSize(68.dp,39.dp)
            }

        }

    AsyncImage(model = image, contentDescription = null,  contentScale = ContentScale.Crop,
        modifier = Modifier.padding(end=4.dp).size(size).clip(RoundedCornerShape(6.dp)).clickable { onClick(index) })
    //.shadow(4.dp, RoundedCornerShape(6.dp)


}