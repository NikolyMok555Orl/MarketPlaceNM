package com.example.home.ui

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.home.R
import com.example.home.ui.component.*
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.navigation.Screen
import com.example.remote.api.Api
import com.example.remote.api.ApiApp
import com.example.remote.repository.AppNetworkRepo
import com.example.store.ShopDb
import com.example.store.UserRepositoryImpl
import com.example.uigeneral.theme.GreyBorder
import com.example.uigeneral.theme.MarketPlaceNMTheme


@Composable
fun HomeScreenUI(
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current,
   /* homeVM: HomeVM = viewModel(factory = HomeVM
        .getProvideFactory(AppNetworkRepo(Api.get()), UserRepositoryImpl(ShopDb.getDatabase(context)))),*/
    homeVM: HomeVM = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val stateUI = homeVM.state.collectAsState()
    val showHint = remember(stateUI.value) {
        mutableStateOf(stateUI.value.responseSearch.isNotEmpty())
    }
    HomeScreenUI(stateUI.value, homeVM::search, showHint.value, {
        showHint.value = false
    }, {
        navController.navigate(Screen.Item.route)
    }, modifier)
}


@Composable
fun HomeScreenUI(
    stateUI: HomeStateUI,
    search: (newQ: String) -> Unit,
    showHint: Boolean,
    hideHint: () -> Unit,
    onClickSale: (sale: com.example.remote.js.ItemSale) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 47.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(R.drawable.gamburger),
                        contentDescription = "gamburger"
                    )
                }
            }
            Row() {


                Text(
                    "Trade By ",
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    "Data",
                    style = MaterialTheme.typography.h6.copy(fontSize = 20.sp),
                    color = MaterialTheme.colors.primary
                )
            }

            AsyncImage(
                model = if (!stateUI.user?.avatar.isNullOrBlank()) Uri.parse(stateUI.user?.avatar)
                else com.example.uigeneral.R.drawable.avatar, contentDescription = "Ава", Modifier
                    .clip(CircleShape)
                    .border(1.dp, GreyBorder, CircleShape)
                    .size(31.dp),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, end = 37.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Location",
                    style = MaterialTheme.typography.overline.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = 10.sp,
                        lineHeight = 15.sp
                    ),
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
        //Поиск
        SearchBarUI(stateUI, search, hideHint, showHint)
        //Категории
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp), contentPadding = PaddingValues(end = 21.dp)
        ) {
            items(items = stateUI.category) {
                ItemGroupUI(img = it.icon, title = it.title)
            }
        }
        //Latest
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 11.dp, top = 29.dp)
        ) {
            if (stateUI.latests.isNotEmpty() && stateUI.sales.isNotEmpty()) {
                HeaderRowUI(stringResource(R.string.latest))
                LazyRow() {
                    items(items = stateUI.latests) {
                        ItemLatestUI(it)
                    }
                }
                HeaderRowUI(stringResource(R.string.flash_sale), modifier=Modifier.padding(top=17.dp))
                LazyRow() {
                    items(items = stateUI.sales) {
                        ItemSaleUI(it, onClickSale)
                    }
                }
            }
            HeaderRowUI(stringResource(R.string.brands), modifier=Modifier.padding(top=17.dp))
            LazyRow() {
                items(items = stateUI.brands) {
                    ItemBrandUI(it)
                }
            }
        }


    }


}



@Composable
fun HeaderRowUI(title: String, modifier: Modifier=Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.h6)
        Text(text = stringResource(
            R.string.view_all
        ), style = MaterialTheme.typography.overline)
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderRowUIPreview() {
    MarketPlaceNMTheme() {
        HeaderRowUI("Latest")
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenUIPreivew() {
    MarketPlaceNMTheme() {
        HomeScreenUI(
            stateUI = HomeStateUI(category = getGroup(), brands = getBrands()),
            search = {},
            showHint = false,
            hideHint = {},
            onClickSale = {})
    }
}



