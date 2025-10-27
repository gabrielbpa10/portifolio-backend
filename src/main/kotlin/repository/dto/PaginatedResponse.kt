package org.portifolio.repository.dto

data class PaginatedResponse<T>(
    val itens: List<T>,
    val paginaAtual: Int,
    val totalPaginas: Int,
    val totalRegistros: Long
)
