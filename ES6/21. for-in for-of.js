// 반복문들을 알아보자.

// for in 반복문
// object에 사용한다
// object 자료형에 저장된 자료들을 하나씩 꺼내고 싶을 때 사용한다.

var obj = {name : 'kim', age: 30}
for (const key in obj) {
    console.log(obj[key]);
}
// 반복문은 obj 자료 내부 데이터 개수만큼 반복하면서 key 값을 할당한다
// 반복시마다 변경되는 key 값을 이용해서 오브젝트 내의 자료들을 모두 출력할 수 있다.

// for in 반복문의 특징
// 1. enumerable 인 것들만 출력해준다.
// 2. 부모의 prototype에 저장된 것도 출력해준다.

// 1. enumerable 인 것들만 출력해준다.
//objec 자료형을 만들 때 {name : 'kim'}을 저장하면 kim이라는 자료만 달랑 저장되지 않는다.
// kim과 함께 속성 3개가 추가로 저장된다.
var obj = {name : 'kim', age: 30}
console.log(Object.getOwnPropertyDescriptor(obj, "name"));
// { value: 'kim', writable: true, enumerable: true, configurable: true }
// 이 값들이 kim과 함께 몰래 저장되는 속성들이다


// 2. 부모의 prototype에 저장된 것도 출력해준다.
