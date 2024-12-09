// function 만드는 방법
function 함수만드는방법1() {
}

var 함수만드는방법2 = function () {
}
var 함수만드는방법3 = () => {
} // arrow function (es6)

// 함수를 만드는 이유
// 1. 코드들을 기능으로 묶고 싶을 때
// 2. 입출력 기계를 만들고 싶을 때


// arrow function의 장점
// 1. 입출력 기계를 만들 때 보기 쉽다.
// 2. 소괄호를 생략할 수 있다.
// 3. 코드가 한줄이라면 중괄호를 생략할 수 있다.

// 1. 입출력 기계를 만들 때 보기 쉽다.
var 입출력_기계 = (a) => {
    return a + 10
}
// 입출력_기계() 함수는 파라미터로 들어온 값에 10을 추가하여 출력하는 입출력 기계이다.

// 2. 소괄호를 생략할 수 있다.
var 입출력_기계2 = a => {
    return a + 10
} // 파라미터가 1개라면 소괄호를 생략할 수 있다.

// 3. 코드가 한줄이라면 중괄호를 생략할 수 있다.
var 입출력_기계3 = a => a + 10 // 코드가 1줄이기 때문에 중괄호와 return을 생략한다.
var 입출력_기계4 = a => console.log(a); // 코드가 1줄이기 때문에 중괄호와 return을 생략한다.


// 용례
[1, 2, 3, 4, 5].forEach(function (a) {
    console.log(a);
    // array 자료 갯수만큼 내부 코드를 실행한다.
    // a는 array 내의 자료들을 의미한다.
});

// 잘 보면 function(a){...} 를 화살표 함수로 바꿀 수 있을 듯 하다.
[1, 2, 3, 4, 5].forEach((a) => console.log(a));

var 오브젝트 = {
    함수: () => {
        return this;
    }
}

console.log(오브젝트.함수()); // window 출력
// 화살표함수는 부모의 this를 물러받는다.

// arrow function을 쓰면 내부에서 this값을 쓸 때 밖에 있던 this 값을 그대로 사용한다.
var 오브젝트1 = {
    함수: function () {
        console.log(this)
    }
}

오브젝트1.함수();
// 함수()를 가지고 있는 오브젝트인 오브젝트1이 콘솔창에 출력된다.

var 오브젝트2 = {
    함수: () => {
        console.log(this);
    }
}
오브젝트2.함수();
// 여기서는 this가 window이다. arrow function 안에서는 this값이 변하지 않고 밖에 있던 this를 그대로 사용한다.
// 오브젝트2 밖에 있던 this는 window이다.
