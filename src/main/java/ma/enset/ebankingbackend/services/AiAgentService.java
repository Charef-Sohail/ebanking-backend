package ma.enset.ebankingbackend.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.stereotype.Service;

@Service
public class AiAgentService {

    private final ChatClient chatClient;

    public AiAgentService(ChatClient.Builder chatClientBuilder, SimpleVectorStore vectorStore) {

        // 1. Nouvelle syntaxe pour la Mémoire : On crée une fenêtre glissante des 20 derniers messages
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(20)
                .build();

        this.chatClient = chatClientBuilder
                .defaultSystem("Vous êtes l'assistant virtuel de E-Banking. Vous devez répondre aux questions des clients de manière courtoise. Vous devez vous baser uniquement sur les documents fournis.")

                // 2. On ajoute l'Advisor de mémoire (avec le builder)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())

                // 3. On ajoute l'Advisor pour le RAG (avec le builder)
                .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())

                .build();
    }

    public String askQuestion(String question, String conversationId) {
//        return chatClient.prompt()
//                .user(question)
//                // 4. On indique à la mémoire quel est l'identifiant de la conversation Telegram
//                .advisors(a -> a.param("chat_memory_conversation_id", conversationId))
//                .call()
//                .content();
        return "🤖 [MODE TEST] Bonjour ! Je suis l'assistant E-Banking. \nJ'ai bien reçu votre message : \"" + question + "\" \n(L'IA est actuellement désactivée pour les tests).";
    }
}