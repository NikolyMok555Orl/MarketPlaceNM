package com.example.marketplacenm.profile.ui

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.profile.component.ItemMenuProfileUI
import com.example.profile.ProfileStateUI
import com.example.profile.ProfileVM
import com.example.profile.R
import com.example.store.ShopDb
import com.example.store.UserRepositoryImpl
import com.example.uigeneral.component.ErrorDialogUI
import com.example.uigeneral.theme.Border
import com.example.uigeneral.theme.MarketPlaceNMTheme
import kotlinx.coroutines.launch


@Composable
fun ProfileScreenUI(
    navController: NavController = rememberNavController(), context:Context= LocalContext.current,
   // profileVM: ProfileVM = viewModel(factory = ProfileVM.getProvideFactory(UserRepositoryImpl(ShopDb.getDatabase(context)))),
    profileVM: ProfileVM = hiltViewModel(),

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


    ProfileScreenUI(state = state.value, toBack={
                                               // navController.popBackStack()
    }, changePhoto = {
        contract.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
        )
    }, logout = remember(profileVM) {
        {
            profileVM.logout()
            navController.navigate(com.example.navigation.Screen.Sign.route)
        }
    },showAlert=showAlert.value,error=error.value?:"",
        closeAlert = {showAlert.value=false}, modifier = modifier)
}


@Composable
fun ProfileScreenUI(state: ProfileStateUI, toBack:()->Unit, changePhoto: () -> Unit, logout: () -> Unit,
                    showAlert:Boolean, error: String, closeAlert:()->Unit
                    , modifier: Modifier = Modifier) {

    Column(
        modifier= modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if(showAlert)
            ErrorDialogUI(error = error ,closeAlert)
        ProfileHeader(toBack)
        Column(horizontalAlignment = Alignment.CenterHorizontally,modifier= modifier.
            padding(horizontal = 42.dp)) {
            AsyncImage(
                model = if (!state.thisAvatar.isNullOrBlank()) Uri.parse(state.thisAvatar)
                else com.example.uigeneral.R.drawable.avatar, contentDescription = null, modifier = Modifier
                    .clip(CircleShape)
                    .size(61.dp)
                    .border(
                        1.dp, Color(
                            Border.value
                        ), CircleShape
                    ), contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = R.string.change_photo),
                fontWeight = FontWeight.W500,
                fontSize = 8.sp,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .clickable {
                        changePhoto()
                    }
                    .padding(bottom = 17.dp)
            )

            Text(
                text = stringResource(id = R.string.default_name),
                style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
                modifier = Modifier.padding(bottom = 36.dp)

            )
            //TODO добавить нормальную кнопку
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(
                    text =stringResource(R.string.upload_item),
                    style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.SemiBold)
                )
            }
            Column(modifier=Modifier.padding(top = 14.dp)) {
                ItemMenuProfileUI(R.drawable.credit_card, stringResource(R.string.trade_store), {},
                    iconAdd = {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_next),
                            contentDescription = null,
                        )
                    }

                )
                ItemMenuProfileUI(R.drawable.credit_card, stringResource(R.string.payment_method), {}, iconAdd = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_next),
                        contentDescription = null,
                    )
                })
                ItemMenuProfileUI(R.drawable.credit_card, stringResource(R.string.balance), {},
                    iconAdd = {
                        Text(text = "\$ 1593")
                    })
                ItemMenuProfileUI(R.drawable.credit_card, stringResource(R.string.trade_history), {}, iconAdd = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_next),
                        contentDescription = null,
                    )
                })
                ItemMenuProfileUI(R.drawable.group_92, stringResource(R.string.restore_purchase), {},
                    iconAdd = {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_next),
                            contentDescription = null,
                        )
                    })
                ItemMenuProfileUI(R.drawable.help, stringResource(R.string.help), {})
                ItemMenuProfileUI(R.drawable.log_out, stringResource(R.string.log_out), logout)

            }
        }
    }


}


@Composable
fun ProfileHeader(onClickBack: () -> Unit) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()

    ) {
        IconButton(onClick = onClickBack, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "icon back")
        }
        Text(
            text = stringResource(id = R.string.profile),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.h3.copy(fontSize = 15.sp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenUIPreivew() {
    MarketPlaceNMTheme() {
        ProfileScreenUI(ProfileStateUI(),{}, {}, {}, false, "", {})
    }
}