package com.example.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profile.R
import com.example.uigeneral.theme.MarketPlaceNMTheme


@Composable
fun ItemMenuProfileUI(
    icon: Int, title: String, onClick: () -> Unit,
    iconAdd: @Composable (() -> Unit?)? = null,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .clickable { onClick() }
        .fillMaxWidth()
        .padding(bottom = 25.dp)) {
        Icon(
            painter = painterResource(id = icon), contentDescription = null,
            modifier
                .clip(
                    CircleShape
                )
                .size(40.dp)
                .background(
                    Color(0xFFEEEFF4)
                )
                .padding(10.dp)
        )
        Text(text = title, style = MaterialTheme.typography.body1, modifier = Modifier.padding(start = 6.dp))
        if (iconAdd != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = modifier.weight(1f)
            ) {
                iconAdd()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ItemLatestUIPreivew() {
    MarketPlaceNMTheme() {
        ItemMenuProfileUI(R.drawable.credit_card, "Trade store", {}, {})
    }
}