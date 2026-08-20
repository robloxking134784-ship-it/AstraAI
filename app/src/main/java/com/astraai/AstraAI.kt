AstraAI.kt

package com.astraai.ai

/**
 * Основная логика AstraAI.
 *
 * Интерфейс приложения не должен заниматься логикой ИИ.
 * MainActivity передаёт сюда сообщение пользователя,
 * а AstraAI возвращает ответ.
 */
class AstraAI {

    private var conversationHistory = mutableListOf<Message>()

    /**
     * Отправляет сообщение пользователя в AstraAI.
     */
    suspend fun sendMessage(userMessage: String): String {

        if (userMessage.isBlank()) {
            return "Введите сообщение."
        }

        conversationHistory.add(
            Message(
                role = "user",
                content = userMessage
            )
        )

        // Здесь позже будет настоящий запрос к AI API.
        val response = generateResponse(userMessage)

        conversationHistory.add(
            Message(
                role = "assistant",
                content = response
            )
        )

        return response
    }

    /**
     * Временная логика ответа.
     *
     * Позже этот метод заменим на запрос к модели ИИ.
     */
    private fun generateResponse(message: String): String {

        return when {
            message.contains("привет", ignoreCase = true) ->
                "Привет! Я AstraAI 🤖"

            message.contains("как дела", ignoreCase = true) ->
                "У меня всё отлично! Готов помогать."

            else ->
                "Я получил твоё сообщение: \"$message\""
        }
    }

    /**
     * Возвращает историю текущего диалога.
     */
    fun getConversationHistory(): List<Message> {
        return conversationHistory.toList()
    }

    /**
     * Очищает историю диалога.
     */
    fun clearConversation() {
        conversationHistory.clear()
    }
}

/**
 * Одно сообщение в диалоге.
 */
data class Message(
    val role: String,
    val content: String
)
