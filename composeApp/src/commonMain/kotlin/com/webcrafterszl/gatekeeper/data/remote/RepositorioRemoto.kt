package com.webcrafterszl.gatekeeper.data.remote

import com.webcrafterszl.gatekeeper.data.model.CredencialRFID
import com.webcrafterszl.gatekeeper.data.model.Identificavel
import com.webcrafterszl.gatekeeper.data.model.Portador
import com.webcrafterszl.gatekeeper.data.model.Reserva
import com.webcrafterszl.gatekeeper.data.model.Visitante
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class RepositorioRemoto(
	private val baseUrl: String = "https://gatekeeper-kmp-default-rtdb.firebaseio.com",
	private val client: HttpClient = KtorClient.client,
	private val json: Json = Json {
		ignoreUnknownKeys = true
		encodeDefaults = true
		explicitNulls = false
	},
) {
	private fun collectionUrl(collection: String): String = "${baseUrl.trimEnd('/')}/$collection.json"

	private fun itemUrl(collection: String, id: Int): String = "${baseUrl.trimEnd('/')}/$collection/$id.json"

	private suspend inline fun <reified T> putBody(collection: String, id: Int, item: T) {
		try {
			client.put(itemUrl(collection, id)) {
				contentType(ContentType.Application.Json)
				setBody(item)
			}
		} catch (e: Exception) {
			throw Exception("Erro ao atualizar item $id em $collection: ${e.message}", e)
		}
	}

	private suspend inline fun <reified T : Identificavel> listBody(collection: String): List<T> {
		return try {
			val responseText = client.get(collectionUrl(collection)).bodyAsText()
			if (responseText.isBlank() || responseText == "null") return emptyList()

			val parsed = json.parseToJsonElement(responseText)
			val nodes: List<JsonElement> = when (parsed) {
				is JsonObject -> {
					parsed["error"]?.let {
						throw Exception("Firebase retornou erro para $collection: $it")
					}
					parsed.values.toList()
				}
				is JsonArray -> parsed.filterNot { it.toString() == "null" }
				else -> emptyList()
			}

			nodes
				.mapNotNull { node -> runCatching { json.decodeFromJsonElement<T>(node) }.getOrNull() }
				.sortedBy { it.id }
		} catch (e: Exception) {
			throw Exception("Erro ao listar $collection: ${e.message}", e)
		}
	}

	private suspend fun deleteItem(collection: String, id: Int) {
		try {
			client.delete(itemUrl(collection, id))
		} catch (e: Exception) {
			throw Exception("Erro ao excluir item $id em $collection: ${e.message}", e)
		}
	}

	private suspend inline fun <reified T : Identificavel> nextId(collection: String): Int {
		val atual = listBody<T>(collection)
		return (atual.maxOfOrNull { it.id } ?: 0) + 1
	}

	suspend fun listarPortadores(): List<Portador> = listBody("portadores")

	suspend fun salvarPortador(portador: Portador) {
		val item = if (portador.id > 0) portador else portador.copy(id = nextId<Portador>("portadores"))
		putBody("portadores", item.id, item)
	}

	suspend fun excluirPortador(id: Int) = deleteItem("portadores", id)

	suspend fun listarCredenciais(): List<CredencialRFID> = listBody("credenciais")

	suspend fun salvarCredencial(credencial: CredencialRFID) {
		val item = if (credencial.id > 0) credencial else credencial.copy(id = nextId<CredencialRFID>("credenciais"))
		putBody("credenciais", item.id, item)
	}

	suspend fun excluirCredencial(id: Int) = deleteItem("credenciais", id)

	suspend fun listarVisitantes(): List<Visitante> = listBody("visitantes")

	suspend fun salvarVisitante(visitante: Visitante) {
		val item = if (visitante.id > 0) visitante else visitante.copy(id = nextId<Visitante>("visitantes"))
		putBody("visitantes", item.id, item)
	}

	suspend fun excluirVisitante(id: Int) = deleteItem("visitantes", id)


	suspend fun listarReservas(): List<Reserva> = listBody("reservas")

	suspend fun salvarReserva(reserva: Reserva) {
		val item = if (reserva.id > 0) reserva else reserva.copy(id = nextId<Reserva>("reservas"))
		putBody("reservas", item.id, item)
	}

	suspend fun excluirReserva(id: Int) = deleteItem("reservas", id)
}