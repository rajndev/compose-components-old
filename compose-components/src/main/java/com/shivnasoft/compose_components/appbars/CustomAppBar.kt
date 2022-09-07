package com.shivnasoft.compose_components.appbars

import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    titleContentColor: Color = Color.Black,
    titleModifier: Modifier = Modifier,
    titleFontSize: TextUnit = TextUnit.Unspecified,
    containerBackGroundColor: Color = Color.White,
    appBarElevation: Dp = Dp.Unspecified,
    actions: @Composable () -> Unit = { },
    navController: NavController? = null,
    scaffoldState: ScaffoldState? = null,
) {
    val scope = rememberCoroutineScope()

    SmallTopAppBar(
        modifier = Modifier.shadow(elevation = appBarElevation),
        title = {
            Text(
                title,
                modifier = titleModifier,
                fontSize = titleFontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerBackGroundColor,
            titleContentColor = titleContentColor
        ),
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