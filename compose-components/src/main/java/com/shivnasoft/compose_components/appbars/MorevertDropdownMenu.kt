package com.shivnasoft.compose_components.appbars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MorevertDropdownMenu(
    dropDownList: List<String> = listOf(),
    dropDownListMap: Map<Any, String> = mapOf(),
    dropDownListRoundedCornerAmount: Dp = 0.dp,
    onMenuItemClick: (Any?, String) -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }
    Column {
        IconButton(onClick = {
            showMenu.value = !showMenu.value
        }) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "Overflow menu",
            )
        }
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(
                medium = RoundedCornerShape(
                    dropDownListRoundedCornerAmount
                )
            )
        ) {
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false }
            ) {
                when {
                    dropDownList != null -> {
                        dropDownList.forEach { label ->
                            DropdownMenuItem(onClick = {
                                onMenuItemClick(null, label)
                                showMenu.value = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                    dropDownListMap != null -> {
                        dropDownListMap.forEach { (key, value) ->
                            DropdownMenuItem(onClick = {
                                onMenuItemClick(key, value)
                                showMenu.value = false
                            }) {
                                Text(text = value)
                            }
                        }
                    }
                }

                when {
                    dropDownList.isNotEmpty() -> {
                        dropDownList.forEach { label ->
                            DropdownMenuItem(onClick = {
                                onMenuItemClick(null, label)
                                showMenu.value = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                    dropDownListMap.isNotEmpty() -> {
                        dropDownListMap.forEach { (key, value) ->
                            DropdownMenuItem(onClick = {
                                onMenuItemClick(key, value)
                                showMenu.value = false
                            }) {
                                Text(text = value)
                            }
                        }
                    }
                }

            }
        }
    }
}