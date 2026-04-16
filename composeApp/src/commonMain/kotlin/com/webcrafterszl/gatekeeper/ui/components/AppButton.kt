package com.webcrafterszl.gatekeeper.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppButton(
	text: String,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	onClick: () -> Unit,
) {
	Button(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		colors = ButtonDefaults.buttonColors(),
	) {
		Text(text)
	}
}