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
console.log(myNames["name"]) // "kim" 출력

// 코드를 조건부로 실행하고 싶을 때
// 조건식 부분이 true라면 괄호 안의 코드가 동작한다.
if(조건식) {
    console.log("안녕");
}

// 조건식 부분이 거짓일 때 동작하는 코드를 설정하고 싶다면 else 부분에 작성한다.
if(조건식) {
    console.log("안녕");
} else {
    console.log("안녕2");
}

// 여러 조건을 동시에 구분하고 싶다면 else if 를 사용한다.

if(조건식) {
    console.log("안녕");
} else if (조건식2) {
    console.log("안녕2");
} else {
    console.log("안녕3");
}

// 코드를 반복하고 싶다면 for문 사용
// 안녕! 을 5번 반복한다.
for (var i = 0; i < 5; i++) {
    console.log("안녕!");
}
// forEach
// array의 길이만큼 function이 동작한다.
// 안녕!이 4번 출력된다.
[1, 2, 3, 4].forEach(function () {
    console.log("안녕!");
});
// function에 파라미터를 지정했다면 해당 파라미터는 array의 원소이다.
// 1 2 3 4 출력
[1, 2, 3, 4].forEach(function (a) {
    console.log(a);
});