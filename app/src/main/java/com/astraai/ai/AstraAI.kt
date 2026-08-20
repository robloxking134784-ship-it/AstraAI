package com.astraai.ai

class AstraAI {

private val responseEngine = ResponseEngine()  

private val conversationHistory = mutableListOf<ConversationMessage>()  

fun ask(question: String): String {  

    val cleanQuestion = question.trim()  

    if (cleanQuestion.isEmpty()) {  
        return "Пожалуйста, напиши вопрос."  
    }  

    conversationHistory.add(  
        ConversationMessage(  
            role = "user",  
            content = cleanQuestion  
        )  
    )  

    val answer = responseEngine.respond(cleanQuestion)  

    conversationHistory.add(  
        ConversationMessage(  
            role = "assistant",  
            content = answer  
        )  
    )  

    return answer  
}  

fun getHistory(): List<ConversationMessage> {  
    return conversationHistory.toList()  
}  

fun clearHistory() {  
    conversationHistory.clear()  
}  

fun getKnowledgeCount(): Int {  
    return KnowledgeBase.size()  
}

}

data class ConversationMessage(
val role: String,
val content: String
)
