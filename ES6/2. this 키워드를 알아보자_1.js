// 함수와 Object에서 사용하는 경우

/*
this는 상황에 따라 다른 뜻을 가지는데 뜻이 3~4가지 된다.
1. this를 그냥 쓰거나 함수안에서 쓰면 window를 뜻한다. (브라우저 기준)
2. strict mode + 일반함수 내에서 쓰면 undefined
3. 메서드에서 this를 쓰면 그 메서드를 지니는 오브젝트를 의미한다.
window는 js의 기본 함수들을 저장하는 공간이다.
 */

// 1. this를 그냥 쓰거나 함수안에서 쓰면 window를 뜻한다. (브라우저 기준)
console.log(this); // window 출력
// 함수를 만들어서 this를 출력해보자.
function 함수() {
    console.log(this);
}
함수(); // window 출력

// 2. strict 모드에서는 출력이 달라진다. (브라우저 기준)
'use strict';
console.log(this); // window 출력
function 함수1() {
    console.log(this);
}
함수1(); // 브라우저 기준으로 undefined 출력

// 3. 메서드에서 this를 쓰면 그 메서드를 지니는 오브젝트를 의미한다.
var object = {
    data: "kim",
    함수: function () {
        console.log(this);
    }
}

object.함수();