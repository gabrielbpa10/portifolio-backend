package org.portifolio.resource

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.portifolio.service.CertificadoServices

@Path("certificados/api/")
class CertificadoResource(
    private val certificadoService: CertificadoServices
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    fun getCertificados() = certificadoService.getCertificados()

}