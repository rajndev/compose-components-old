package com.shivnasoft.compose_components.forms

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    inputVal: String,
    textStyle: TextStyle = TextStyle(Color.Black),
    isSingleLine: Boolean = false,
    maxLines: Int = 0,
    isError: Boolean = false,
    errorTextMessage: String = "",
    isReadOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValChange: (String) -> Unit
) {
    OutlinedTextField(
        value = inputVal,
        onValueChange = {
            onValChange(it)
        },
        label = { Text(text = label) },
        textStyle = textStyle,
        singleLine = isSingleLine,
        maxLines = maxLines,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        modifier = modifier,
        readOnly = isReadOnly
    )

    if (isError && errorTextMessage.isNotEmpty()) {
        Text(
            text = errorTextMessage,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}
