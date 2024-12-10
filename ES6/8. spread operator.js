// 마침표를 3개 연달아 찍으면 spread operator가 된다.
// 괄호를 제거해주는 연산자이다.
// 1. array에 붙이면 대괄호를 제거해준다.
// 2. 문자에 붙이면 펼쳐준다.

// 1. array에 붙이면 대괄호를 제거해준다.
var 어레이 = ["hello", "world"];
console.log(어레이);
console.log(...어레이);

// 2. 문자에 붙이면 펼쳐준다.
var 문자 = "hello";
console.log(문자);
console.log(...문자);

// 그래서 어디에 씀?
// 1. array 합치기, 복사
var a = [1, 2, 3];
var b = [4, 5];
var c = [a, b];
var d = [...a, ...b];
console.log(c); // 2중 배열
console.log(d); // 배열

// 2. deep copy
var x = [1, 2, 3];
var y = x;
var z = [...x];
// x와 y는 같은 인스턴스를 참조하지만 z는 완전 새로운 인스턴스를 참조한다.