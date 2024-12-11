// 반복문들을 알아보자.

// for in 반복문
// object에 사용한다
// object 자료형에 저장된 자료들을 하나씩 꺼내고 싶을 때 사용한다.

var obj = {name: 'kim', age: 30}
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
var obj = {name: 'kim', age: 30}
console.log(Object.getOwnPropertyDescriptor(obj, "name"));
// { value: 'kim', writable: true, enumerable: true, configurable: true }
// 이 값들이 kim과 함께 몰래 저장되는 속성들이다
// 여기서 `enumerable` 이라는 값이 있는데, 이게 true인 자료들만 for in 반복문을 출력할 수 있다
// 이 값을 강제로 false로 만들면 for in 반복문이 제외하고 다른 값들을 순회한다

// 2. 부모의 prototype에 저장된 것도 출력해준다.
// object의 부모의 유전자에 있는 속성도 반복문으로 출력해준다

class 부모 {

    static {
        this.prototype.name = 'park';
    }
}

부모.prototype.name = 'park';

var 자식 = new 부모();
for (const key in 자식) {
    console.log(자식[key]);
}

// park이라는 자료는 부모가 가지고 있는 것인데도 출력된다
// 단점임
// 거르려면 if문을 사용하자
for (const key in 자식) {
    if (자식.hasOwnProperty(key)) {
        console.log(자식[key]); // 출력 아무것도 안 됨
    }
}

// for of 반복문
// for in 반복문과 유사하다
var arr = [1, 2, 3];
for (const number of arr) {
    console.log(number);
}
// array 뿐만 아니라, 문자, arguments, NodeList, Map, Set 이라는 자료형에 적용할 수 있는 반복문이다
// 자세히 말하자면 iterable인 자료형에만 적용가능한 반복문이다
// 무슨 말인고 하면 [Symbol.iterator]() 이라는 메서드를 가지고 있는 자료형들을 뜻한다
console.log(arr[Symbol.iterator]());