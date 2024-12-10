// es6 업그레이드된 함수의 default기능과
// es5의 arguments를 알아보자.

function 더하기(a, b) {
    console.log(a + b); // 파라미터 두 개를 더해주는 함수
}

더하기(1, 2);

// 더하기() 에는 파라미터가 2개 들어가지만 파라미터를 1개만 넣을 수도 있다.
더하기(1); // NaN

// default 파라미터를 알아보자.