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

var 나이가숫자인사람 = {
    name: '김',
    age: 20,
    setAge(age) {
        if(typeof age === 'number') {
            // 아니면 parseInt(age) 사용해도 된다.
            this.age = age;
        }
    },
}

// 이렇게 되어있는 경우
// 나이가숫자인사람.age = '20'; 이렇게 직접적으로 접근해서 나이를 문자열 데이터로 바꾸는 상황을 방지할 수 있다.
나이가숫자인사람.setAge('20'); // -> 문자열이 파라미터로 들어왔기 때문에 적용되지 않는다.
console.log(나이가숫자인사람.age);
나이가숫자인사람.setAge(200);
console.log(나이가숫자인사람.age);

// 복잡한 소괄호가 보기 싫다면 set/get 키워드를 함수 앞에 붙인다.

var set키워드설정한사람 = {
    name: '김',
    age: 20,
    get getAge() {
        return this.age + 1;
    },
    set setAge(age) {
        if(typeof age === 'number') {
            // 아니면 parseInt(age) 사용해도 된다.
            this.age = age;
        }
    },
}

set키워드설정한사람.setAge = '20'; // 적용 안 됨
console.log(set키워드설정한사람.age);
set키워드설정한사람.setAge = 200;
console.log(set키워드설정한사람.age); // get 키워드를 함수 앞에 붙여서 소괄호가 필요없다.
console.log(set키워드설정한사람.getAge);


// class에서 getter/setter 사용하기

class Person {
    constructor() {
        this.name = 'park';
        this.age = 20;
    }

    get nextAge() {
        return this.age + 1;
    }

    set setAge(age) {
        this.age = parseInt(age);
    }
}

var person1 = new Person();
console.log(person1.nextAge);
person1.setAge = '2000';
console.log(person1.age);