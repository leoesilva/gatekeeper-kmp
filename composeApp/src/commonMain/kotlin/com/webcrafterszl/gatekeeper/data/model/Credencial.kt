package com.webcrafterszl.gatekeeper.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CredencialRFID(
	override val id: Int = 0,
	val codigoHexadecimal: String = "",
	val nivelAcesso: Int = 0,
	val horaUltimaLeitura: String = "",
	val bloqueada: Boolean = false,
) : Identificavel

typealias Credencial = CredencialRFID
