package com.shivnasoft.compose_components.images

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageScope
import com.shivnasoft.compose_components.images.image_utils.MetricsUtils

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageDialog(
    showDialog: MutableState<Boolean>,
    imageUri: Uri,
    imageContentDescription: String? = null,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = null,
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
) {
    var imgWidth by rememberSaveable { mutableStateOf(0) }
    val iconSize = 36.dp
    val context = LocalContext.current

    var mod: Modifier = Modifier.Companion

    val configuration = LocalConfiguration.current
    val screenDensity = configuration.densityDpi / 160f
    val screenWidthInPx = configuration.screenWidthDp.toFloat() * screenDensity
    val screenWidthInDp = MetricsUtils.convertPixelsToDp(screenWidthInPx, context)

    if (imgWidth < screenWidthInPx) {
        val showWidth = screenWidthInDp - 40f
        mod = mod.then(Modifier.width(showWidth.dp))
    }

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White.copy(alpha = 0.0f), shape = RoundedCornerShape(10.dp))
                    .then(mod)
            ) {
                SubcomposeAsyncImage(
                    model = imageUri,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(10.dp))
                        .then(mod),
                    contentScale = ContentScale.Fit,
                    contentDescription = imageContentDescription,
                    loading = loading,
                    success = success,
                    error = error
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.Black.copy(alpha = 0.55f))
                        .size(iconSize)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        tint = Color.White,
                        contentDescription = "Remove the picture",
                        modifier = Modifier
                            .clickable(onClick = {
                                showDialog.value = false
                            })
                            .size(30.dp)
                    )
                }
            }
        }
    }

}
