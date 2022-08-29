package com.shivnasoft.composecomponents

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.shivnasoft.compose_components.CustomAppBar
import com.shivnasoft.composeprogressbars.ui.theme.ComposeComponentsTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        setContent {
            ComposeComponentsTheme {
                navController = rememberAnimatedNavController()
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {*/
                Scaffold(
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
                }
                //}
            }
        }
    }
}