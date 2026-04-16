package com.webcrafterszl.gatekeeper.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Portador(
	override val id: Int = 0,
	val nomeCompleto: String = "",
	val email: String = "",
	val dataContratacao: String = "",
	val acessoAtivo: Boolean = true,
) : Identificavel
