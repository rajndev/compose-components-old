package com.shivnasoft.compose_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    isSingleLine: Boolean = false,
    maxLines: Int = 0,
    isError: Boolean = false,
    isNameError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = inputVal,
            onValueChange = {
                onValChange(it)
            },
            label = { Text(text = label) },
            //colors = colors,
            textStyle = TextStyle(Color.Black),
            singleLine = isSingleLine,
            maxLines = maxLines,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            modifier = modifier
        )

        if (isNameError) {
            Text(
                text = "Name is required.",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

    }
}
