package org.portifolio.repository.model

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_configuracao_certificado", schema = "dbo")
data class Certificado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    val id: Long? = null,

    @Column(name = "ds_titulo", nullable = false)
    val titulo: String = "",

    @Column(name = "ds_plataforma", nullable = false)
    val plataforma: String = ""

) : PanacheEntityBase()