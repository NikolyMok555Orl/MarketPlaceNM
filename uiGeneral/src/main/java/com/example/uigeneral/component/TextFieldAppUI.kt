package com.example.marketplacenm.item.data.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uigeneral.theme.MarketPlaceNMTheme
import androidx.compose.foundation.background

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning

@ExperimentalMaterialApi
@Composable
fun TextFieldAppUI(
    value: String,
    onTextValueChanged: (String) -> Unit,
  //  placeholder: String?,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation=VisualTransformation.None,
    keyboardActions: KeyboardActions=KeyboardActions.Default,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    // parameters below will be passed to BasicTextField for correct behavior of the text field,
    // and to the decoration box for proper styling and sizing
    val enabled = true
    val singleLine = true

    // val passwordTransformation = PasswordVisualTransformation()
    BasicTextField(
        value = value,
        onValueChange = onTextValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .height(29.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.surface),
         visualTransformation = visualTransformation,
        // internal implementation of the BasicTextField will dispatch focus events
        interactionSource = interactionSource,
        singleLine = singleLine,
        keyboardActions= keyboardActions ?: KeyboardActions.Default
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            enabled = enabled,
            singleLine = singleLine,
            placeholder = placeholder,
            isError = isError,
            trailingIcon = trailingIcon
                ?: if (isError) {
                    { ErrorEconUI() }
                } else {
                    {}
                },
            leadingIcon = {},
            interactionSource = interactionSource,
            innerTextField = it,
            visualTransformation = visualTransformation,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 14.dp, end = 14.dp, top = 0.dp, bottom = 0.dp
            )
        )
    }
}



@Composable
private fun ErrorEconUI() {
    Icon(Icons.Default.Warning, "error")
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun TextFieldAppUIPreview() {

    MarketPlaceNMTheme() {
        Box(modifier = Modifier.padding(8.dp)) {
            TextFieldAppUI("", {}, { Text(
                text = "Плейсхолдер",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) })
        }
    }

}


