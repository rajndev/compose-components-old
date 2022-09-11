package com.shivnasoft.compose_components.forms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp

@Composable
fun DropdownMenuField(
    modifier: Modifier = Modifier,
    fieldLabel: String,
    inputVal: String,
    isSingleLine: Boolean = false,
    maxLines: Int = 1,
    isError: Boolean = false,
    errorTextMessage: String = "",
    dropDownList: List<String>,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit
) {
    var mExpanded = rememberSaveable { mutableStateOf(false) }
    var mTextFieldSize = remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column {
        OutlinedTextField(
            value = inputVal,
            onValueChange = { onValueChanged(it) },
            label = { Text(text = fieldLabel) },
            singleLine = isSingleLine,
            maxLines = maxLines,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = {
                Icon(icon, null,
                    Modifier.clickable { mExpanded.value = !mExpanded.value })
            },
            modifier = modifier
          /*  modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize.value = coordinates.size.toSize()
                }
                .then(modifier)*/
        )

        DropdownMenu(
            expanded = mExpanded.value,
            onDismissRequest = { mExpanded.value = false },
            modifier = Modifier.width(200.dp)
            /*modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() })*/
        ) {
            dropDownList.forEach { label ->
                DropdownMenuItem(onClick = {
                    onValueChanged(label)
                    mExpanded.value = false
                }) {
                    Text(text = label)
                }
            }
        }

        if (isError && errorTextMessage.isNotEmpty()) {
            Text(
                text = errorTextMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
            )
        }
    }
}