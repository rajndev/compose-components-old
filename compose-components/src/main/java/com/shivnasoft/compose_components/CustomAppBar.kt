package com.shivnasoft.compose_components

import androidx.compose.foundation.clickable

import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    backGroundColor: Color = Color.White,
    actions: @Composable () -> Unit = { },
    navController: NavController,
    scaffoldState: ScaffoldState? = null,
) {
    val scope = rememberCoroutineScope()

    SmallTopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = backGroundColor
        ),
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {   IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
        {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Nav drawer icon",
                modifier = Modifier.clickable(onClick = {
                    scope.launch {
                        scaffoldState?.drawerState?.open()
                    }
                })
            )
        }

        },
        actions = {
            actions()
        }
    )
}