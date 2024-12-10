// getter setter 를 알아보자.
// 대충 오브젝트 내의 함수들을 괄호없이 쓸 수 있게 만들어주는 키워드라고 이해하면 된다.

var 사람 = {
    name: 'kim',
    age: 30,
}

// 사람의 name을 꺼내려면 어떡해야하는가?
console.log(사람.name);
// 이렇게 쓰면 된다.
// 그런데 요즘은 이렇게 안 하고 name을 꺼내는 함수를 따로 정의해서 사용한다.

var 미래사람 = {
    name: 'kim',
    age: 30,
    nextAge() {
        return this.age + 1;
    }
}

console.log(미래사람.nextAge());
// 1년 뒤 사람의 나이를 출력해준다.
// 근데 왜 이렇게함?
// object 자료가 복잡할 때 사용하기 편하기 때문이다.
// 또한 object 자료를 수정할 때 실수를 방지해준다.