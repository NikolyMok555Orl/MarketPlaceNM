package com.example.marketplacenm.home.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketplacenm.R
import com.example.marketplacenm.home.data.js.ItemSale
import com.example.marketplacenm.home.ui.component.*
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.navigation.Screen
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun HomeScreenUI(navController: NavController = rememberNavController(), homeVM: HomeVM = viewModel(),
                    modifier: Modifier=Modifier){

    val stateUI=homeVM.state.collectAsState()
    val showHint= remember(stateUI.value) {
        mutableStateOf( stateUI.value.responseSearch.isNotEmpty())
    }
    HomeScreenUI(stateUI.value, homeVM::search,showHint.value, {
        showHint.value=false
    },{
        navController.navigate(Screen.Item.route)
    }, modifier)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenUI(stateUI: HomeStateUI, search:(newQ:String)->Unit, showHint:Boolean, hideHint:()->Unit, onClickSale:(sale: ItemSale)->Unit, modifier: Modifier=Modifier){

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
        
        //Поиск
        Column {


            TextFieldAppUI(value = stateUI.query,
                onTextValueChanged = search,
                placeholder = "What are you looking for ?",
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        hideHint()
                    }
                ))
            if (showHint) {
                DropdownMenu(
                    expanded = showHint,
                    onDismissRequest = { hideHint() },
                    properties = PopupProperties(focusable = false),
                    modifier = Modifier.background(color = MaterialTheme.colors.surface.copy(alpha = 1f))
                ) {
                    stateUI.responseSearch.forEachIndexed { _, s ->
                        Text(text = s, textAlign = TextAlign.Center, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                search(s)
                                hideHint()
                            })
                    }
                }
            }
        }
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
            HomeScreenUI(
                stateUI = HomeStateUI(category = getGroup(), brands = getBrands()),
                search = {},
                showHint = false,
                hideHint = {},
                onClickSale = {})
        }
}



