package com.webcrafterszl.gatekeeper.data.remote

import com.webcrafterszl.gatekeeper.data.model.CredencialRFID
import com.webcrafterszl.gatekeeper.data.model.Identificavel
import com.webcrafterszl.gatekeeper.data.model.Portador
import com.webcrafterszl.gatekeeper.data.model.Reserva
import com.webcrafterszl.gatekeeper.data.model.Visitante
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
private data class FirebasePostResponse(val name: String)

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

	private suspend inline fun <reified T> postBody(collection: String, item: T): String {
		return client.post(collectionUrl(collection)) {
			contentType(ContentType.Application.Json)
			setBody(item)
		}.bodyAsText()
	}

	private suspend inline fun <reified T> putBody(collection: String, id: Int, item: T) {
		client.put(itemUrl(collection, id)) {
			contentType(ContentType.Application.Json)
			setBody(item)
		}
	}

	private suspend inline fun <reified T : Identificavel> listBody(collection: String): List<T> {
		val responseText = client.get(collectionUrl(collection)).bodyAsText()
		if (responseText.isBlank() || responseText == "null") return emptyList()

		return json.decodeFromString<Map<String, T>>(responseText).values.sortedBy { it.id }
	}

	private suspend fun deleteItem(collection: String, id: Int) {
		client.delete(itemUrl(collection, id))
	}

	private suspend inline fun <reified T : Identificavel> nextId(collection: String): Int {
		val atual = listBody<T>(collection)
		return (atual.maxOfOrNull { it.id } ?: 0) + 1
	}

	private fun decodeFirebaseName(responseText: String): String? =
		if (responseText.isBlank() || responseText == "null") null else json.decodeFromString<FirebasePostResponse>(responseText).name

	suspend fun criarPortador(portador: Portador): String? = decodeFirebaseName(postBody("portadores", portador))

	suspend fun listarPortadores(): List<Portador> = listBody("portadores")

	suspend fun salvarPortador(portador: Portador) {
		val item = if (portador.id > 0) portador else portador.copy(id = nextId<Portador>("portadores"))
		putBody("portadores", item.id, item)
	}

	suspend fun excluirPortador(id: Int) = deleteItem("portadores", id)

	suspend fun criarCredencial(credencial: CredencialRFID): String? = decodeFirebaseName(postBody("credenciais", credencial))

	suspend fun listarCredenciais(): List<CredencialRFID> = listBody("credenciais")

	suspend fun salvarCredencial(credencial: CredencialRFID) {
		val item = if (credencial.id > 0) credencial else credencial.copy(id = nextId<CredencialRFID>("credenciais"))
		putBody("credenciais", item.id, item)
	}

	suspend fun excluirCredencial(id: Int) = deleteItem("credenciais", id)

	suspend fun criarVisitante(visitante: Visitante): String? = decodeFirebaseName(postBody("visitantes", visitante))

	suspend fun listarVisitantes(): List<Visitante> = listBody("visitantes")

	suspend fun salvarVisitante(visitante: Visitante) {
		val item = if (visitante.id > 0) visitante else visitante.copy(id = nextId<Visitante>("visitantes"))
		putBody("visitantes", item.id, item)
	}

	suspend fun excluirVisitante(id: Int) = deleteItem("visitantes", id)

	suspend fun criarReserva(reserva: Reserva): String? = decodeFirebaseName(postBody("reservas", reserva))

	suspend fun listarReservas(): List<Reserva> = listBody("reservas")

	suspend fun salvarReserva(reserva: Reserva) {
		val item = if (reserva.id > 0) reserva else reserva.copy(id = nextId<Reserva>("reservas"))
		putBody("reservas", item.id, item)
	}

	suspend fun excluirReserva(id: Int) = deleteItem("reservas", id)
}