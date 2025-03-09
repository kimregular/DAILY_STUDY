package hello.hellokotlin.dto.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class PostWithRequestDto(

    @field:Min(value = 1, message = "나이는 최소 1세 이상이어야 합니다.")
    @field:Max(value = 100, message = "나이는 최대 100세 이하여야 합니다.")
    val age: Int,

    @field:NotBlank(message = "이름은 공백일 수 없습니다.")
    val name: String


)
