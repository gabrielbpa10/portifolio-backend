package org.portifolio.repository.dto

import jakarta.persistence.Column

data class CertificadoDTO(
    val id: Long? = null,
    val titulo: String = "",
    val plataforma: String = ""
)
