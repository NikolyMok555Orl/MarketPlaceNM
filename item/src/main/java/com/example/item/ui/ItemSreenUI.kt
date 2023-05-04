package com.example.item.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.item.R
import com.example.item.ui.component.ColorItemUI
import com.example.item.ui.component.ShortImageUI
import com.example.remote.api.Api
import com.example.remote.js.DetailsItem
import com.example.remote.repository.AppNetworkRepo
import com.example.uigeneral.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemScreenUI(
    navController: NavController = rememberNavController(),
    itemVM: ItemVM = hiltViewModel(),
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
    changeQuantity: (q: Double) -> Unit,
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
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(9.dp))
                    )
                }
                IconButton(onClick = toBack, modifier = Modifier.align(Alignment.TopStart)) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "to back")
                }
                Column(
                    modifier = Modifier
                        .offset(x = 18.dp)
                        .padding(bottom = 28.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Surface(
                        color = AppIconButton,
                        modifier = Modifier
                            .size(width = 42.dp, height = 95.dp)
                            .clip(RoundedCornerShape(14.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_like),
                                    contentDescription = null,
                                    tint=MaterialTheme.colors.primaryVariant,
                                    modifier = Modifier.size(width = 15.dp, height = 13.dp)
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .width(12.dp)
                                    .height(1.dp)
                                    .background(
                                        MaterialTheme.colors.primary
                                    )
                            )
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                                    contentDescription = null,
                                    tint=MaterialTheme.colors.primaryVariant,
                                    modifier = Modifier.size(width = 16.dp, height = 18.dp)
                                )
                            }

                        }
                    }
                }

            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 35.dp, end = 24.dp, start = 24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .fillMaxWidth()
                ) {
                    itemStateUI.detailsItem.image_urls.forEachIndexed { index, _ ->
                        ShortImageUI(
                            index,
                            item.image_urls[index],
                            statePager.currentPage == index,
                            onClickImage
                        )
                    }

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 21.dp)
                ) {

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h2,
                        fontWeight = FontWeight.W600,
                        fontSize = 17.sp,
                        color = DetailsTitle
                    )
                    Text(
                        text = "\$ ${item.price}",
                        style = MaterialTheme.typography.h2,
                        fontWeight = FontWeight.W600,
                        fontSize = 13.sp,
                        color = DetailsTitle
                    )
                }
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.W400,
                    fontSize = 9.sp,
                    color = MaterialTheme.colors.onSecondary
                )
                //Рейтинг
                Row(modifier = Modifier.padding(top = 9.dp, end = 13.dp)) {
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
                        text = "(${item.number_of_reviews} ${stringResource(R.string.reviews)})",
                        style = MaterialTheme.typography.h2,
                        fontWeight = FontWeight.W400,
                        fontSize = 9.sp
                    )
                }
                Text(text = stringResource(R.string.color), style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.W600, fontSize = 10.sp, color = DetailsColor, modifier = Modifier.padding(end = 11.dp))
                Row(modifier = Modifier.padding(end = 20.dp)) {
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
            }
            BottonItemToCart(changeQuantity, itemStateUI)


        }
    } ?: run {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

    }


}

@Composable
private fun BottonItemToCart(
    changeQuantity: (q: Double) -> Unit,
    itemStateUI: ItemStateUI
) {
    Surface(
        color = Color
            (0xFF181726), modifier = Modifier
            .clip(
                RoundedCornerShape(topStart = 9.dp, topEnd = 9.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = 25.dp)) {
                Text(
                    stringResource(R.string.quantity),
                    style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSecondary,
                    fontSize = 9.sp, modifier = Modifier.padding(top = 14.dp)
                )
                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        onClick = { changeQuantity(-1.0) },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .width(38.dp)
                            .height(22.dp)

                    ) {
                        Text(
                            "-",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        onClick = { changeQuantity(1.0) },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .width(38.dp)
                            .height(22.dp)
                    ) {
                        Text(
                            "+",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
            Row(modifier = Modifier
                .weight(1f)
                .padding(top = 19.dp, bottom = 22.dp)) {
                Button(
                    onClick = {
                        if (itemStateUI.quantity > 0) {
                            changeQuantity(-1 * itemStateUI.quantity)
                        } else {
                            changeQuantity(1.0)
                        }
                    }, modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .width(170.dp)
                        .height(44.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "# ${itemStateUI.getSum()}",
                            color = AddToCartCount, style = MaterialTheme.typography.h2,
                            fontWeight = FontWeight.W600, fontSize = 8.sp
                        )
                        Text(
                            text = stringResource(id = R.string.addCart),
                            style = MaterialTheme.typography.h2,
                            fontWeight = FontWeight.W600,
                            fontSize = 8.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun ItemScreenUIPreivew() {
    MarketPlaceNMTheme() {
        ItemScreenUI(
            ItemStateUI(
           DetailsItem(
                listOf(),
                "Features waterproof, fire, air resistant shoes. all changed when the country of fire attacked",
                listOf(), "New balance Sneakers", 4000, 22.50, 4.3
            ), null, 2.0
        ),
            rememberPagerState(), {}, {}, {}, {})
    }
}