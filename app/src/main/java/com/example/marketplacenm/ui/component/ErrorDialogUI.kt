package com.example.marketplacenm.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme


@Composable
fun ErrorDialogUI(error: String, onClose: () -> Unit) {
    val openDialog = remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(text = "Error!")
        },
        text = {
            Text(error)
        },
        confirmButton = {
            Button(
                onClick = {
                    onClose()
                }) {
                Text("OK")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewErrorDialog() {
    MarketPlaceNMTheme() {
        ErrorDialogUI("Test", {})
    }
}