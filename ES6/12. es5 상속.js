// prototype이니 this니, class니 복잡하다면 그냥 es5 방식으로 상속 기능을 사용하면 된다.
// Object.create() 문법을 알아보자.

// Object.create(프로토타입object);

var 부모 = {name : 'kim', age: 50};
// 부모가 가진 name, age를 그대로 물려받은 자식 object를 만들고 싶다.
var 자식 = Object.create(부모);
// prototype을 부모로 해주세요 라는 뜻이다.

console.log(자식); // 자식이라는 오브젝트에는 아무것도 없다.
console.log(자식.name); // 자식의 부모가  name을 갖고있기 때문에 정상 출력
// 자식의 나이가 50인데 바꾸려면?
자식.age = 20;
console.log(자식.age); // 이제부터는 자식이 age를 갖고있으므로 부모까지 가서 확인하지 않는다.

var 손자 = Object.create(자식);
console.log(손자.name);
console.log(손자.age);
// 자식의 age 값이 20이므로 20출력