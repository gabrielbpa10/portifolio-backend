package org.portifolio.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import org.portifolio.repository.model.Certificado

@ApplicationScoped
class CertificadoRepository : PanacheRepository<Certificado>