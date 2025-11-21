package org.portifolio.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.quarkus.runtime.Startup
import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.ApplicationScoped
import java.util.Base64

@Startup
@ApplicationScoped
class FirebaseConfig {

//    @PostConstruct
//    fun init() {
//        if (FirebaseApp.getApps().isEmpty()) {
//            val serviceAccount = this::class.java.classLoader
//                .getResourceAsStream("firebase-service-account.json")
////                .getResourceAsStream("FIREBASE_CREDENTIALS_JSON")
//                ?: throw IllegalStateException("Arquivo FIREBASE_CREDENTIALS_JSON não encontrado em resources!")
//
//            val options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setStorageBucket("portifolio-8d184.firebasestorage.app")
//                .build()
//
//            FirebaseApp.initializeApp(options)
//        }
//    }

    // FORMATO BASE_64 COMPILACAO PROJETO JAR PARA DEPLOY
    @PostConstruct
    fun init() {
        val base64 = System.getenv("FIREBASE_CREDENTIALS_BASE64")
            ?: throw IllegalStateException("Variável FIREBASE_CREDENTIALS_BASE64 não encontrada!")

        val jsonBytes = Base64.getDecoder().decode(base64)

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(jsonBytes.inputStream()))
            .setStorageBucket("portifolio-8d184.firebasestorage.app")
            .build()

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        }
    }
}