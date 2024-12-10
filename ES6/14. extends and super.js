// extends 와 super를 알아보자.
// 이거 왜씀?
// class 부모 를 만들었는데 이와 비슷한 class를 하나 더 만들고 싶을 때 사용한다.

class 할아버지 {
    constructor(name) {
        this.성 = 'kim';
        this.이름 = name;
    }
}

var 할아버지1 = new 할아버지("수로");
console.log(할아버지1);

// 이제 아버지라는 클래스를 하나 더 만들건데 할아버지의 속성을 그대로 물려받고싶다.
class 아버지 extends 할아버지 {
    constructor(name) {
        super(name); // -> 할아버지 클래스의 생성자를 의미한다.
        this.나이 = 50;
        // extends 해서 만든 클래스는 this를 함부로 못쓴다.
        // super() 다음에 써야한다.
    }
}

var 아버지1 = new 아버지("유신");
console.log(아버지1);