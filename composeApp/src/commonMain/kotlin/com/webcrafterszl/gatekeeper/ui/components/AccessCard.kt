package com.webcrafterszl.gatekeeper.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccessCard(
	title: String,
	lines: List<String>,
	modifier: Modifier = Modifier,
	onEdit: (() -> Unit)? = null,
	onDelete: (() -> Unit)? = null,
) {
	Card(
		modifier = modifier.fillMaxWidth(),
		elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
	) {
		Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
			Text(title)
			lines.take(3).forEach { Text(it) }
			Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
				if (onEdit != null) {
					Button(onClick = onEdit) { Text("✏ Editar") }
				}
				if (onDelete != null) {
					Button(onClick = onDelete) { Text("🗑 Apagar") }
				}
			}
		}
	}
}