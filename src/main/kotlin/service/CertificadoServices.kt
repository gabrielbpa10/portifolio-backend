package org.portifolio.service

import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.repository.CertificadoRepository
import org.portifolio.repository.dto.PaginatedResponse
import org.portifolio.repository.model.Certificado

@ApplicationScoped
class CertificadoServices(
    private val certificadoRepository: CertificadoRepository
) {

    fun getCertificados(pagina: Int, tamanho: Int): PaginatedResponse<Certificado> {
        val resultado = certificadoRepository.getListagemPaginado(pagina, tamanho)
        val novosItens = resultado.itens.map { it.copy(url = "https://${it.url}") }
        return resultado.copy(itens = novosItens)
    }
}