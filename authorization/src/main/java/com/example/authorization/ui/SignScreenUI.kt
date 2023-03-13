package com.example.authorization.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.authorization.R
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.navigation.Screen
import com.example.store.ShopDb
import com.example.store.UserRepositoryImpl
import com.example.uigeneral.component.ErrorDialogUI
import com.example.uigeneral.theme.AppLink
import com.example.uigeneral.theme.MarketPlaceNMTheme


@Composable
fun SignScreenUI(
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current,
    singVM: SignVM = viewModel(factory = SignVM.getProvideFactory(UserRepositoryImpl(ShopDb.getDatabase(context)))),
    modifier: Modifier = Modifier
) {

    val state = singVM.state.collectAsState()

    val error: MutableState<String?> = remember {
        mutableStateOf(null)
    }
    val showAlert = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        singVM.sharedFlowError.collect {
            error.value = it
            showAlert.value = true
        }
    }
    LaunchedEffect(key1 = state.value) {
        if (state.value.isLogin) {
            navController.navigate(Screen.ScreenMenu.Home.route)
        }
    }


    SignScreenUI(state = state.value,
        changeFirstName = singVM::setFirstName,
        changeLastName = singVM::setLastName,
        changeEmail = singVM::setEmail,
        navToLogin = {
            navController.navigate(Screen.Login.route)
        },
        sign = singVM::sign,
        showAlert = showAlert.value,
        error = error.value ?: "Unknow error",
        closeAlert = { showAlert.value = false },
        modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignScreenUI(
    state: SignStateUI, changeFirstName: (fm: String) -> Unit, changeLastName: (lm: String) -> Unit,
    changeEmail: (email: String) -> Unit, navToLogin: () -> Unit, sign: () -> Unit,
    showAlert: Boolean, error: String, closeAlert: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.h5, modifier = Modifier.padding(
                start = 112.dp,
                end = 113.dp, bottom = 60.dp
            )
        )

        if (showAlert)
            ErrorDialogUI(error = error, closeAlert)

        Column(Modifier.padding(start = 42.dp, end = 42.dp)) {
            TextFieldAppUI(
                value = state.firstName,
                changeFirstName,
                placeholder = {
                    Text(
                        text = stringResource(R.string.first_name),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.overline.copy(fontSize = 11.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.padding(bottom = 35.dp)
            )
            TextFieldAppUI(
                value = state.lastName,
                changeLastName,
                placeholder = {
                    Text(
                        text = stringResource(R.string.last_name),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.overline.copy(fontSize = 11.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.padding(bottom = 35.dp)
            )
            TextFieldAppUI(
                value = state.email,
                changeEmail,
                placeholder ={
                    Text(
                        text = stringResource(R.string.email),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.overline.copy(fontSize = 11.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                } ,
                isError = state.emailIsError,
                modifier = Modifier.padding(bottom = 35.dp)
            )

            MainButtonUI(
                stringResource(R.string.sign_in),
                sign,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 70.dp)
            ) {
                Text(
                    text = stringResource(R.string.already_have_acc),
                    style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.onSecondary)
                )
                TextButton(
                    onClick = navToLogin, contentPadding = PaddingValues(0.dp), modifier = Modifier
                        .height(16.dp)
                        .padding(0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        style = MaterialTheme.typography.overline.copy(color = AppLink)
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 38.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.google), contentDescription = null)
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(R.string.sign_with_google), style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.apple), contentDescription = null)
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(R.string.sign_with_apple), style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignScreenUIPreview() {
    MarketPlaceNMTheme() {
        Surface(color = MaterialTheme.colors.background) {
            SignScreenUI()
        }
    }
}