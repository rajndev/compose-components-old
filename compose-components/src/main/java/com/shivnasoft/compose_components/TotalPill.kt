package com.shivnasoft.compose_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TotalPill(
    modifier: Modifier = Modifier,
    itemTotalAmount: String = ""
) {
    val scroll = rememberScrollState(0)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.then(Modifier
            .clip(RoundedCornerShape(50))
            .background(Color.Gray)
            .padding(5.dp)
            .clickable {

            })
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_outline_receipt_long_40),
            tint = Color.White,
            contentDescription = "receipt icon",
            modifier = Modifier
                .size(25.dp)
                .padding(end = 3.dp, start = 5.dp)
        )

        Text(
            text = "Total",
            color = Color.White,
            modifier = Modifier
                .padding(end = 3.dp)
        )

        Text(
            text = itemTotalAmount,
            modifier = Modifier
                .width(50.dp)
                .horizontalScroll(scroll),
            color = Color.White
        )
    }
}

@Preview
@Composable
fun TotalPillPreview() {
    TotalPill(itemTotalAmount = "35.32")
}
