// 함수와 Object에서 사용하는 경우

console.log(this);
/*
this는 상황에 따라 다른 뜻을 가지는데 뜻이 3~4가지 된다.
1. this를 그냥 쓰거나 함수안에서 쓰면 window를 뜻한다. (브라우저 기준)
window는 js의 기본 함수들을 저장하는 공간이다.
 */

// 함수를 만들어서 this를 출력해보자.
function 함수() {
    console.log(this);
}
함수();