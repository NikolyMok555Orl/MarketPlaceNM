package com.example.marketplacenm.authorization.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun LoginScreenUI(modifier: Modifier=Modifier){

    Column(modifier=modifier.fillMaxSize()) {

        Text("Welcome back")

         TextField(value = "", onValueChange = {},
         placeholder = { Text(text = "First name")})

        TextField(value = "", onValueChange = {},
            placeholder = { Text(text = "First name")})
    }




}


@Preview(showBackground = true)
@Composable
fun LoginScreenUIPreview(){

    MarketPlaceNMTheme{

        LoginScreenUI()

    }


}