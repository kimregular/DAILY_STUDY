package com.regular.chat

import com.regular.chat.service.ChatService
import com.regular.chat.service.CsEvaluation
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.SystemMessage
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.ChatOptions
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ChatController(
    chatClientBuilder: ChatClient.Builder,
    private val chatService: ChatService
) {

    private val chatClient = chatClientBuilder.build()

    @GetMapping("/ai")
    fun generation(userPrompt: String): String {
        return chatClient.prompt()
            .user(userPrompt)
            .call()
            .content() ?: "No response from AI"
    }

    @GetMapping("/st", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(userPrompt: String): Flux<String> {
        return chatClient
            .prompt()
            .user(userPrompt)
            .stream()
            .content()
    }

    @PostMapping("/call", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun call(
        @RequestBody @Valid promptBody: PromptBody
    ): ChatResponse {
        val prompt = createPrompt(promptBody)
        return chatService.call(prompt, promptBody.conversationId)!!
    }

    @PostMapping("/stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(
        @RequestBody @Valid promptBody: PromptBody
    ): Flux<String> {
        val prompt = createPrompt(promptBody)
        return chatService.stream(prompt, promptBody.conversationId)
    }

    /**
     * {
     *   "conversationId": "string1",
     *   "userPrompt": "물건이 일주일째 안 옵니다. 지금 당장 환불해주세요",
     *   "systemPrompt": "너는 쇼핑몰 CS 문의를 분류하는 냉철하고 객관적인 담당자야. 고객의 감정적인 표현에 휘둘리지 말고 오직 '사실'에만 기반해서 긴급도를 평가해",
     *   "chatOptions": {
     *     "temperature": 0.0
     *   }
     * }
     */
    @PostMapping("/cs", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun cs(
        @RequestBody @Valid promptBody: PromptBody
    ): CsEvaluation = chatService.csEvaluation(
        createPrompt(promptBody),
        promptBody.conversationId
    )

    private fun createPrompt(promptBody: PromptBody): Prompt {
        val messages = mutableListOf<Message>()

        if (!promptBody.systemPrompt.isNullOrBlank()) {
            messages.add(SystemMessage(promptBody.systemPrompt))
        }
        messages.add(UserMessage(promptBody.userPrompt))

        val options = promptBody.chatOptions?.let {
            ChatOptions.builder()
                .apply { it.temperature?.let { t -> temperature(t) } }
                .apply { it.maxTokens?.let { m -> maxTokens(m) } }
                .apply { it.topP?.let { p -> topP(p) } }
                .build()
        }

        return if (options != null) Prompt(messages, options) else Prompt(messages)
    }
}

data class ChatOptionsRequest(
    val temperature: Double? = null,
    val maxTokens: Int? = null,
    val topP: Double? = null
)

data class PromptBody(
    @NotNull val conversationId: String,
    @NotNull val userPrompt: String,
    @Nullable val systemPrompt: String? = null,
    val chatOptions: ChatOptionsRequest? = null
)