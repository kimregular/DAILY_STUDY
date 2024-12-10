// 기존 문자열 할당은 아래와 같이 진행한다.
var 문자1 = "기존 문자열 할당 방법";
// es6 부터는 백틱을 사용해서 문자열을 할당할 수 있다.
var 문자2 = `새로운 문자열 할당 방법`;

// 새로운 문자열 할당 방법을 사용하는 이유
// 1. 엔터키 가능
// 2. 중간에 변수 넣기가 쉽다

// 1. 엔터키 가능
var 문자열_엔터키 = `아무렇게나

엔터키를 넣어도

문자열
할당이 
가능하다.`;

// 2. 중간에 변수 넣기가 쉽다
var 나이 = 30;
var 문자3 = `내 나이는 ${나이}살이다.`;
console.log(문자3);