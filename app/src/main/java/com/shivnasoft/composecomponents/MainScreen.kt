package com.shivnasoft.composecomponents

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shivnasoft.compose_components.images.AppImage
import com.shivnasoft.compose_components.images.ImageDialog

@Composable
fun MainScreen() {
    val editedImageUri = rememberSaveable { mutableStateOf(Uri.EMPTY) }
    val isImageEdited = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val isGalleryIconClicked = rememberSaveable { mutableStateOf(false) }
    val showDialog = rememberSaveable { mutableStateOf(false) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null && uri.toString().isNotEmpty()) {
                editedImageUri.value = uri
                isImageEdited.value = true
            } else {
                Toast.makeText(context, "Could not add gallery photo.", Toast.LENGTH_SHORT).show()
            }
        }
    )

    @Composable
    fun LaunchGallery() {
        SideEffect {
            imagePicker.launch("image/*")
            isGalleryIconClicked.value = false
        }
    }

    if (isGalleryIconClicked.value) {
        LaunchGallery()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 90.dp, start = 16.dp, end = 16.dp)
    )
    {
        /*Button(onClick = {
            navController.navigate(NavScreens.ThumbnailScreen.route)
        }) {
            Text(text = "Goto permissions screen")
        }*/

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            if (isImageEdited.value) {
                AppImage(
                    cardModifier = Modifier.size(200.dp),
                    cardElevationAmount = 8.dp,
                    imageModifier = Modifier.padding(4.dp),
                    imageContainerModifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .border(1.dp, Color.Gray.copy(alpha = 0.23f))
                        .background(Color.White),
                    imageUri = editedImageUri.value,
                    imageContentScale = ContentScale.Fit,
                    onImageClick = { isGalleryIconClicked.value = true },
                    onDeleteIconClick = { isImageEdited.value = false },
                    onZoomIconClick = { showDialog.value = true },
                    loading = { CircularProgressIndicator() },
                    error = {
                        Toast.makeText(
                            context,
                            "Could not show item image",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                ImageDialog(
                    showDialog = showDialog,
                    imageUri = editedImageUri.value
                )
            } else {
                Box {
                    Column(
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .border(3.dp, Color.Gray)
                            .padding(10.dp)
                            .clickable {
                                //imagePicker.launch("image/*")
                                isGalleryIconClicked.value = true
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_outline_add_photo_40),
                            tint = Color.Gray,
                            contentDescription = "Click to Add Image from gallery",
                            modifier = Modifier
                                .size(53.dp)
                        )

                        Text(
                            text = "Pick from Gallery",
                            fontSize = 14.sp, color = Color.Black
                        )
                    }
                }
            }
        }
    }
}