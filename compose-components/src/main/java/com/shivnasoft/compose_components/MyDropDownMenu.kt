package com.shivnasoft.compose_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun MyDropdownMenu(
    modifier: Modifier = Modifier, fieldLabel: String,
    inputVal: String,
    isSingleLine: Boolean = false,
    maxLines: Int = 0,
    isError: Boolean = false,
    isCategoryError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChanged: (String) -> Unit
) {
    var mExpanded = rememberSaveable { mutableStateOf(false) }
    var mTextFieldSize = remember { mutableStateOf(Size.Zero) }
    val mCategories = listOf(
        "Produce",
        "Bakery",
        "Meats",
        "Dairy",
        "Deli",
        "Beverages",
        "Frozen"
    )
    val icon = if (mExpanded.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column {
        Box {
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
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize.value = coordinates.size.toSize()
                    }
                    .then(modifier)
            )
        }

        if (isCategoryError) {
            Text(
                text = "Category is required.",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
            )
        }

        DropdownMenu(
            expanded = mExpanded.value,
            onDismissRequest = { mExpanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.value.width.toDp() })
        ) {
            mCategories.forEach { label ->
                DropdownMenuItem(onClick = {
                    onValueChanged(label)
                    mExpanded.value = false
                }) {
                    Text(text = label)
                }
            }
        }

    }
}