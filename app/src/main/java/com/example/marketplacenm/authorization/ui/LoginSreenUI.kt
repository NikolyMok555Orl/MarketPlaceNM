package com.example.marketplacenm.authorization.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marketplacenm.R
import com.example.marketplacenm.item.data.component.MainButtonUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI
import com.example.marketplacenm.navigation.Screen
import com.example.marketplacenm.ui.component.ErrorDialogUI
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun LoginScreenUI(
    navController: NavController = rememberNavController(), loginVM: LoginVM = viewModel(),
    modifier: Modifier = Modifier
) {

    val state = loginVM.state.collectAsState()

    val error: MutableState<String?> = remember {
        mutableStateOf(null)
    }
    val showAlert = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        loginVM.sharedFlowError.collect {
            error.value = it
            showAlert.value = true
        }
    }
    LaunchedEffect(key1 = state.value) {
        if (state.value.isLogin) {
            navController.navigate(Screen.ScreenMenu.Home.route)
        }
    }

    LoginScreenUI(
        state = state.value,
        changeFirstName = loginVM::setFirstName,
        changePassword = loginVM::setPassword,
        showAlert = showAlert.value,
        error = error.value ?: "",
        closeAlert = {
            showAlert.value = false
        },
        toLogin = loginVM::login,
        changeShowPass=loginVM::showPass,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreenUI(
    state: LoginStateUI,
    changeFirstName: (fm: String) -> Unit,
    changePassword: (pass: String) -> Unit,
    showAlert: Boolean,
    error: String,
    closeAlert: () -> Unit,
    toLogin: () -> Unit,
    changeShowPass:(newVal:Boolean)->Unit,
    modifier: Modifier = Modifier
) {

    Column(verticalArrangement = Arrangement.Center,modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 42.dp)) {
        if(showAlert)
            ErrorDialogUI(error = error ,closeAlert)
        Text(text = "Welcome back", textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 72.dp))

        TextFieldAppUI(
            value = state.firstName,
            changeFirstName,
            placeholder = "First name",
            modifier = Modifier.padding(bottom = 35.dp)
        )

        TextFieldAppUI(
            value = state.password,
            changePassword,
            placeholder = "Password",
            visualTransformation = if (state.showPass)  VisualTransformation.None else PasswordVisualTransformation() ,
            trailingIcon = {
                Icon(painter = painterResource(id = if (state.showPass) R.drawable.eye else R.drawable.eye_off),
                    contentDescription = null,
                modifier.clickable {
                    changeShowPass(!state.showPass)
                })

            },
            modifier = Modifier.padding(bottom = 35.dp)
        )

        MainButtonUI("Login", toLogin, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp))
    }


}


@Preview(showBackground = true)
@Composable
fun LoginScreenUIPreview() {

    MarketPlaceNMTheme {
        LoginScreenUI(
            state = LoginStateUI(firstName = "", password = ""),
            changeFirstName = {},
            changePassword = {},
            showAlert = false,
            error = "",
            closeAlert = {},
            toLogin = {},
            changeShowPass = {})

    }


}