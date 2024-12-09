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

// 자료를 한번에 여러개 저장하려면 array, object를 사용한다.
var myName = "kim";
var names = ["kim", "lee"]; // array
var myNames = { // object 값 옆에 이름을 붙여줘야함
    name: 'kim',
    age: 20,
};

// array와 object 자료형 접근하기
console.log(names[0]); // "kim" 출력
console.log(myNames.name); // "kim" 출력