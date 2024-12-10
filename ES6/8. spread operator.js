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
// 1. array 합치기
var a = [1, 2, 3];
var b = [4, 5];
var c = [a, b];
var d = [...a, ...b];
console.log(c); // 2중 배열
console.log(d); // 배열

// 2. 배열 deep copy
var x = [1, 2, 3];
var y = x;
var z = [...x];
// x와 y는 같은 인스턴스를 참조하지만 z는 완전 새로운 인스턴스를 참조한다.

// 3. 오브젝트 합치기
var o1 = {a: 1, b: 2};
var o2 = {o1, c: 3};
var o3 = {...o1, d: 4};
console.log(o2); // { o1: { a: 1, b: 2 }, c: 3 }
console.log(o3); // { a: 1, b: 2, d: 4 }

// 4. 오브젝트 딥카피
var ox = {a: 3};
var oy = ox;
oy.b = 4;
console.log(ox); // { a: 3, b: 4 }
console.log(oy); // { a: 3, b: 4 }
