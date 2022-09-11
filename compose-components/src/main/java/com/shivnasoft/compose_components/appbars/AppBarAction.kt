package com.shivnasoft.compose_components.appbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppBarAction(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    iconColor: Color = Color.Black,
    contentDescription: String = "",
    onClick: () -> Unit = { }
) {
    when {
        painter != null ->
            IconButton(onClick = onClick) {
                Icon(
                    painter = painter,
                    modifier = modifier,
                    tint = iconColor,
                    contentDescription = contentDescription
                )
            }

        imageVector != null -> IconButton(onClick = onClick) {
            Icon(
                imageVector = imageVector,
                modifier = modifier,
                tint = iconColor,
                contentDescription = contentDescription
            )
        }
    }

    /*painter?.let {
        IconButton(onClick = onClick) {
            Icon(
                painter = painter,
                modifier = Modifier.size(27.dp).then(modifier),
                tint = iconColor,
                contentDescription = contentDescription
            )
        }
    }

    imageVector?.let {
        IconButton(onClick = onClick ) {
            Icon(
                imageVector = imageVector,
                modifier = Modifier.size(27.dp).then(modifier),
                tint = iconColor,
                contentDescription = contentDescription
            )
        }
    }*/
}