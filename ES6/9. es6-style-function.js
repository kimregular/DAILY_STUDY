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
    for(let i = 0; i < arguments.length; i++) {
        console.log(arguments[i]);
    }
}

argumentsPrinter(1, 2, 3);