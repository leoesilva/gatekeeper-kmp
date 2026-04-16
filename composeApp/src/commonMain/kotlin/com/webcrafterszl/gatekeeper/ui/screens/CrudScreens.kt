package com.webcrafterszl.gatekeeper.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webcrafterszl.gatekeeper.data.model.CredencialRFID
import com.webcrafterszl.gatekeeper.data.model.Identificavel
import com.webcrafterszl.gatekeeper.data.model.Portador
import com.webcrafterszl.gatekeeper.data.model.Reserva
import com.webcrafterszl.gatekeeper.data.model.Visitante
import com.webcrafterszl.gatekeeper.ui.components.AccessCard
import com.webcrafterszl.gatekeeper.ui.components.AppButton
import com.webcrafterszl.gatekeeper.ui.components.AppTextField
import com.webcrafterszl.gatekeeper.viewmodel.CredencialViewModel
import com.webcrafterszl.gatekeeper.viewmodel.PortadorViewModel
import com.webcrafterszl.gatekeeper.viewmodel.ReservaViewModel
import com.webcrafterszl.gatekeeper.viewmodel.VisitanteViewModel


data class FormFieldSpec(
    val key: String,
    val label: String,
    val secureText: Boolean = false,
)

@Composable
private fun <T : Identificavel> CrudScreen(
    title: String,
    viewModelFormValues: Map<String, String>,
    items: List<T>,
    fields: List<FormFieldSpec>,
    onValueChange: (String, String) -> Unit,
    onSave: () -> Unit,
    onClear: () -> Unit,
    onBack: () -> Unit,
    onEdit: (T) -> Unit,
    onDelete: (T) -> Unit,
    summary: (T) -> List<String>,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        AppButton(text = "Voltar", onClick = onBack)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            fields.forEach { field ->
                AppTextField(
                    value = viewModelFormValues[field.key].orEmpty(),
                    onValueChange = { onValueChange(field.key, it) },
                    label = field.label,
                    secureText = field.secureText,
                )
            }
            AppButton(text = "Gravar", onClick = onSave)
            AppButton(text = "Limpar Campos", onClick = onClear)
        }

        if (items.isEmpty()) {
            Text("Nenhum registro encontrado.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(items, key = { it.id }) { item ->
                    AccessCard(
                        title = "$title #${item.id}",
                        lines = summary(item),
                        onEdit = { onEdit(item) },
                        onDelete = { onDelete(item) },
                    )
                }
            }
        }
    }
}

@Composable
fun PortadorCrudScreen(
    viewModel: PortadorViewModel,
    onBack: () -> Unit,
) {
    CrudScreen(
        title = "CRUD Portador",
        viewModelFormValues = viewModel.formValues,
        items = viewModel.itens,
        fields = listOf(
            FormFieldSpec("id", "ID"),
            FormFieldSpec("nomeCompleto", "Nome completo"),
            FormFieldSpec("email", "E-mail"),
            FormFieldSpec("dataContratacao", "Data de contratação"),
            FormFieldSpec("acessoAtivo", "Acesso ativo"),
        ),
        onValueChange = viewModel::atualizarCampo,
        onSave = viewModel::salvar,
        onClear = viewModel::limparCampos,
        onBack = onBack,
        onEdit = viewModel::preencherFormulario,
        onDelete = viewModel::apagar,
        summary = { listOf(it.nomeCompleto, it.email, "Ativo: ${it.acessoAtivo}") },
    )
}

@Composable
fun CredencialCrudScreen(
    viewModel: CredencialViewModel,
    onBack: () -> Unit,
) {
    CrudScreen(
        title = "CRUD Credencial RFID",
        viewModelFormValues = viewModel.formValues,
        items = viewModel.itens,
        fields = listOf(
            FormFieldSpec("id", "ID"),
            FormFieldSpec("codigoHexadecimal", "Código hexadecimal"),
            FormFieldSpec("nivelAcesso", "Nível de acesso"),
            FormFieldSpec("horaUltimaLeitura", "Hora da última leitura"),
            FormFieldSpec("bloqueada", "Bloqueada"),
        ),
        onValueChange = viewModel::atualizarCampo,
        onSave = viewModel::salvar,
        onClear = viewModel::limparCampos,
        onBack = onBack,
        onEdit = viewModel::preencherFormulario,
        onDelete = viewModel::apagar,
        summary = { listOf(it.codigoHexadecimal, "Acesso: ${it.nivelAcesso}", "Bloqueada: ${it.bloqueada}") },
    )
}

@Composable
fun VisitanteCrudScreen(
    viewModel: VisitanteViewModel,
    onBack: () -> Unit,
) {
    CrudScreen(
        title = "CRUD Visitante",
        viewModelFormValues = viewModel.formValues,
        items = viewModel.itens,
        fields = listOf(
            FormFieldSpec("id", "ID"),
            FormFieldSpec("nomeVisitante", "Nome do visitante"),
            FormFieldSpec("documento", "Documento"),
            FormFieldSpec("dataVisita", "Data da visita"),
            FormFieldSpec("liberado", "Liberado"),
        ),
        onValueChange = viewModel::atualizarCampo,
        onSave = viewModel::salvar,
        onClear = viewModel::limparCampos,
        onBack = onBack,
        onEdit = viewModel::preencherFormulario,
        onDelete = viewModel::apagar,
        summary = { listOf(it.nomeVisitante, it.documento, "Liberado: ${it.liberado}") },
    )
}

@Composable
fun ReservaCrudScreen(
    viewModel: ReservaViewModel,
    onBack: () -> Unit,
) {
    CrudScreen(
        title = "CRUD Reserva",
        viewModelFormValues = viewModel.formValues,
        items = viewModel.itens,
        fields = listOf(
            FormFieldSpec("id", "ID"),
            FormFieldSpec("nomeEspaco", "Nome do espaço"),
            FormFieldSpec("quantidadePessoas", "Quantidade de pessoas"),
            FormFieldSpec("horaInicio", "Hora de início"),
            FormFieldSpec("precisaEquipamento", "Precisa de equipamento"),
        ),
        onValueChange = viewModel::atualizarCampo,
        onSave = viewModel::salvar,
        onClear = viewModel::limparCampos,
        onBack = onBack,
        onEdit = viewModel::preencherFormulario,
        onDelete = viewModel::apagar,
        summary = { listOf(it.nomeEspaco, "Pessoas: ${it.quantidadePessoas}", "Equipamento: ${it.precisaEquipamento}") },
    )
}

