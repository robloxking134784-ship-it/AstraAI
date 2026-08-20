package com.astraai.ai

class ResponseEngine {

    fun respond(question: String): String {

        val cleanQuestion = normalize(question)

        if (cleanQuestion.isEmpty()) {
            return "Пожалуйста, напиши вопрос."
        }

        val exactAnswer = KnowledgeBase.findAnswer(cleanQuestion)

        if (exactAnswer != null) {
            return exactAnswer
        }

        val relatedAnswer = findRelatedAnswer(cleanQuestion)

        if (relatedAnswer != null) {
            return relatedAnswer
        }

        return unknownAnswer(cleanQuestion)
    }

    private fun findRelatedAnswer(question: String): String? {

        val identityWords = listOf(
            "кто ты",
            "ты кто",
            "как тебя зовут",
            "твое имя",
            "тебя зовут",
            "кто тебя создал",
            "кто создал тебя",
            "создатель"
        )

        if (identityWords.any { question.contains(it) }) {
            return KnowledgeBase.findAnswer("кто ты")
        }

        val spaceWords = listOf(
            "космос",
            "вселенная",
            "галактик",
            "планет",
            "звезд",
            "черная дыра",
            "черные дыры"
        )

        if (spaceWords.any { question.contains(it) }) {
            return KnowledgeBase.findAnswer("что такое космос")
        }

        val greetingWords = listOf(
            "привет",
            "здравствуй",
            "здравствуйте",
            "хай",
            "хелло"
        )

        if (greetingWords.any { question.contains(it) }) {
            return KnowledgeBase.findAnswer("привет")
        }

        val healthWords = listOf(
            "зодак",
            "цетиризин",
            "аллерги"
        )

        if (healthWords.any { question.contains(it) }) {
            return KnowledgeBase.findAnswer("как принимать правильно зодак")
        }

        return null
    }

    private fun unknownAnswer(question: String): String {

        return """
Я пока не нашла точного ответа на этот вопрос.

Попробуй сформулировать вопрос немного иначе или добавь это знание в базу AstraAI.
""".trimIndent()
    }

    private fun normalize(text: String): String {

        return text
            .lowercase()
            .trim()
            .replace("ё", "е")
            .replace(Regex("\\s+"), " ")
            .removeSuffix("?")
            .trim()
    }
}
