package com.example.marketplacenm.profile.ui

import android.graphics.drawable.BitmapDrawable
import android.provider.ContactsContract.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketplacenm.authorization.ui.LoginVM
import com.example.marketplacenm.profile.ui.component.ItemMenuProfileUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun ProfileScreenUI(navController: NavController = rememberNavController(), profileVM: ProfileVM = viewModel(),
                  modifier: Modifier=Modifier){


    ProfileScreenUI(modifier)
}


@Composable
fun ProfileScreenUI(modifier: Modifier=Modifier){

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

        Row(modifier = Modifier.fillMaxWidth()){

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "toBack")
            }

            Text("Profile")
        }


        AsyncImage(model="", contentDescription = null )
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Change photo")
        }
        //TODO добавить нормальную кнопку
        Button(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text(text = "Upload item")
        }

        ItemMenuProfileUI(0, "Trade store")
        ItemMenuProfileUI(0, "Payment method")
        ItemMenuProfileUI(0, "Balance")
        ItemMenuProfileUI(0, "Trade history")
        ItemMenuProfileUI(0, "Restore Purchase")
        ItemMenuProfileUI(0, "Help")
        ItemMenuProfileUI(0, "Log out")




    }


}





@Preview(showBackground = true)
@Composable
private fun ProfileScreenUIPreivew(){
    MarketPlaceNMTheme() {
        ProfileScreenUI()
    }
}