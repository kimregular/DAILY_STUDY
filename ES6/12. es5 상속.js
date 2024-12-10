// prototype이니 this니, class니 복잡하다면 그냥 es5 방식으로 상속 기능을 사용하면 된다.
// Object.create() 문법을 알아보자.

// Object.create(프로토타입object);

var 부모 = {name : 'kim', age: 50};
// 부모가 가진 name, age를 그대로 물려받은 자식 object를 만들고 싶다.
var 자식 = Object.create(부모);
// prototype을 부모로 해주세요 라는 뜻이다.

console.log(자식); // 자식이라는 오브젝트에는 아무것도 없다.
console.log(자식.name);