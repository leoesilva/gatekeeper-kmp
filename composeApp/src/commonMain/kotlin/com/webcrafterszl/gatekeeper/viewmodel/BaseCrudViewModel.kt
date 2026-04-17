package com.webcrafterszl.gatekeeper.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.webcrafterszl.gatekeeper.data.model.Identificavel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseCrudViewModel<T : Identificavel>(
    initialFormValues: Map<String, String>,
    private val toEntity: (Map<String, String>) -> T,
    private val fromEntity: (T) -> Map<String, String>,
    private val carregarItens: suspend () -> List<T>,
    private val salvarItem: suspend (T) -> Unit,
    private val excluirItem: suspend (T) -> Unit,
) : ViewModel() {
    val itens = mutableStateListOf<T>()
    val formValues = mutableStateMapOf<String, String>().apply { putAll(initialFormValues) }

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val emptyFormValues = initialFormValues.toMap()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    init {
        recarregar()
    }

    fun atualizarCampo(chave: String, valor: String) {
        formValues[chave] = valor
    }

    fun preencherFormulario(item: T) {
        formValues.clear()
        formValues.putAll(fromEntity(item))
    }

    fun limparCampos() {
        formValues.clear()
        formValues.putAll(emptyFormValues)
    }

    fun salvar() {
        scope.launch {
          val sucesso = executarOperacao { salvarItem(toEntity(formValues)) }
          if (sucesso) {
            limparCampos()
            recarregar()
          }
        }
    }

    fun apagar(item: T) {
        scope.launch {
          val sucesso = executarOperacao { excluirItem(item) }
          if (sucesso) {
            recarregar()
          }
        }
    }

    fun recarregar() {
        scope.launch {
            executarOperacao {
            val carregados = carregarItens()
            itens.clear()
            itens.addAll(carregados)
            }
        }
    }

      private suspend fun executarOperacao(operacao: suspend () -> Unit): Boolean {
        isLoading = true
        errorMessage = null
        val resultado = runCatching { operacao() }
            .onFailure { errorMessage = it.message ?: "Falha inesperada ao processar o CRUD." }
        isLoading = false
        return resultado.isSuccess
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}



