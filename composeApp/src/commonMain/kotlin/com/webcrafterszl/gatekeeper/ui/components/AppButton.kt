package com.webcrafterszl.gatekeeper.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

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
		shape = RoundedCornerShape(12.dp),
		colors = ButtonDefaults.buttonColors(
			containerColor = MaterialTheme.colorScheme.primary,
			contentColor = MaterialTheme.colorScheme.onPrimary,
			disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
			disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
		),
	) {
		Text(text)
	}
}