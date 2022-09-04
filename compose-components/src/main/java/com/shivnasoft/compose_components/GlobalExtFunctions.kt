package com.shivnasoft.compose_components

import androidx.compose.ui.Modifier

object GlobalExtFunctions {
    fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
        return if (condition) {
            then(modifier(this))
        } else {
            this
        }
    }
}