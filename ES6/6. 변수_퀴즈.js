// 다음 코드의 콘솔창 출력 결과는 뭘까요?
함수1();
function 함수1() {
    console.log(안녕);
    let 안녕 = "Hello";
}

// 다음 코드의 콘솔창 출력 결과는 뭘까요?
함수2();
var 함수2 function() {
    console.log(안녕);
    var 안녕 = "Hello";
}

// 다음 코드의 콘솔창 출력 결과는 뭘까요?
let a = 1;
var 함수3 = function() {
    a = 2;
}
console.log(a);

// 다음 코드의 콘솔창 출력 결과는 뭘까요?
let x = 1;
var y = 2;
window.x = 3;
window.y = 4;
console.log(x + y);