// 함수와 Object에서 사용하는 경우

/*
this는 상황에 따라 다른 뜻을 가지는데 뜻이 여러가지가 있다.
window는 js의 기본 함수들을 저장하는 공간이다.
1. this를 그냥 쓰거나 함수안에서 쓰면 window를 뜻한다. (브라우저 기준)
2. strict mode + 일반함수 내에서 쓰면 undefined
3. 메서드에서 this를 쓰면 그 메서드를 지니는 오브젝트를 의미한다.
4. 새로 생성되는 인스턴스를 의미한다.
5. 이벤트리스너에서는 e.currentTarget
사실 1번과 3번은 똑같은 의미이다. 왜냐하면 함수나 변수를 전역 공간에 만들면 window에 보관되기 때문이다.
 */

// 1. this를 그냥 쓰거나 함수안에서 쓰면 window를 뜻한다. (브라우저 기준)
console.log(this); // window 출력
// 함수를 만들어서 this를 출력해보자.
function 함수() {
    console.log(this);
}
함수(); // window 출력

// 2. strict 모드에서는 출력이 달라진다. (브라우저 기준)
'use strict';
console.log(this); // window 출력
function 함수1() {
    console.log(this);
}
함수1(); // 브라우저 기준으로 undefined 출력

// 3. 메서드에서 this를 쓰면 그 메서드를 지니는 오브젝트를 의미한다.
var object = {
    data: "kim",
    함수: function () {
        console.log(this);
    }
}

object.함수(); // object body 출력

// 만약 중첩 오브젝트라면 어떻게 출력될까?
var outer = {
    inner : {
        함수 : function() {
            console.log(this);
        }
    }
}

outer.inner.함수(); // inner body 만 출력한다.

// 만약 화살표 함수라면 어떻게 출력할까?
var outer2 = {
    inner2 : {
        함수 : () => {
            console.log(this);
        }
    }
}
outer2.inner2.함수();
// this값을 부모에게 물려받아 사용한다. window가 출력된다. (브라우저 기준)

// 3. 새로 생성되는 인스턴스를 의미한다.
function 객체_생성기() {
    this.이름 = "kim"; // 객체_생성기() 를 통해 만들어지는 인스턴스의 이름은 kim이 된다.
}

var 새로운_인스턴스 = new 객체_생성기();
console.log(새로운_인스턴스); // 객체_생성기 { '이름': 'kim' } 출력

// 5. 이벤트리스너에서는 e.currentTarget
// html 안에 id="버튼" 인 button 태그가 있다고 가정하자.
// this == e.currentTarget
document.getElementById("버튼").addEventListener("click", function (e) {
    console.log(this);
    console.log(e.currentTarget); // 둘은 똑같은 값이다.
    // 지금 이벤트가 동작하고 있는 곳을 의미한다.
});

// 특수 경우를 알아보자.
// 아래 코드는 실행시 1,2,3을 출력한다.
document.getElementById("버튼").addEventListener("click", function (e) {
    var 어레이 = [1, 2, 3];
    어레이.forEach(function (a) {
        console.log(a);
    });
});

// forEach 안에 있는 함수를 콜백함수라고 부른다. (함수의 파라미터에 들어가는 함수 의미)
// 함수를 순차적으로 실행하고 싶을 때 콜백함수를 사용한다.

// 오브젝트 내에서 콜백함수를 쓴다면 this는? 1. 일반 함수
var 오브젝트 = {
    이름들: ['김', '이', '박'],
    함수: function () {
        오브젝트.이름들.forEach(function () {
            console.log(this);
        });
    },
};
오브젝트.함수(); // window를 3번 출력한다.

// 오브젝트 내에서 콜백함수를 쓴다면 this는? 2. 화살표 함수
var 오브젝트 = {
    이름들: ['김', '이', '박'],
    함수: function () {
        오브젝트.이름들.forEach(() => {
            console.log(this);
        });
    },
};
오브젝트.함수(); // 오브젝트를 3번 출력한다.