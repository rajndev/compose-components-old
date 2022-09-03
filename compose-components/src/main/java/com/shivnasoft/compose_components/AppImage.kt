package com.shivnasoft.compose_components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageScope

@Composable
fun AppImage(
    cardModifier: Modifier,
    cardElevationAmount: Dp,
    cardContainerBackgroundColor: Color = White,
    imageModifier: Modifier = Modifier,
    imageContainerModifier: Modifier = Modifier,
    imageUri: Uri = Uri.EMPTY,
    imageContentScale: ContentScale = ContentScale.None,
    imageContentDescription: String? = null,
    onImageClick: (() -> Unit)? = null,
    onDeleteIconClick: () -> Unit,
    onZoomIconClick: () -> Unit,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = null,
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
) {
    val iconSize = 36.dp
    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }

    Box(modifier = Modifier.padding((iconSize / 2))) {
        Card(
            modifier = cardModifier,
            colors = CardDefaults.cardColors(
                containerColor =  cardContainerBackgroundColor
            ),
            elevation = CardDefaults.cardElevation(cardElevationAmount)
        ) {
            Column(
                modifier = imageContainerModifier
                    .then(
                        when {
                            onImageClick != null -> Modifier.clickable { onImageClick() }
                            else -> Modifier
                        }
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = imageUri,
                    modifier = imageModifier.then(
                        Modifier
                            .padding(5.dp)
                    ),
                    contentScale = imageContentScale,
                    contentDescription = imageContentDescription,
                    loading = loading,
                    success = success,
                    error = error
                )
            }
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .offset {
                    IntOffset(x = +offsetInPx, y = -offsetInPx)
                }
                .clip(CircleShape)
                .background(color = Black)
                .size(iconSize)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                tint = White,
                contentDescription = "Temporarily remove item picture",
                modifier = Modifier
                    .clickable(onClick = {
                        onDeleteIconClick()
                    })
                    .size(27.dp)
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .offset {
                    IntOffset(x = +offsetInPx, y = +offsetInPx * 2)
                }
                .clip(CircleShape)
                .background(color = Black)
                .size(iconSize)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filled_zoom_in_30),
                tint = White,
                contentDescription = "Zoom in picture",
                modifier = Modifier
                    .clickable(onClick = {
                        onZoomIconClick()
                    })
                    .size(27.dp)
            )
        }
    }
}