package com.webcrafterszl.gatekeeper.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Reserva(
	override val id: Int = 0,
	val nomeEspaco: String = "",
	val quantidadePessoas: Int = 0,
	val horaInicio: String = "",
	val precisaEquipamento: Boolean = false,
) : Identificavel
