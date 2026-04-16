package com.webcrafterszl.gatekeeper.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webcrafterszl.gatekeeper.ui.components.AppButton
import com.webcrafterszl.gatekeeper.ui.components.AppTextField

@Composable
fun RegisterScreen(
	onRegisterClick: () -> Unit,
	onBackClick: () -> Unit,
) {
	var nome by remember { mutableStateOf("") }
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	Column(
		modifier = Modifier.fillMaxSize().padding(24.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Text("Registro", style = MaterialTheme.typography.headlineLarge)
		AppTextField(value = nome, onValueChange = { nome = it }, label = "Nome", modifier = Modifier.padding(top = 24.dp))
		AppTextField(value = email, onValueChange = { email = it }, label = "E-mail", modifier = Modifier.padding(top = 12.dp))
		AppTextField(value = password, onValueChange = { password = it }, label = "Senha", modifier = Modifier.padding(top = 12.dp), secureText = true)
		AppButton(text = "Criar conta", modifier = Modifier.padding(top = 24.dp), onClick = onRegisterClick)
		AppButton(text = "Voltar", modifier = Modifier.padding(top = 12.dp), onClick = onBackClick)
	}
}