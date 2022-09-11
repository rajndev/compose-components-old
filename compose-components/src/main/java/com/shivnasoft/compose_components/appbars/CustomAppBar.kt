package com.shivnasoft.compose_components.appbars

import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun CustomAppBar(
    title: String,
    titleModifier: Modifier = Modifier,
    titleColor: Color = Color.Black,
    titleFontSize: TextUnit = TextUnit.Unspecified,
    titleTextStyle: TextStyle = MaterialTheme.typography.h6,
    backgroundColor: Color = Color.White,
    appBarElevation: Dp = Dp.Unspecified,
    actions: @Composable () -> Unit = { },
    navController: NavController? = null,
    scaffoldState: ScaffoldState? = null,
) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = titleModifier,
                fontSize = titleFontSize,
                style = titleTextStyle,
                color = titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = backgroundColor,
        elevation = appBarElevation,
        navigationIcon =
        {
            when {
                navController?.previousBackStackEntry != null -> {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }

                scaffoldState != null -> {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Navigation drawer"
                        )
                    }
                }
            }
        },
        actions = {
            actions()
        }
    )

    /* SmallTopAppBar(
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
     )*/
}