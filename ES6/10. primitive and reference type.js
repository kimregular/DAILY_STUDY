// 그냥 문자와 숫자는 primitive type이다.

var 그냥문자 = 'aaa';
var 그냥숫자 = 123;

// primitive type의 데이터는 변수에 그 값이 그대로 저장된다.
// 뭔 당연한 소리냐고?


// 배열과 오브젝트를 저장하는 변수에는 배열과 오브젝트 값이 그대로 저장되지 않고 해당 인스턴스를 가르키는 주소값이 변수에 저장된다.
// 이 주소값을 레퍼런스(참조값)이라고 한다.

var 어레이 = [1, 2, 3];
// 레퍼런스
// [1, 2, 3]이 어디에 있는지 가르키는 화살표라고 이해하면 된다.
var obj = {name: 'kim'};
// {name: 'kim'} 값이 obj에 저장되지 않고 {name: 'kim'}을 가리키는 화살표가 obj에 저장된다.

// primitive type 복사
var 이름1 = '김';
var 이름2 = 이름1;
console.log(이름1); // 김
console.log(이름2); // 김

// 만약 이름1의 값을 바꾼다면 이름2는 뭐가 출력될까?
이름1 = '박';
console.log(이름1); // 박
console.log(이름2); // 김

// reference type 복사
var obj1 = {name: '김'};
var obj2 = obj1;
console.log(obj1); // { name: '김' }
console.log(obj2); // { name: '김' }

// 만약 obj1의 값을 바꾸면 obj2는 뭐가 출력될까?
obj1.name = '박';
console.log(obj1); // { name: '박' }
console.log(obj2); // { name: '박' }

// primitive type 과는 다른 결과가 나왔다.
// reference type은 할당 과정이 primitive type과는 다르기 때문이다.
// primitive type 의 값은 변수에 그 값이 그대로 들어간다는 사실을 명심하자
// reference type은 변수에 { name: '김' } 값이 그대로 들어가지 않는다.
// { name: '김' } 값이 메모리상 어딘가 있어요 라고 가르키는 화살표를 변수에 저장한다.
// obj2에 obj1에 저장된 화살표를 저장했기 때문에 obj1과 obj2는 이제 똑같은 인스턴스를 가르키는 화살표를 갖고 있다.
// 그래서 obj1에서 값을 변경하면 obj2에서도 변경된 값이 출력되는 것이다.
// 따라서 array, object는 함부로 복사해서는 안 된다.