package com.webcrafterszl.gatekeeper.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
	var tentouRegistrar by remember { mutableStateOf(false) }
	val colorScheme = MaterialTheme.colorScheme

	val nomeValido = nome.trim().length >= 3
	val emailValido = email.isNotBlank() && "@" in email && "." in email
	val senhaValida = password.length >= 6
	val formularioValido = nomeValido && emailValido && senhaValida

	Box(modifier = Modifier.fillMaxSize().background(colorScheme.background)) {
		Column(
			modifier = Modifier.fillMaxSize().padding(24.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Surface(
				modifier = Modifier.fillMaxWidth().widthIn(max = 460.dp),
				color = colorScheme.surface,
				shape = RoundedCornerShape(20.dp),
				tonalElevation = 2.dp,
			) {
				Column(modifier = Modifier.padding(20.dp)) {
					Text(
						text = "Criar conta",
						style = MaterialTheme.typography.headlineSmall,
						color = colorScheme.tertiary,
						fontWeight = FontWeight.SemiBold,
					)
					Text(
						text = "Cadastre sua credencial para acessar o sistema Gatekeeper.",
						style = MaterialTheme.typography.bodyMedium,
						color = colorScheme.onSurfaceVariant,
						modifier = Modifier.padding(top = 6.dp),
					)

					AppTextField(
						value = nome,
						onValueChange = { nome = it },
						label = "Nome completo",
						modifier = Modifier.padding(top = 20.dp),
					)
					if (tentouRegistrar && !nomeValido) {
						Text(
							text = "Informe nome e sobrenome (minimo 3 caracteres).",
							style = MaterialTheme.typography.bodySmall,
							color = colorScheme.error,
							modifier = Modifier.padding(top = 6.dp),
						)
					}

					AppTextField(
						value = email,
						onValueChange = { email = it },
						label = "E-mail institucional",
						modifier = Modifier.padding(top = 12.dp),
					)
					if (tentouRegistrar && !emailValido) {
						Text(
							text = "Informe um e-mail valido para continuar.",
							style = MaterialTheme.typography.bodySmall,
							color = colorScheme.error,
							modifier = Modifier.padding(top = 6.dp),
						)
					}

					AppTextField(
						value = password,
						onValueChange = { password = it },
						label = "Senha",
						modifier = Modifier.padding(top = 12.dp),
						secureText = true,
					)
					Text(
						text = "Use no minimo 6 caracteres.",
						style = MaterialTheme.typography.bodySmall,
						color = colorScheme.onSurfaceVariant,
						modifier = Modifier.padding(top = 6.dp),
					)
					if (tentouRegistrar && !senhaValida) {
						Text(
							text = "A senha deve ter pelo menos 6 caracteres.",
							style = MaterialTheme.typography.bodySmall,
							color = colorScheme.error,
							modifier = Modifier.padding(top = 4.dp),
						)
					}

					AppButton(
						text = "Criar conta",
						modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
						enabled = formularioValido,
						onClick = {
							tentouRegistrar = true
							if (formularioValido) {
								onRegisterClick()
							}
						},
					)

					OutlinedButton(
						onClick = onBackClick,
						modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
						colors = ButtonDefaults.outlinedButtonColors(
							contentColor = colorScheme.primary,
						),
					) {
						Text("Voltar para login")
					}
				}
			}
		}
	}
}