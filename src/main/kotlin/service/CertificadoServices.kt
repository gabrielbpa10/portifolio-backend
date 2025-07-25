package org.portifolio.service

import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.repository.CertificadoRepository
import org.portifolio.repository.model.Certificado

@ApplicationScoped
class CertificadoServices(
    private val certificadoRepository: CertificadoRepository
) {

    fun getCertificados(): List<Certificado> = certificadoRepository.listAll()
}