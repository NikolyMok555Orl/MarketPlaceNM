package com.example.marketplacenm.item.ui

import android.widget.RatingBar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.example.marketplacenm.home.ui.HomeVM
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.ui.component.ColorItemUI
import com.example.marketplacenm.item.ui.component.ShortImageUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun ItemScreenUI(
    navController: NavController = rememberNavController(), itemVM: ItemVM = viewModel(),
    modifier: Modifier = Modifier
) {

    val state = itemVM.state.collectAsState()

    ItemScreenUI(state.value,itemVM::selectImage, itemVM::selectColor, { navController.popBackStack() }, modifier)
}

@Composable
fun ItemScreenUI(
    itemStateUI: ItemStateUI, onClickImage:(index:Int)->Unit,onClickColor:(index:Int)->Unit, toBack: () -> Unit,  modifier: Modifier = Modifier
) {
    itemStateUI.detailsItem?.let { item ->
        Column(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.size(328.dp, 279.dp)) {
                AsyncImage(
                    model = item.image_urls[itemStateUI.selectImage ?: 0],
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(onClick = toBack, modifier = Modifier.align(Alignment.TopStart)) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "to back")
                }

            }
            Row() {
                item.image_urls.forEachIndexed { index, img ->
                    ShortImageUI(index, img, index == (itemStateUI.selectImage ?: 0), onClickImage)
                }
            }
            Row() {

                Text(text = item.name)
                Text(text = "\$ ${item.price}")
            }
            Text(text = item.description)
            //Рейтинг
            Row() {
                AsyncImage(model =  R.drawable.star, contentDescription = "star", modifier = Modifier.size(10.dp))
                Text(text = item.rating.toString(), style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600,
                    fontSize = 9.sp)
                Text(text = "(${item.number_of_reviews} reviews)" ,
                    style = MaterialTheme.typography.h2, fontWeight = FontWeight.W400, fontSize = 9.sp)
            }
            Text(text = "Color:")
            Row {
                item.colors.forEachIndexed { index, color ->
                    ColorItemUI(index = index, color = Color(color.toColorInt()) , isSelect = index == (itemStateUI.selectColor
                        ?: 0), onClick = onClickColor)
                }
            }
            Surface {
                Row() {
                    Column() {
                        Text("Quantity: ")
                        Row() {
                            MainButtonUI(text = "-", onClick = { /*TODO*/ })
                            MainButtonUI(text = "+", onClick = { /*TODO*/ })


                        }
                    }

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "ADD TO CART")
                    }
                }
            }


        }
    } ?: run {
        Box(contentAlignment = Alignment.Center
            ,modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }

    }


}


@Preview(showBackground = true)
@Composable
private fun ItemScreenUIPreivew() {
    MarketPlaceNMTheme() {
        ItemScreenUI(ItemStateUI(), {}, {}, {})
    }
}