package com.example.marketplacenm.item.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketplacenm.R
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.ui.component.ColorItemUI
import com.example.marketplacenm.item.ui.component.ShortImageUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemScreenUI(
    navController: NavController = rememberNavController(), itemVM: ItemVM = viewModel(),
    modifier: Modifier = Modifier
) {

    val state = itemVM.state.collectAsState()
    val statePager = rememberPagerState()
    val scope = rememberCoroutineScope()

    ItemScreenUI(
        state.value,
        statePager,
        {
            scope.launch {
                statePager.scrollToPage(it)
            }
        },
        itemVM::selectColor,
        itemVM::changeQuantity,
        { navController.popBackStack() },
        modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemScreenUI(
    itemStateUI: ItemStateUI,
    statePager: PagerState,
    onClickImage: (index: Int) -> Unit,
    onClickColor: (index: Int) -> Unit,
    changeQuantity:(q:Double)->Unit,
    toBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    itemStateUI.detailsItem?.let { item ->
        Column(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.size(328.dp, 279.dp)) {
                HorizontalPager(
                    count = item.image_urls.size, state = statePager, modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = item.image_urls[it],
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                IconButton(onClick = toBack, modifier = Modifier.align(Alignment.TopStart)) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "to back")
                }
                Surface(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Column {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_like), contentDescription = null)
                        }
                        Spacer(modifier = Modifier.width(12.dp)
                            .height(1.dp)
                            .background(
                                MaterialTheme.colors.primary
                            ))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_share), contentDescription = null)
                        }
                        
                    }
                    
                    
                }
                
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxWidth()) {
                itemStateUI.detailsItem.image_urls.forEachIndexed { index, _ ->
                    ShortImageUI(index, item.image_urls[index], statePager.currentPage == index, onClickImage)
                }

            }

            Row() {

                Text(text = item.name)
                Text(text = "\$ ${item.price}")
            }
            Text(text = item.description)
            //Рейтинг
            Row() {
                AsyncImage(
                    model = R.drawable.star,
                    contentDescription = "star",
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = item.rating.toString(),
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.W600,
                    fontSize = 9.sp
                )
                Text(
                    text = "(${item.number_of_reviews} reviews)",
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.W400,
                    fontSize = 9.sp
                )
            }
            Text(text = "Color:")
            Row {
                item.colors.forEachIndexed { index, color ->
                    ColorItemUI(
                        index = index,
                        color = Color(color.toColorInt()),
                        isSelect = index == (itemStateUI.selectColor
                            ?: 0),
                        onClick = onClickColor
                    )
                }
            }
            Surface {
                Row() {
                    Column() {
                        Text("Quantity: ")
                        Row() {
                            MainButtonUI(text = "-", onClick ={ changeQuantity(-1.0)})
                            MainButtonUI(text = "+", onClick = { changeQuantity(1.0)})
                        }
                    }

                    Button(onClick = {
                        if(itemStateUI.quantity>0){
                            changeQuantity(-1*itemStateUI.quantity)
                        }else{
                            changeQuantity(1.0)
                        }
                    }) {
                        Row(horizontalArrangement = Arrangement.SpaceAround,modifier=Modifier.fillMaxWidth()) {
                            Text(text = "# ${itemStateUI.getSum()}")
                            Text(text = "ADD TO CART")
                        }
                    }
                }
            }


        }
    } ?: run {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

    }


}


@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun ItemScreenUIPreivew() {
    MarketPlaceNMTheme() {
        ItemScreenUI(ItemStateUI(), rememberPagerState(), {}, {}, {}, {})
    }
}