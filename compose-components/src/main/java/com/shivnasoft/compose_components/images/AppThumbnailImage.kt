package com.shivnasoft.compose_components.images

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageScope

@Composable
fun AppThumbnailImage(
    imageModifier: Modifier = Modifier,
    imageContainerModifier: Modifier = Modifier,
    imageUri: Uri = Uri.EMPTY,
    imageContentScale: ContentScale = ContentScale.Crop,
    imageContentDescription: String? = null,
    onImageClick: (() -> Unit)? = null,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = null,
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
) {
    Column(
        modifier = imageContainerModifier
            .then(
                when {
                    onImageClick != null -> Modifier.clickable { onImageClick() }
                    else -> Modifier
                }
            )
    ) {
        SubcomposeAsyncImage(
            model = imageUri,
            modifier = imageModifier,
            contentScale = imageContentScale,
            contentDescription = imageContentDescription,
            loading = loading,
            success = success,
            error = error
        )
    }
}