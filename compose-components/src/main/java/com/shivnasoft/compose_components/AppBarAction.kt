package com.shivnasoft.cheetahlist.feature_shoppinglists.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppBarAction(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    iconColor: Color = Color.Black,
    contentDescription: String = "",
    onClick: () -> Unit = { }
) {
    painter?.let {
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
    }
}