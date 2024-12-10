// object를 안전하게 많이 복사해서 만들 수 있는 방법을 알아보자.

var 사람 = {name : 'kim'};

// {name : 'kim'} 구조를 가지는 오브젝트를 많이 만들고 싶을 때 어떡해야할까?
// 예시로 알아보자.
// 학생 출석부를 만드는 예시다.

var 학생a = {name : 'kim', age: 15};
var 학생b = {name : 'lee', age: 15};
var 학생c = {name : 'park', age: 15};

// 이런식으로 만들어도 된다. 그런데 정말 이 방법이 최선일까?
// 학생 객체를 여러개 만들기 위한 객체 생성 기계가 필요한데 이때 사용 가능한 문법이 constructor이다.

// 1. function 사용
function 기계() { // 학생 오브젝트를 생성해주는 기계
    this.name = 'kim';
    this.age = 15;
}
// 이 함수를 사용하면 이제 해당 값을 가진 오브젝트를 마구 만들어낼 수 있다.

var 학생1 = new 기계();
var 학생2 = new 기계();
var 학생3 = new 기계();
console.log(학생1);
console.log(학생2);
console.log(학생3);
