package com.webcrafterszl.gatekeeper.viewmodel

import com.webcrafterszl.gatekeeper.data.model.Visitante
import com.webcrafterszl.gatekeeper.data.remote.RepositorioRemoto

class VisitanteViewModel(
    repository: RepositorioRemoto = RepositorioRemoto(),
) : BaseCrudViewModel<Visitante>(
    initialFormValues = mapOf(
        "id" to "",
        "nomeVisitante" to "",
        "documento" to "",
        "dataVisita" to "",
        "liberado" to "false",
    ),
    toEntity = { values ->
        Visitante(
            id = values["id"]?.toIntOrNull() ?: 0,
            nomeVisitante = values["nomeVisitante"].orEmpty(),
            documento = values["documento"].orEmpty(),
            dataVisita = values["dataVisita"].orEmpty(),
            liberado = values["liberado"]?.equals("true", ignoreCase = true) == true,
        )
    },
    fromEntity = { item ->
        mapOf(
            "id" to item.id.toString(),
            "nomeVisitante" to item.nomeVisitante,
            "documento" to item.documento,
            "dataVisita" to item.dataVisita,
            "liberado" to item.liberado.toString(),
        )
    },
    carregarItens = { repository.listarVisitantes() },
    salvarItem = { item -> repository.salvarVisitante(item) },
    excluirItem = { item -> repository.excluirVisitante(item.id) },
)

