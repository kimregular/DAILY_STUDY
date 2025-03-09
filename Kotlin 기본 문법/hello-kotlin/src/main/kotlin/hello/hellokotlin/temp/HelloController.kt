package hello.hellokotlin.temp

import hello.hellokotlin.dto.request.PostWithRequestDto
import hello.hellokotlin.dto.response.PostWithResponseDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class HelloController(
    val helloService: HelloService
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, Kotlin"
    }

    @GetMapping("/with")
    fun getWith(@RequestParam age: Int): String {
        return "Your age is $age"
    }

    @PostMapping("/withPost")
    fun postWith(@RequestBody @Valid postWithDto: PostWithRequestDto): ResponseEntity<PostWithResponseDto> {
        return ResponseEntity.ok(helloService.postWith(postWithDto))
    }
}