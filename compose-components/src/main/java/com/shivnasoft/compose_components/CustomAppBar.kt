package com.shivnasoft.compose_components

import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    titleContentColor: Color = Color.Black,
    containerBackGroundColor: Color = Color.White,
    actions: @Composable () -> Unit = { },
    navController: NavController? = null,
    scaffoldState: ScaffoldState? = null,
) {
    val scope = rememberCoroutineScope()

    SmallTopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = containerBackGroundColor,
            titleContentColor = titleContentColor
        ),
        navigationIcon = if (navController?.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState?.drawerState?.open()
                    }
                }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Nav drawer icon",
                    )
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