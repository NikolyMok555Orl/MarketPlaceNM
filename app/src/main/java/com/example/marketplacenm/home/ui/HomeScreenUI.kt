package com.example.marketplacenm.home.ui

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketplacenm.R
import com.example.marketplacenm.home.data.js.ItemSale
import com.example.marketplacenm.home.ui.component.*
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.navigation.Screen
import com.example.marketplacenm.profile.ui.ProfileVM
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme



@Composable
fun HomeScreenUI(navController: NavController = rememberNavController(), homeVM: HomeVM = viewModel(),
                    modifier: Modifier=Modifier){

    val stateUI=homeVM.state.collectAsState()

    HomeScreenUI(stateUI.value,{
        navController.navigate(Screen.Item.route)
    }, modifier)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenUI(stateUI: HomeStateUI, onClickSale:(sale: ItemSale)->Unit, modifier: Modifier=Modifier){

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Row() {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.List, contentDescription =null )
            }
            Text(text = "Trade by bata")
            AsyncImage(model = if (!stateUI.user?.avatar.isNullOrBlank()) Uri.parse(stateUI.user?.avatar)
            else R.drawable.avatar, contentDescription = "Ава")
        }

        Row() {
            Text("Location")
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription =null )
        }
        
        
        TextFieldAppUI(value = "", onTextValueChanged = {}, placeholder = "What are you looking for ?")
        //Категори
        LazyRow(){
           items(items = stateUI.category){
               ItemGroupUI(img = it.icon, title = it.title)
           }
        }
        //Latest
        if(stateUI.latests.isNotEmpty() && stateUI.sales.isNotEmpty()) {
            HeaderRowUI("Latest")
            LazyRow() {
                items(items = stateUI.latests) {
                    ItemLatestUI(it)
                }
            }
            HeaderRowUI("Flash Sale")
            LazyRow() {
                items(items = stateUI.sales) {
                    ItemSaleUI(it, onClickSale)
                }
            }
        }
        HeaderRowUI("Brands")
        LazyRow(){
            items(items = stateUI.brands){
                ItemBrandUI(it)
            }
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
            HomeScreenUI(HomeStateUI(category = getGroup(), brands = getBrands()), {})
        }
}



