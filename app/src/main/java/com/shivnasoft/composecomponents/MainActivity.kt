package com.shivnasoft.composecomponents

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.shivnasoft.compose_components.appbars.CustomAppBar
import com.shivnasoft.compose_components.forms.CustomDropdownMenu
import com.shivnasoft.compose_components.forms.CustomOutlinedTextField
import com.shivnasoft.compose_components.forms.DropdownMenuOutlinedTextField
import com.shivnasoft.composecomponents.ui.theme.ComposeComponentsTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        setContent {
            ComposeComponentsTheme {
                navController = rememberAnimatedNavController()
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

                val text = rememberSaveable { mutableStateOf("") }

                Scaffold(
                    topBar = {
                        CustomAppBar(
                            appBarElevation = 4.dp,
                            title = "Main screen",
                            actions = {
                                IconButton(onClick = { /* doSomething() */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            navController = navController,
                            scaffoldState = scaffoldState
                        )
                    },
                    content = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
//                                CustomOutlinedTextField(label = "Testing", inputVal = "", onValChange =  {}, maxLines = 1 )
//                                CustomDropdownMenu(items = listOf("Meats", "Dairy", "Produce", "Frozen"))
                                DropdownMenuOutlinedTextField(
                                    modifier = Modifier.width(250.dp),
                                    fieldLabel = "Test Field",
                                    inputVal = text.value,
                                    onValueChanged = { _, value -> text.value = value },
                                    dropDownList = listOf("Homer", "Bart", "Lisa")
                                )

                                /*Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .requiredHeight(70.dp)
                                        .padding(10.dp)
                                ) {

                                    IconButton(onClick = { *//* doSomething() *//* }) {
                                        Icon(
                                            painterResource(id = R.drawable.ic_baseline_photo_camera_24),
                                            contentDescription = "Add Item Picture",
                                            modifier = Modifier
                                                .size(40.dp)
                                        )
                                    }
                                }*/
                            }

                          MainScreen()
                        }

                        /*LazyColumn(
                            contentPadding = innerPadding,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val list = (0..75).map { it.toString() }
                            items(count = list.size) {
                                Text(
                                    text = list[it],
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }*/
                    }
                )

                // SimpleSmallTopAppBar()


                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {*/
                /*  Scaffold(
                      topBar = {
                          CustomAppBar(
                              "Test Bar",
                              backGroundColor = Color.LightGray,
                              actions = { },
                              navController,
                              scaffoldState
                          )
                      },
                      bottomBar = { }
                  )
                  {
                      Text("It Works, It Works! :)")
                  }*/
                //}
            }
        }
    }
}

/*

@Composable
fun SimpleSmallTopAppBar() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Simple TopAppBar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { */
/* doSomething() *//*
 }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { */
/* doSomething() *//*
 }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val list = (0..75).map { it.toString() }
                items(count = list.size) {
                    Text(
                        text = list[it],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    )
}*/
