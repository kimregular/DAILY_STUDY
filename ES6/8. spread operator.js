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
oy.b = 4; // oy에만 값을 추가했지만 ox에도 값이 추가됨 -> 같은 인스턴스를 참조하기 때문
console.log(ox); // { a: 3, b: 4 }
console.log(oy); // { a: 3, b: 4 }
ox = {a: 3}; // 다시 초기화
var oz = {...ox};
oz.b = 4;
console.log(ox); // { a: 3 }
console.log(oz); // { a: 3, b: 4 }
// oz 에만 값이 추가되었다.

// 5. 함수 파라미터 삽입시 사용
function 더하기(a, b, c) {
    console.log(a + b + c);
}

더하기(1, 2, 3);
// 만약 배열을 파라미터로 넣는다면?
var params = [10, 20, 30];
더하기(params); // 에러!

// 이런경우 옛날에는 apply()를 사용했다.
더하기.apply(undefined, params);
// es6 부터는 ... 사용한다.
더하기(...params);

// apply() 뭐임?
var person1 = {
    인사: function () {
        console.log(this.name + "안녕");
    }
};

var person2 = {
    name: "김이박",
}

person1.인사(); // undefined안녕

// person1의 인사() 를 person2에 적용하고 싶다면?
// 1. 인사() 를 복사해서 person2에 붙여넣는다.
// 2. apply() 사용한다
person1.인사.apply(person2); // 김이박안녕

// call() 을 알아보자.
// apply()와 비슷하다.

person1.인사.call(person2); // 김이박안녕

// 차이점이라면 apply()는 두 번째 파라미터로 배열을 받는다.
// call()은 배열이 아닌 값을 받는다.