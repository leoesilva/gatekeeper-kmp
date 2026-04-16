package com.webcrafterszl.gatekeeper.viewmodel

import com.webcrafterszl.gatekeeper.data.model.Reserva
import com.webcrafterszl.gatekeeper.data.remote.RepositorioRemoto

class ReservaViewModel(
    repository: RepositorioRemoto = RepositorioRemoto(),
) : BaseCrudViewModel<Reserva>(
    initialFormValues = mapOf(
        "id" to "",
        "nomeEspaco" to "",
        "quantidadePessoas" to "0",
        "horaInicio" to "",
        "precisaEquipamento" to "false",
    ),
    toEntity = { values ->
        Reserva(
            id = values["id"]?.toIntOrNull() ?: 0,
            nomeEspaco = values["nomeEspaco"].orEmpty(),
            quantidadePessoas = values["quantidadePessoas"]?.toIntOrNull() ?: 0,
            horaInicio = values["horaInicio"].orEmpty(),
            precisaEquipamento = values["precisaEquipamento"]?.equals("true", ignoreCase = true) == true,
        )
    },
    fromEntity = { item ->
        mapOf(
            "id" to item.id.toString(),
            "nomeEspaco" to item.nomeEspaco,
            "quantidadePessoas" to item.quantidadePessoas.toString(),
            "horaInicio" to item.horaInicio,
            "precisaEquipamento" to item.precisaEquipamento.toString(),
        )
    },
    carregarItens = { repository.listarReservas() },
    salvarItem = { item -> repository.salvarReserva(item) },
    excluirItem = { item -> repository.excluirReserva(item.id) },
)

