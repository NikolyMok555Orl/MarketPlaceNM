package com.example.marketplacenm.item.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun ItemScreenUI(modifier: Modifier=Modifier){
    Column(modifier=modifier.fillMaxSize()){
        Box() {
            AsyncImage(model = "", contentDescription = "")
        }
        Row() {
            AsyncImage(model = "", contentDescription = "")
        }

        Row(){

            Text(text = "New balance Sneakers")
            Text(text = "\$ 22,50")
        }
        Text(text = "Features waterproof, fire, air resistant shoes. all changed when the country of fire attacked")
        //Рейтинг
        Row(){


        }
        Text(text ="Color:")

        Row {

            Spacer(modifier =Modifier)
        }



        Surface {
            Row() {
                Column() {
                    Text("Quantity: ")
                    Row(){
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



}





@Preview(showBackground = true)
@Composable
private fun ItemScreenUIPreivew(){
    MarketPlaceNMTheme() {
        ItemScreenUI()
    }
}