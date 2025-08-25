package org.portifolio.service

import com.google.firebase.FirebaseApp
import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.repository.CertificadoRepository
import org.portifolio.repository.dto.PaginatedResponse
import org.portifolio.repository.model.Certificado
import java.util.Optional
import com.google.firebase.cloud.StorageClient
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Response
import org.jboss.resteasy.reactive.RestResponse
import java.io.ByteArrayInputStream

@ApplicationScoped
class CertificadoServices(
    private val certificadoRepository: CertificadoRepository
) {

    fun getCertificados(pagina: Int, tamanho: Int): PaginatedResponse<Certificado> {
        val resultado = certificadoRepository.getListagemPaginado(pagina, tamanho)
        val novosItens = resultado.itens.map { it.copy(url = "https://${it.url}") }
        return resultado.copy(itens = novosItens)
    }

    fun getDownload(id: Long): ByteArrayInputStream? {
        val certificadoMetadados = certificadoRepository.findByIdOptional(id)
            .orElseThrow { RuntimeException("Certificado não encontrado.") }
        val nome = "certificados/" + id + "/" + certificadoMetadados.titulo
        val bucket = StorageClient.getInstance().bucket()
        val blob = bucket.get(nome)

        return blob?.let {
            ByteArrayInputStream(it.getContent())
        }
    }
}