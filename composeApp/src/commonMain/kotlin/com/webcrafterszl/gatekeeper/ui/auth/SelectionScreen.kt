package com.webcrafterszl.gatekeeper.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webcrafterszl.gatekeeper.ui.components.AppButton

@Composable
fun SelectionScreen(
	onAdminClick: () -> Unit,
	onUserClick: () -> Unit,
	onLogoutClick: () -> Unit,
) {
	val colorScheme = MaterialTheme.colorScheme

	Box(modifier = Modifier.fillMaxSize().background(colorScheme.background)) {
		Column(
			modifier = Modifier.fillMaxSize().padding(24.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Surface(
				color = colorScheme.surface,
				shape = MaterialTheme.shapes.large,
				tonalElevation = 2.dp,
			) {
				Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
					Text("Escolha o módulo", style = MaterialTheme.typography.headlineSmall, color = colorScheme.tertiary)
					Text(
						text = "Administrador ou Autoatendimento",
						style = MaterialTheme.typography.bodyMedium,
						color = colorScheme.onSurfaceVariant,
						modifier = Modifier.padding(top = 6.dp),
					)
					AppButton(text = "Entrar como Admin", modifier = Modifier.padding(top = 24.dp), onClick = onAdminClick)
					AppButton(text = "Entrar como Usuario", modifier = Modifier.padding(top = 12.dp), onClick = onUserClick)
					AppButton(text = "Sair", modifier = Modifier.padding(top = 24.dp), onClick = onLogoutClick)
				}
			}
		}
	}
}