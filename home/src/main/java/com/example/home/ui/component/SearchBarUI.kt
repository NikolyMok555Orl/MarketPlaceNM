package com.example.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.home.R
import com.example.home.ui.HomeStateUI
import com.example.marketplacenm.item.data.component.TextFieldAppUI

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SearchBarUI(
    stateUI: HomeStateUI,
    search: (newQ: String) -> Unit,
    hideHint: () -> Unit,
    showHint: Boolean
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 9.dp, bottom = 17.dp, start = 58.dp, end = 58.dp)
    ) {
        TextFieldAppUI(value = stateUI.query,
            onTextValueChanged = search,
            placeholder = {
                Text(
                    text = stringResource(
                        R.string.search_hint
                    ),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.overline.copy(
                        fontSize = 9.sp, fontFamily = FontFamily(
                            Font(com.example.uigeneral.R.font.poppins)
                        )
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    hideHint()
                }
            ))
        if (showHint) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 56.dp)) {
                DropdownMenu(
                    expanded = showHint,
                    onDismissRequest = { hideHint() },
                    properties = PopupProperties(focusable = false),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.surface.copy(alpha = 1f))
                ) {
                    stateUI.responseSearch.forEachIndexed { _, s ->
                        Text(text = s, textAlign = TextAlign.Center, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                search(s)
                                hideHint()
                            })
                    }
                }
            }
        }
    }
}