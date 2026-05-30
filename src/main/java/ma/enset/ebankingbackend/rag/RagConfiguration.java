package ma.enset.ebankingbackend.rag;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfiguration {

    /**
     * Ce Bean indique à Spring Boot d'instancier un SimpleVectorStore au démarrage.
     * Le EmbeddingModel est injecté automatiquement par Spring Boot (grâce à notre clé OpenAI).
     * Il servira à transformer notre texte en vecteurs mathématiques.
     */
    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}