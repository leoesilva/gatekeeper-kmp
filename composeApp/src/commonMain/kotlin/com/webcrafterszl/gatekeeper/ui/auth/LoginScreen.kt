package com.webcrafterszl.gatekeeper.ui.auth

import androidx.compose.foundation.Image
import org.jetbrains.compose.resources.painterResource
import gatekeeper.composeapp.generated.resources.Res
import gatekeeper.composeapp.generated.resources.gatekeeper_logo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
	onLoginClick: () -> Unit,
	onRegisterClick: () -> Unit,
	logoContent: (@Composable () -> Unit)? = null,
) {
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var showPassword by remember { mutableStateOf(false) }
    var tentouEntrar by remember { mutableStateOf(false) }

    val colorScheme = MaterialTheme.colorScheme

	val emailValido = email.isBlank() || ("@" in email && "." in email)
	val emailInvalido = tentouEntrar && email.isNotBlank() && !emailValido
	val podeTentarEntrar = email.isNotBlank() && password.isNotBlank()

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(colorScheme.background)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(24.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
		) {
			Column(
				modifier = Modifier.widthIn(max = 460.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
			) {
				if (logoContent != null) {
					logoContent()
				} else {
					DefaultLoginLogo()
				}
				Surface(
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 24.dp),
					shape = RoundedCornerShape(20.dp),
					color = colorScheme.surface,
					tonalElevation = 2.dp,
				) {
					Column(modifier = Modifier.padding(16.dp)) {
						OutlinedTextField(
							value = email,
							onValueChange = { email = it },
							modifier = Modifier.fillMaxWidth(),
							label = { Text("E-mail") },
							singleLine = true,
							isError = emailInvalido,
							keyboardOptions = KeyboardOptions(
								capitalization = KeyboardCapitalization.None,
								autoCorrectEnabled = false,
								imeAction = ImeAction.Next,
							),
							colors = loginTextFieldColors(),
						)

						OutlinedTextField(
							value = password,
							onValueChange = { password = it },
							modifier = Modifier
								.fillMaxWidth()
								.padding(top = 12.dp),
							label = { Text("Senha") },
							singleLine = true,
							visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
							keyboardOptions = KeyboardOptions(
								capitalization = KeyboardCapitalization.None,
								autoCorrectEnabled = false,
								imeAction = ImeAction.Done,
							),
							trailingIcon = {
								TextButton(onClick = { showPassword = !showPassword }) {
									Text(
										text = if (showPassword) "Ocultar" else "Mostrar",
										color = colorScheme.primary,
									)
								}
							},
							colors = loginTextFieldColors(),
						)

						if (emailInvalido) {
							Text(
								text = "Informe um e-mail valido para continuar.",
								modifier = Modifier.padding(top = 8.dp),
								style = MaterialTheme.typography.bodySmall,
								color = colorScheme.error,
							)
						}

						PrimaryActionButton(
							modifier = Modifier
								.fillMaxWidth()
								.padding(top = 20.dp),
							enabled = podeTentarEntrar,
							onClick = {
								tentouEntrar = true
								if (emailValido) {
									onLoginClick()
								}
							},
						)

						Row(
							modifier = Modifier
								.fillMaxWidth()
								.padding(top = 8.dp),
							horizontalArrangement = Arrangement.Center,
							verticalAlignment = Alignment.CenterVertically,
						) {
							Text(
								text = "Primeiro acesso?",
								color = colorScheme.onSurfaceVariant,
								style = MaterialTheme.typography.bodySmall,
							)
							TextButton(onClick = onRegisterClick) {
								Text("Criar cadastro", color = colorScheme.primary, fontWeight = FontWeight.Medium)
							}
						}
					}
				}
			}
		}
	}
}

@Composable
private fun DefaultLoginLogo() {
	Box(
		modifier = Modifier
			.size(320.dp)
			.padding(6.dp),
		contentAlignment = Alignment.Center,
	) {
		Image(
			painter = painterResource(Res.drawable.gatekeeper_logo),
			contentDescription = "Logo Gatekeeper",
			modifier = Modifier.fillMaxSize()
		)
	}
}

@Composable
private fun PrimaryActionButton(
	modifier: Modifier = Modifier,
	enabled: Boolean,
	onClick: () -> Unit,
) {
	val colorScheme = MaterialTheme.colorScheme

	Button(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		contentPadding = PaddingValues(vertical = 14.dp),
		shape = RoundedCornerShape(14.dp),
		colors = ButtonDefaults.buttonColors(
			containerColor = colorScheme.primary,
			contentColor = colorScheme.onPrimary,
			disabledContainerColor = colorScheme.surfaceVariant,
			disabledContentColor = colorScheme.onSurfaceVariant,
		),
	) {
		Text(
			text = "Entrar",
			style = MaterialTheme.typography.titleMedium,
			fontWeight = FontWeight.SemiBold,
		)
	}
}

@Composable
private fun loginTextFieldColors() = OutlinedTextFieldDefaults.colors(
	focusedContainerColor = MaterialTheme.colorScheme.surface,
	unfocusedContainerColor = MaterialTheme.colorScheme.surface,
	focusedTextColor = MaterialTheme.colorScheme.onSurface,
	unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
	focusedBorderColor = MaterialTheme.colorScheme.primary,
	unfocusedBorderColor = MaterialTheme.colorScheme.outline,
	focusedLabelColor = MaterialTheme.colorScheme.primary,
	unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
	cursorColor = MaterialTheme.colorScheme.primary,
	errorBorderColor = MaterialTheme.colorScheme.error,
	errorLabelColor = MaterialTheme.colorScheme.error,
)
