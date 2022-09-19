package com.shivnasoft.compose_components.forms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropdownMenuOutlinedTextField(
    containerModifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    fieldLabel: String,
    inputVal: String,
    isSingleLine: Boolean = false,
    maxLines: Int = 1,
    isError: Boolean = false,
    errorTextMessage: String = "",
    dropDownList: List<String> = listOf(),
    dropDownListMap: Map<Any, String> = mutableMapOf(),
    dropDownListRoundedCornerAmount: Dp = 0.dp,
    onValueChanged: (Any?, String) -> Unit
) {
    var mExpanded = rememberSaveable { mutableStateOf(false) }
    var mTextFieldSize = remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(modifier = containerModifier) {
        OutlinedTextField(
            value = inputVal,
            onValueChange = { },
            label = { Text(text = fieldLabel) },
            singleLine = isSingleLine,
            maxLines = maxLines,
            isError = isError,
            trailingIcon = {
                Icon(icon, null,
                    Modifier.clickable { mExpanded.value = !mExpanded.value })
            },
            //modifier = modifier,
            readOnly = true,
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize.value = coordinates.size.toSize()
                }
                .then(fieldModifier)
        )

        MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(dropDownListRoundedCornerAmount))) {
            DropdownMenu(
                expanded = mExpanded.value,
                onDismissRequest = { mExpanded.value = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() })
            ) {
                when {
                    dropDownList.isNotEmpty() -> {
                        dropDownList.forEach { label ->
                            DropdownMenuItem(onClick = {
                                onValueChanged(null, label)
                                mExpanded.value = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                    dropDownListMap.isNotEmpty() -> {
                        dropDownListMap.forEach { (key, value) ->
                            DropdownMenuItem(onClick = {
                                onValueChanged(key, value)
                                mExpanded.value = false
                            }) {
                                Text(text = value)
                            }
                        }
                    }
                    /*dropDownListMutableMap.isNotEmpty() -> {
                        dropDownListMutableMap.forEach { (key, value) ->
                            DropdownMenuItem(onClick = {
                                onValueChanged(key, value)
                                mExpanded.value = false
                            }) {
                                Text(text = value)
                            }
                        }
                    }*/
                }
            }
        }

        if (isError && errorTextMessage.isNotEmpty()) {
            Text(
                text = errorTextMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}