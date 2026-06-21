package com.regular.chat.service

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.api.Advisor
import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ChatService(
    chatClientBuilder: ChatClient.Builder,
    advisors: List<Advisor>
) {

    private val chatClient = chatClientBuilder.defaultAdvisors(advisors).build()

    fun stream(prompt: Prompt, conversationId: String): Flux<String> {
        return prepareRequest(prompt, conversationId)
            .stream()
            .content()
    }

    fun call(prompt: Prompt, conversationId: String): ChatResponse? {
        return prepareRequest(prompt, conversationId)
            .call()
            .chatResponse()
    }

    fun csEvaluation(
        prompt: Prompt,
        conversationId: String
    ): CsEvaluation {
        return prepareRequest(prompt, conversationId)
            .call()
            .entity(CsEvaluation::class.java) ?: throw IllegalStateException()
    }

    private fun prepareRequest(prompt: Prompt, conversationId: String): ChatClient.ChatClientRequestSpec {
        return chatClient.prompt(prompt)
            .advisors { advisorSpec ->
                advisorSpec
                    .param(ChatMemory.CONVERSATION_ID, conversationId)
            }
    }
}

enum class Urgency {
    LOW, MEDIUM, HIGH, URGENT
}

enum class Category {
    REFUND, SHIPPING, DEFECT, INQUIRY
}

data class CsEvaluation(
    val urgency: Urgency,
    val category: Category,
    val keywords: List<String>
)