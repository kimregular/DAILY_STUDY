// class 문법을 알아보자.
// constructor, prototype을 이용한 상속기능을 만들 수 있게 도와주는 문법이다.

class 부모 {

    constructor() {
        this.name = "kim";
        this.age = 20;
        this.noProtoSayHi = function() {console.log("noProtoSayHi")}
        // 여기에 추가된 함수는 일반 함수
        // 자식들이 직접 함수를 갖고있는다.
    }

    protoSayHi() {
        console.log("protoSayHi");
        // 여기에 추가된 함수는 프로토타입 함수
        // 자식들이 함수를 갖고있지 않는다 -> 프로토타입에 갖고있는다.
    }
}

var 자식 = new 부모();
console.log(자식);
자식.noProtoSayHi();
자식.protoSayHi();
console.log(자식.__proto__);
console.log(부모.prototype);