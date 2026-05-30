package ma.enset.ebankingbackend.bot;

import ma.enset.ebankingbackend.services.AiAgentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramChatBot extends TelegramLongPollingBot {

    private final AiAgentService aiAgentService;

    @Value("${telegram.bot.username}")
    private String botUsername;

    // Le token est passé au super constructeur (comme l'exige la nouvelle version de la librairie)
    public TelegramChatBot(@Value("${telegram.bot.token}") String botToken, AiAgentService aiAgentService) {
        super(botToken);
        this.aiAgentService = aiAgentService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    /**
     * Cette méthode est appelée AUTOMATIQUEMENT à chaque fois que quelqu'un envoie un message au bot
     */
    @Override
    public void onUpdateReceived(Update update) {
        // On vérifie si la mise à jour contient un message texte
        if (update.hasMessage() && update.getMessage().hasText()) {

            // 1. Récupérer le texte de l'utilisateur
            String userMessage = update.getMessage().getText();
            // 2. Récupérer l'ID du chat (important pour la mémoire et pour savoir à qui répondre)
            String chatId = update.getMessage().getChatId().toString();

            System.out.println("Message reçu sur Telegram : " + userMessage);

            // 3. Envoyer le message à notre Cerveau IA
            // Note : on utilise le chatId comme ID de conversation pour la mémoire
            String aiResponse = aiAgentService.askQuestion(userMessage, chatId);

            // 4. Préparer le message de retour pour Telegram
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(aiResponse);

            // 5. Envoyer la réponse sur Telegram
            try {
                execute(message); // Méthode héritée de TelegramLongPollingBot
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}