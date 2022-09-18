package com.shivnasoft.compose_components.appbars

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MorevertDropdownMenu(
    dropDownList: List<String>? = null,
    dropDownListMap: Map<Any, String>? = null,
    dropDownListRoundedCornerAmount: Dp = 0.dp,
    onMenuItemClick: (Any?, String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = {
        showMenu = !showMenu
    }) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "Overflow menu",
        )
    }
    MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(dropDownListRoundedCornerAmount))) {
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            when {
                dropDownList != null -> {
                    dropDownList.forEach { label ->
                        DropdownMenuItem(onClick = {
                            onMenuItemClick(null, label)
                            showMenu = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
                dropDownListMap != null -> {
                    dropDownListMap.forEach { (key, value) ->
                        DropdownMenuItem(onClick = {
                            onMenuItemClick(key, value)
                            showMenu = false
                        }) {
                            Text(text = value)
                        }
                    }
                }
            }
        }
    }
}