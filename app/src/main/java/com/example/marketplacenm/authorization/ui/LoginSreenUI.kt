package com.example.marketplacenm.authorization.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreenUI(modifier: Modifier=Modifier){

    Column(modifier=modifier.fillMaxSize()) {

        Text("Welcome back")

        TextFieldAppUI(
            value = "",
            {  },
            placeholder = "First name",
            modifier = Modifier.padding(bottom = 35.dp)
        )

        TextFieldAppUI(
            value = "",
            {  },
            placeholder = "Password",
            modifier = Modifier.padding(bottom = 35.dp)
        )

        MainButtonUI("Sign in", {},  modifier = Modifier.padding(bottom = 15.dp))
    }




}


@Preview(showBackground = true)
@Composable
fun LoginScreenUIPreview(){

    MarketPlaceNMTheme{

        LoginScreenUI()

    }


}