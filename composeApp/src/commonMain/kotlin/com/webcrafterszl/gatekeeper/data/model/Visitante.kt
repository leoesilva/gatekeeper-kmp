package com.webcrafterszl.gatekeeper.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Visitante(
	override val id: Int = 0,
	val nomeVisitante: String = "",
	val documento: String = "",
	val dataVisita: String = "",
	val liberado: Boolean = false,
) : Identificavel
