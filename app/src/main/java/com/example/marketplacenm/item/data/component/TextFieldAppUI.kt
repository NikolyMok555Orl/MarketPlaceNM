package com.example.marketplacenm.item.data.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.PasswordVisualTransformation

@ExperimentalMaterialApi
@Composable
fun TextFieldAppUI(
    value: String,
    onTextValueChanged: (String) -> Unit,
    placeholder: String?,

    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    // parameters below will be passed to BasicTextField for correct behavior of the text field,
    // and to the decoration box for proper styling and sizing
    val enabled = true
    val singleLine = true
    val passwordTransformation = PasswordVisualTransformation()
    BasicTextField(
        value = value,
        onValueChange = onTextValueChanged,
        modifier = modifier.size(width = 289.dp, height = 29.dp).clip(RoundedCornerShape(15.dp)).background(MaterialTheme.colors.surface),
       // visualTransformation = passwordTransformation,
        // internal implementation of the BasicTextField will dispatch focus events
       // interactionSource = interactionSource,
        singleLine = singleLine
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value=value,
            enabled=enabled,
            singleLine = singleLine,
            placeholder = {if(placeholder!=null) Text(text = placeholder, textAlign = TextAlign.Center,modifier= modifier.fillMaxWidth())} ,
            interactionSource = interactionSource,
            innerTextField = it,
            visualTransformation = VisualTransformation.None,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 14.dp, end = 14.dp, top = 0.dp, bottom = 0.dp
            )
        )
    }


}


@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun TextFieldAppUIPreview() {

    MarketPlaceNMTheme() {
        Box(modifier = Modifier.padding(8.dp)) {
            TextFieldAppUI("", {}, "Плейсхолдер")
        }
    }

}


