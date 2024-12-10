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
