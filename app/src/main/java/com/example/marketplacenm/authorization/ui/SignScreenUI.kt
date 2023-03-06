package com.example.marketplacenm.authorization.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketplacenm.R
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignScreenUI(modifier: Modifier=Modifier){
    Column(verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally,modifier = modifier.fillMaxSize()) {
           Text(text = "Sign in", style = MaterialTheme.typography.h3, modifier=Modifier.padding(start = 112.dp,
               end = 113.dp, bottom = 60.dp) )

        TextFieldAppUI(value = "", {}, placeholder = "First name", modifier=Modifier.padding(bottom = 35.dp))
        TextFieldAppUI(value = "", {}, placeholder = "Last name", modifier=Modifier.padding(bottom = 35.dp))
        TextFieldAppUI(value = "", {}, placeholder = "Email", modifier=Modifier.padding(bottom = 35.dp))
        MainButtonUI("Sign in", {}, modifier=Modifier.padding(bottom = 15.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier=Modifier.padding(bottom = 70.dp)) {
            Text(text = "Already have an account?")
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Log in")
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier=Modifier.padding(bottom = 38.dp)){
            Icon(painter = painterResource(id = R.drawable.google), contentDescription =null )
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign in with Google")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.apple), contentDescription =null )
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign in with Apple")
            }
        }
    }
}




@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignScreenUIPreview(){
   MarketPlaceNMTheme() {
       Surface(color = MaterialTheme.colors.background) {
           SignScreenUI()
       }
   }
}