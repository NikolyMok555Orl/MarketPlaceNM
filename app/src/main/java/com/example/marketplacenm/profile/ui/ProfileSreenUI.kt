package com.example.marketplacenm.profile.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketplacenm.R
import com.example.marketplacenm.navigation.Screen
import com.example.marketplacenm.profile.ui.component.ItemMenuProfileUI
import com.example.marketplacenm.ui.component.ErrorDialogUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme
import kotlinx.coroutines.launch


@Composable
fun ProfileScreenUI(
    navController: NavController = rememberNavController(), profileVM: ProfileVM = viewModel(),
    modifier: Modifier = Modifier
) {
    val state=profileVM.state.collectAsState()
    val scope = rememberCoroutineScope()
    val error:MutableState<String?> = remember {
        mutableStateOf(null)
    }
    val showAlert= remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        profileVM.sharedFlowError.collect {
            error.value=it
            showAlert.value=true
        }
    }

    val context = LocalContext.current

    val contract =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(
                    uri ?: return@rememberLauncherForActivityResult, flag
                )
                scope.launch {
                     profileVM.changeAvatar(uri.toString())
                }
            })


    ProfileScreenUI(state = state.value, changePhoto = {
        contract.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
        )
    }, logout = remember(profileVM) {
        {
            profileVM.logout()
            navController.navigate(Screen.Sign.route)
        }
    },showAlert=showAlert.value,error=error.value?:"",
        closeAlert = {showAlert.value=false}, modifier = modifier)
}


@Composable
fun ProfileScreenUI(state: ProfileStateUI, changePhoto: () -> Unit, logout: () -> Unit,
                    showAlert:Boolean, error: String, closeAlert:()->Unit
    ,modifier: Modifier = Modifier) {

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if(showAlert)
            ErrorDialogUI(error = error ,closeAlert)
        Row(modifier = Modifier.fillMaxWidth()) {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "toBack")
            }
            Text("Profile")
        }

        AsyncImage(model = if (!state.thisAvatar.isNullOrBlank())Uri.parse(state.thisAvatar)
        else R.drawable.avatar, contentDescription = null, modifier = Modifier
            .clip(CircleShape)
            .size(61.dp)
            .border(1.dp, Color(0xFFC0C0C0
        ), CircleShape), contentScale = ContentScale.Crop)
        Text(
            text = "Change photo",
            fontWeight = FontWeight.W500,
            fontSize = 8.sp,
            modifier = Modifier.clickable {
                changePhoto()
            }
        )
        //TODO добавить нормальную кнопку
        Button(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text(text = "Upload item")
        }

        ItemMenuProfileUI(0, "Trade store", {})
        ItemMenuProfileUI(0, "Payment method", {})
        ItemMenuProfileUI(0, "Balance", {})
        ItemMenuProfileUI(0, "Trade history", {})
        ItemMenuProfileUI(0, "Restore Purchase", {})
        ItemMenuProfileUI(0, "Help", {})
        ItemMenuProfileUI(0, "Log out", logout)


    }


}


@Preview(showBackground = true)
@Composable
private fun ProfileScreenUIPreivew() {
    MarketPlaceNMTheme() {
        ProfileScreenUI()
    }
}