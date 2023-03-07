package com.example.marketplacenm.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.profile.ui.ProfileVM
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme



@Composable
fun HomeScreenUI(navController: NavController = rememberNavController(), homeVM: HomeVM = viewModel(),
                    modifier: Modifier=Modifier){


    HomeScreenUI(modifier)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenUI(modifier: Modifier=Modifier){

    Column(modifier = modifier.fillMaxSize()) {
        Row() {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.List, contentDescription =null )
            }
            Text(text = "Trade by bata")

            AsyncImage(model = "", contentDescription = "Ава")
        }

        Row() {
            Text("Location")
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription =null )
        }
        
        
        TextFieldAppUI(value = "", onTextValueChanged = {}, placeholder = "What are you looking for ?")
        //Категори
        LazyRow(){


        }
        //Latest
        HeaderRowUI("Latest")
        LazyRow(){

        }
        HeaderRowUI("Flash Sale")
        LazyRow(){

        }

        HeaderRowUI("Brands")
        LazyRow(){

        }



    }




}

@Composable
fun HeaderRowUI(title: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = title)
        Text(text = "View all")
    }
}










@Preview(showBackground = true)
@Composable
private fun HomeScreenUIPreivew(){
        MarketPlaceNMTheme() {
            HomeScreenUI()
        }
}



