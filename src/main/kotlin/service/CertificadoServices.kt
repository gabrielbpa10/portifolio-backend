package org.portifolio.service

import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.repository.CertificadoRepository
import org.portifolio.repository.dto.PaginatedResponse
import org.portifolio.repository.model.Certificado
import com.google.firebase.cloud.StorageClient
import java.io.ByteArrayInputStream

@ApplicationScoped
class CertificadoServices(
    private val certificadoRepository: CertificadoRepository
) {

    fun getCertificados(pagina: Int, tamanho: Int): PaginatedResponse<Certificado> {
        val resultado = certificadoRepository.getListagemPaginado(pagina, tamanho)
        return resultado
    }

    fun getDownload(id: Long): ByteArrayInputStream? {
        val certificadoMetadados = certificadoRepository.findByIdOptional(id)
            .orElseThrow { RuntimeException("Certificado não encontrado.") }
        val path = "certificados/$id/${certificadoMetadados.titulo}"
        val bucket = StorageClient.getInstance().bucket()
        val blob = bucket.get(path)

        return blob?.let {
            ByteArrayInputStream(it.getContent())
        }
    }
}