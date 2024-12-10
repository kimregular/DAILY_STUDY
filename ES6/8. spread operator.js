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
