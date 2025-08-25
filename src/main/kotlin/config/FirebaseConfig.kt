package org.portifolio.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped

@Startup
@ApplicationScoped
class FirebaseConfig {

    @PostConstruct
    fun init() {
        if (FirebaseApp.getApps().isEmpty()) {
            val serviceAccount = this::class.java.classLoader
                .getResourceAsStream("firebase-service-account.json")
                ?: throw IllegalStateException("Arquivo firebase-service-account.json não encontrado em resources!")

            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("portifolio-8d184.firebasestorage.app")
                .build()

            FirebaseApp.initializeApp(options)
            println("🔥 Firebase inicializado com sucesso!")
        }
    }
}