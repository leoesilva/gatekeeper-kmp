package com.webcrafterszl.gatekeeper.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AppTextField(
	value: String,
	onValueChange: (String) -> Unit,
	label: String,
	modifier: Modifier = Modifier,
	secureText: Boolean = false,
) {
	var showPassword by remember { mutableStateOf(!secureText) }

	OutlinedTextField(
		value = value,
		onValueChange = onValueChange,
		label = { Text(label) },
		modifier = modifier.fillMaxWidth(),
		singleLine = true,
		visualTransformation = if (secureText && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
		trailingIcon = if (secureText) {
			{
				IconButton(onClick = { showPassword = !showPassword }) {
					Text(if (showPassword) "Ocultar" else "Mostrar")
				}
			}
		} else {
			null
		},
	)
}