package com.example.marketplacenm.authorization.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.navigation.Screen
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme




@Composable
fun LoginScreenUI(navController: NavController = rememberNavController(), loginVM:LoginVM= viewModel(),
                  modifier: Modifier=Modifier){


    LoginScreenUI({navController.navigate(Screen.ScreenMenu.Home.route)},modifier)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreenUI(toLogin:()->Unit,modifier: Modifier=Modifier){

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

        MainButtonUI("Login", toLogin,  modifier = Modifier.padding(bottom = 15.dp))
    }




}


@Preview(showBackground = true)
@Composable
fun LoginScreenUIPreview(){

    MarketPlaceNMTheme{

        LoginScreenUI()

    }


}