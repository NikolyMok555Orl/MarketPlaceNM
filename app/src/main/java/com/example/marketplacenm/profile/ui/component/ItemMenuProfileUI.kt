package com.example.marketplacenm.profile.ui.component

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marketplacenm.home.ui.component.ItemBrandUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun ItemMenuProfileUI( icon:Int,title:String, modifier: Modifier=Modifier){
    Row(modifier=modifier) {
        Text(text = title)
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemLatestUIPreivew(){
    MarketPlaceNMTheme() {
        ItemMenuProfileUI(0, "Trade store")
    }
}