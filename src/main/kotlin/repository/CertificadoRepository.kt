package org.portifolio.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.panache.common.Page
import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.repository.dto.PaginatedResponse
import org.portifolio.repository.model.Certificado
import kotlin.math.ceil

@ApplicationScoped
class CertificadoRepository : PanacheRepository<Certificado> {

    fun getListagemPaginado(pagina: Int, tamanho: Int): PaginatedResponse<Certificado> {
        val query = findAll()
        val totalRegistros = query.count()
        val totalPaginas = ceil(totalRegistros.toDouble() / tamanho).toInt()

        val itens = query.page<Certificado>(pagina, tamanho)
            .list<Certificado>()

        return PaginatedResponse(
            itens = itens,
            paginaAtual = pagina,
            totalPaginas = totalPaginas,
            totalRegistros = totalRegistros
        )
    }
}