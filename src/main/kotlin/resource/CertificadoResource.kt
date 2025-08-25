package org.portifolio.resource

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse
import org.portifolio.service.CertificadoServices
import java.io.ByteArrayInputStream

@Path("certificados/api/")
class CertificadoResource(
    private val certificadoService: CertificadoServices
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    fun getCertificados(@QueryParam("pagina") pagina: Int = 0, @QueryParam("tamanho") tamanho: Int = 10)
        = certificadoService.getCertificados(pagina, tamanho)

    @GET
    @Produces("application/octet-stream")
    @Path("/{id}")
    fun getDownload(@PathParam("id") id: Long): RestResponse<ByteArrayInputStream> {
        val stream = certificadoService.getDownload(id)
            ?: return RestResponse.status(RestResponse.Status.NOT_FOUND)

        return RestResponse.ResponseBuilder.ok(stream)
            .header("Content-Type", "application/pdf")
            .header("Content-Disposition", "attachment; filename=arquivo")
            .build()
    }

}