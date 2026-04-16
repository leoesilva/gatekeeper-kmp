package com.webcrafterszl.gatekeeper.ui.screens

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webcrafterszl.gatekeeper.ui.components.AppButton
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.launch

@Composable
fun FirebaseConnectionTestScreen(onBack: () -> Unit) {
    val scope = rememberCoroutineScope()
    var statusConexao by remember { mutableStateOf("Aguardando clique...") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Teste rapido Firebase", style = MaterialTheme.typography.headlineSmall)
        Text(
            text = statusConexao,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp),
        )

        AppButton(
            text = "Testar Firebase",
            modifier = Modifier.padding(top = 24.dp),
            onClick = {
                statusConexao = "Enviando para o Firebase..."
                scope.launch {
                    val clienteKtor = HttpClient()
                    try {
                        val url = "https://gatekeeper-kmp-default-rtdb.firebaseio.com/teste.json"
                        val resposta = clienteKtor.post(url) {
                            contentType(ContentType.Application.Json)
                            setBody("""{"mensagem":"Ola Firebase, o Ktor Multiplatform funcionou!"}""")
                        }
                        statusConexao = "Sucesso! Codigo: ${resposta.status.value}"
                    } catch (e: Exception) {
                        statusConexao = "Erro: ${e.message ?: "falha desconhecida"}"
                    } finally {
                        clienteKtor.close()
                    }
                }
            },
        )

        AppButton(text = "Voltar", modifier = Modifier.padding(top = 12.dp), onClick = onBack)
    }
}

