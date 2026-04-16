package com.webcrafterszl.gatekeeper.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webcrafterszl.gatekeeper.ui.components.AppButton
import com.webcrafterszl.gatekeeper.ui.components.AppTextField

@Composable
fun LoginScreen(
	onLoginClick: () -> Unit,
	onRegisterClick: () -> Unit,
) {
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	Column(
		modifier = Modifier.fillMaxSize().padding(24.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Text("Gatekeeper", style = MaterialTheme.typography.headlineLarge)
		Text("Acesso inicial simulado", style = MaterialTheme.typography.bodyMedium)
		AppTextField(value = email, onValueChange = { email = it }, label = "E-mail", modifier = Modifier.padding(top = 24.dp))
		AppTextField(value = password, onValueChange = { password = it }, label = "Senha", modifier = Modifier.padding(top = 12.dp), secureText = true)
		AppButton(text = "Entrar", modifier = Modifier.padding(top = 24.dp), onClick = onLoginClick)
		AppButton(text = "Criar cadastro", modifier = Modifier.padding(top = 12.dp), onClick = onRegisterClick)
	}
}