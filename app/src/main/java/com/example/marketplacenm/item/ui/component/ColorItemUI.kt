package com.example.marketplacenm.item.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ColorItemUI(index:Int, color: Color, isSelect:Boolean, onClick:(index:Int)->Unit, modifier: Modifier=Modifier){
    Spacer(modifier = modifier.size(34.dp, 26.dp).clip(RoundedCornerShape(9.dp)).background(color=color).border(color=Color(0xFFADADAD),
        width = if(isSelect) 2.dp else 0.dp, shape = RoundedCornerShape(9.dp)
    ).clickable {onClick(index)  })
}