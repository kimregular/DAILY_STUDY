// 1. 변수 생성
var name = "kim";
var age = 20;

// 함수
document.getElementById("button").addEventListener("click", function () {
    document.getElementById("hello").innerHTML = "바보";
});

// 위 코드는 너무 길다. 함수를 만들어서 코드를 줄여보자.

function 작명1() {
    document.getElementById("hello").innerHTML = "바보";
}

document.getElementById("button").addEventListener("click", function () {
    작명()
});

// 만약 바보 말고 다른 단어로 바꾸고 싶다면 '파라미터'를 활용하자.
function 작명2(파라미터) {
    document.getElementById("hello").innerHTML = 파라미터;
}

document.getElementById("button").addEventListener("click", function () {
    작명2("이제 이걸로 글자가 설정됨")
});