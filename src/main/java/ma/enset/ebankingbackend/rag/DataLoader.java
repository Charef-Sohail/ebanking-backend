package ma.enset.ebankingbackend.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DataLoader {

    private final SimpleVectorStore simpleVectorStore;

    // On injecte le fichier PDF depuis le dossier resources
    @Value("classpath:/bank-conditions.pdf")
    private Resource pdfResource;

    public DataLoader(SimpleVectorStore simpleVectorStore) {
        this.simpleVectorStore = simpleVectorStore;
    }

    // @PostConstruct indique à Spring d'exécuter cette méthode juste après le démarrage de l'application
    @PostConstruct
    public void initStore() {
        File vectorStoreFile = new File("vector-store.json");

        if (vectorStoreFile.exists()) {
            // OPTIMISATION : Le fichier existe déjà, on ne refait pas d'appels API payants
            System.out.println("Chargement du Vector Store depuis le fichier local...");
            simpleVectorStore.load(vectorStoreFile);
        } else {
            // Le fichier n'existe pas, c'est la première fois qu'on lance l'app
            System.out.println("Lecture du PDF et création des vecteurs en cours...");

            // 1. Lire le PDF (PagePdfDocumentReader est fourni par la dépendance qu'on a ajoutée)
            PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfResource);
            List<Document> documents = pdfReader.get();

            // 2. Découper le texte en chunks (Morceaux)
            TokenTextSplitter textSplitter = TokenTextSplitter.builder().build();
            List<Document> chunks = textSplitter.apply(documents);

            // 3. Ajouter les chunks au Vector Store (C'est ici que l'appel à l'API d'Embedding d'OpenAI se fait)
            //simpleVectorStore.add(chunks);

            // 4. Sauvegarder dans un fichier local pour la prochaine fois
            //simpleVectorStore.save(vectorStoreFile);

            System.out.println("[MODE TEST] Vector Store ignoré pour le moment.");
            System.out.println("Vector Store initialisé avec succès !");
        }
    }
}