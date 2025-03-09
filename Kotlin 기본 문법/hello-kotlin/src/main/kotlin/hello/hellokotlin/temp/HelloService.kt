package hello.hellokotlin.temp

import hello.hellokotlin.dto.request.PostWithRequestDto
import hello.hellokotlin.dto.response.PostWithResponseDto
import org.springframework.stereotype.Service

@Service
class HelloService {

    fun postWith(postWithRequestDto: PostWithRequestDto): PostWithResponseDto {
        return PostWithResponseDto("당신의 이름은 ${postWithRequestDto.name}이고 나이는 ${postWithRequestDto.age}입니다!")
    }
}