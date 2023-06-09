package com.example.marketplacenm.item.data.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uigeneral.theme.MarketPlaceNMTheme


@Composable
fun MainButtonUI(text:String, onClick:()->Unit, modifier: Modifier=Modifier) {
    Button(onClick = onClick,
        modifier.height(46.dp)
            .clip(RoundedCornerShape(15.dp))) {
        Text(text)
    }
}
@Preview(showBackground = true)
@Composable
fun MainButtonUIPreview() {
    MarketPlaceNMTheme() {
        Row(modifier = Modifier.padding(8.dp)) {
            MainButtonUI("Сlick", {})
        }
    }

}