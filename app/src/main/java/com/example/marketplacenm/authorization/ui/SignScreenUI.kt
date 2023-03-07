package com.example.marketplacenm.authorization.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marketplacenm.LOGIN
import com.example.marketplacenm.R
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun SignScreenUI(navController: NavController=rememberNavController(), singVM:SignVM= viewModel(), modifier: Modifier=Modifier){

    SignScreenUI({
        navController.navigate(LOGIN)
    },modifier)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignScreenUI(navToLogin:()->Unit,modifier: Modifier=Modifier){
    val firs_name= remember {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
           Text(text = "Sign in", style = MaterialTheme.typography.h5.copy(fontSize = 26.sp, letterSpacing = -(0.3).sp ), modifier=Modifier.padding(start = 112.dp,
               end = 113.dp, bottom = 60.dp) )

        Column(Modifier.padding(start = 42.dp, end=42.dp)) {
            TextFieldAppUI(
                value = firs_name.value,
                { firs_name.value = it },
                placeholder = "First name",
                modifier = Modifier.padding(bottom = 35.dp)
            )
            TextFieldAppUI(
                value = "",
                {},
                placeholder = "Last name",
                modifier = Modifier.padding(bottom = 35.dp)
            )
            TextFieldAppUI(
                value = "",
                {},
                placeholder = "Email",
                modifier = Modifier.padding(bottom = 35.dp)
            )

            MainButtonUI("Sign in", {},  modifier = Modifier.padding(bottom = 15.dp))


        Row( verticalAlignment = Alignment.CenterVertically,modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 70.dp)) {
            Text(text = "Already have an account?", style=MaterialTheme.typography.overline.copy(color =MaterialTheme.colors.onSecondary))
            TextButton(onClick =navToLogin, contentPadding = PaddingValues(0.dp), modifier = Modifier
                .height(16.dp)
                .padding(0.dp)) {
                Text(text = "Log in", style=MaterialTheme.typography.overline)
            }
        }
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier=Modifier.padding(bottom = 38.dp)){
            Icon(painter = painterResource(id = R.drawable.google), contentDescription =null )
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign in with Google", style=MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.Normal))
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.apple), contentDescription =null )
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign in with Apple", style=MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.Normal))
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