// Array, Object 자료형에 있는 자료들을 변수로 꺼내고 싶을 때
// 한두개면 가능하겠지만 여러개를 뽑아서 변수로 만들려면 코드가 매우 길어진다.
// 이때 destructuring 문법을 사용하면 변수를 쉽게 만들 수 있다.

// arr의 원소 모두를 꺼내서 각자 변수에 담고 싶다.
var arr = [1, 2, 3];
var [a, b, c] = arr;
// 모양만 맞춰서 변수를 선언하면 변수가 생성된다
console.log(a);
console.log(b);
console.log(c);
// 직관적으로 변수를 만들 수 있다.

// 만약 모양이 맞지 않는다면?
var [w, x, y, z] = arr;
console.log(w);
console.log(x);
console.log(y);
console.log(z); // undefined!

// 길이가 맞지 않아 undefined 출력된다.
// 이런 경우에는 기본값을 설정해놓을 수 있다.
var [i, j, k, l = 10] = arr;
console.log(i);
console.log(j);
console.log(k);
console.log(l); // 10 출력

// 오브젝트에도 가능하다!
// object 데이터를 꺼내서 변수에 담아보자.
var obj = {name: 'kim', age: 30};
// 기존 방법
var name = obj.name;
var age = obj.age;
console.log(name);
console.log(age);
// es6
var {name, age} = obj;
console.log(name);
console.log(age);
// object 의 경우에는 array와 다르게 위치와는 상관없다. 키값만 맞으면 해당하는 값이 할당된다.
// 기본값 설정이 가능하다.
var {name, age, something = 'a'} = {name: 'kim', age: 30};
console.log(name);
console.log(age);
console.log(something); // a 출력!

// 변수명을 다르게 하고싶다면?
var {name : 나이, age} = {name : 'kim', age: 30};
console.log(나이); // name이 "나이" 변수에 저장되었다.
console.log(age);