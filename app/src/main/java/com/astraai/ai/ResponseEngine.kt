package com.astraai.ai

class ResponseEngine {

    fun respond(question: String): String {

        if (question.isBlank()) {
            return "Пожалуйста, напиши вопрос."
        }

        val answer = KnowledgeBase.findAnswer(question)

        return answer ?: """
Я пока не знаю точного ответа на этот вопрос.
Попробуй сформулировать его немного иначе.
""".trimIndent()
    }
}
