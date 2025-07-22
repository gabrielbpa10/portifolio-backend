package org.portifolio.service

import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.model.Certificado

@ApplicationScoped
class CertificadoServices {

    fun getCertificados(): List<Certificado> {
        val certifificados = mutableListOf<Certificado>()
        val certificado = Certificado(id = 1, titulo = "Título teste", url = "www.com.br")
        certifificados.add(certificado)

        val listaCertificados = certifificados

        return listaCertificados
    }
}