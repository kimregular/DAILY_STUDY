// Promise가 어렵다면 쉽게 쓸 수 있는 es8 문법이 있다
// async await 이라는 키워드이다.
// 각각 promise와 then을 쉽게 만들어주는 문법이다.

// 함수를 만들어보자.
// 함수의 연산이 끝나면 특정 코드를 실행하고 싶다.
// 콜백은 복잡해서 쓰기 싫다
// promise 는 어려워서 쓰기 싫다
// async await 키워드를 사용해보자.

// async
async function 더하기1() {
    // async를 함수 앞에 붙이면 함수가 promise의 역할을 할 수 있다.
    // 함수 실행 후에 promise 오브젝트가 남는다
    return 1 + 1;
    // return 문은 then이나 catch의 파라미터로 들어간다
}

더하기1().then(function(결과) {
    console.log(`성공이에요! -> ${결과}`);
    // async 함수의 return 값을 파라미터로 받는다.
});

// await
// then 함수를 쓰기 싫다면 await 키워드를 사용할 수 있다.
// await은 async 키워드를 쓴 함수 안에서만 사용할 수 있다는 특징이 있다.
// 간단하게 프로미스.then() 대체품으로 생각하자

// 어려운 연산을 하고나서 성공/실패를 판정해주는 promise를 만들고 싶다고 가정해보자.
async function 더하기2() {
    var 어려운연산 = new Promise(function (성공, 실패) {
        var 결과 = 1 + 1;
        성공(결과);
    });
    어려운연산.then(
        console.log(결과);
    )
}

