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

        return unknownAnswer()
    }

    private fun findRelatedAnswer(question: String): String? {

        if (containsAny(
                question,
                "кто ты",
                "ты кто",
                "как тебя зовут",
                "твое имя",
                "тебя зовут"
            )
        ) {
            return KnowledgeBase.findAnswer("кто ты")
        }

        if (containsAny(
                question,
                "кто тебя создал",
                "кто создал тебя",
                "кто тебя сделал",
                "кто сделал тебя",
                "кто вообще тебя сделал",
                "кто тебя вообще сделал",
                "кто вообще тебя создал",
                "кто тебя вообще создал",
                "кто создал",
                "твой создатель",
                "создатель"
            )
        ) {
            return KnowledgeBase.findAnswer("кто тебя создал")
        }

        if (containsAny(
                question,
                "привет",
                "здравствуй",
                "здравствуйте",
                "хай",
                "хелло",
                "добрый день",
                "доброе утро",
                "добрый вечер"
            )
        ) {
            return KnowledgeBase.findAnswer("привет")
        }

        if (containsAny(
                question,
                "космос",
                "вселенная",
                "галактика",
                "галактики",
                "планета",
                "планеты",
                "звезда",
                "звезды",
                "черная дыра",
                "черные дыры"
            )
        ) {
            return KnowledgeBase.findAnswer("что такое космос")
        }

        if (containsAny(
                question,
                "зодак",
                "цетиризин",
                "аллергия",
                "аллергии"
            )
        ) {
            return KnowledgeBase.findAnswer("как принимать правильно зодак")
        }

        return null
    }

    private fun containsAny(
        question: String,
        vararg words: String
    ): Boolean {
        return words.any { word ->
            question.contains(word)
        }
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

    private fun unknownAnswer(): String {
        return """
Я пока не нашла точного ответа на этот вопрос.

Попробуй сформулировать вопрос немного иначе или добавь это знание в базу AstraAI.
""".trimIndent()
    }
}
