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
