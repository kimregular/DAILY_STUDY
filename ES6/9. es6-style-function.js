// es6 업그레이드된 함수의 default기능과
// es5의 arguments를 알아보자.

function 더하기(a, b) {
    console.log(a + b); // 파라미터 두 개를 더해주는 함수
}

더하기(1, 2);

// 더하기() 에는 파라미터가 2개 들어가지만 파라미터를 1개만 넣을 수도 있다.
더하기(1); // NaN

// default 파라미터를 알아보자.
function 더하기default(a, b = 10) {
    console.log(a + b);
}

더하기default(1, 2); // 3
더하기default(1); // 11
// b 자리에 아무 값도 없다면 저절로 10을 가지게 된다.
// b 자리에 값이 있다면 10은 무시된다.

// 기본값으로 함수를 지정할 수도 있다.
function 임시() {
    return 10;
}

function 더하기defaultFunc(a, b = 임시()) {
    console.log(a + b);
}

더하기defaultFunc(1, 2); // 3
더하기defaultFunc(1); // 11

// 함수의 arguments를 알아보자.

function temp(a, b, c) { // 여기의 a, b, c 는 파라미터
    console.log(a, b, c); // 여기의 a, b, c 는 arguments
}

function 더하기2(a, b, c) { // 여기의 a, b, c 는 파라미터
    console.log(arguments); // 여기의 a, b, c 는 arguments
    console.log(arguments[0]); // 배열 처럼 사용할 수 있다.
    console.log(arguments[1]);
    console.log(arguments[2]);
}

더하기2(1, 2, 3); // [Arguments] { '0': 1, '1': 2, '2': 3 }
// 배열 처럼 사용할 수 있다.

// 파라미터로 입력한 값들을 콘솔에 출력하고 싶다면?
function argumentsPrinter(a, b, c) {
    for (let i = 0; i < arguments.length; i++) {
        console.log(arguments[i]);
    }
}

argumentsPrinter(1, 2, 3);

// rest 파라미터를 알아보자.
// 함수를 만들 때 ... 기호를 파라미터 왼쪽에 추가하자
function 함수2(...rest) {
    console.log(rest);
}

함수2(1, 2, 3, 4, 5, 6); // [ 1, 2, 3, 4, 5, 6 ]
// rest 파라미터는 모든 파라미터를 배열 안에 담고 있다. 이름은 마음대로 지정 가능하다 ex) ...rest, ...내맘, ...뭐임마
// 즉, 원하는 파라미터 왼쪽에 ... 기호를 붙이면 이 자리에 오는 모든 파라미터를 중괄호로 감싸준 파라미터 라는 뜻이 된다.

function 함수3(a, b, ...c) {
    console.log(c); // rest 파라미터만 출력하는 함수
}

함수3(1, 2, 3, 4, 5, 6, 7, 8); // [ 3, 4, 5, 6, 7, 8 ]
// 3번째 파라미터부터 출력된다.

// 최신 문법으로 파라미터들 출력하기
function 함수4(...rest) {
    for (const elem of rest) {
        console.log(elem);
    }
}

함수4(1, 2, 3, 4, 5);

// 주의점
// 1. rest 파라미터는 파라미터의 가장 뒤에 위치해야한다.
// 2. rest 파라미터는 한번만 사용해야한다.

// 1. rest 파라미터는 파라미터의 가장 뒤에 위치해야한다.
// function a (...rest, x, y, z) -> error!

// 2. rest 파라미터는 한번만 사용해야한다.
// function a (...rest1, ...rest2) -> error!