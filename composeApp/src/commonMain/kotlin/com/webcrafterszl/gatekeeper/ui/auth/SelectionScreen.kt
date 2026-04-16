package com.webcrafterszl.gatekeeper.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
	onFirebaseTestClick: () -> Unit,
	onLogoutClick: () -> Unit,
) {
	Column(
		modifier = Modifier.fillMaxSize().padding(24.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Text("Escolha o módulo", style = MaterialTheme.typography.headlineMedium)
		Text("Administrador ou Autoatendimento", style = MaterialTheme.typography.bodyMedium)
		AppButton(text = "Entrar como Admin", modifier = Modifier.padding(top = 24.dp), onClick = onAdminClick)
		AppButton(text = "Entrar como Usuário", modifier = Modifier.padding(top = 12.dp), onClick = onUserClick)
		AppButton(text = "Testar Firebase", modifier = Modifier.padding(top = 12.dp), onClick = onFirebaseTestClick)
		AppButton(text = "Sair", modifier = Modifier.padding(top = 24.dp), onClick = onLogoutClick)
	}
}