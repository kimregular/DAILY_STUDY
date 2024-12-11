// Promise가 어렵다면 쉽게 쓸 수 있는 es8 문법이 있다
// async await 이라는 키워드이다.
// 각각 promise와 then을 쉽게 만들어주는 문법이다.

// 함수를 만들어보자.
// 함수의 연산이 끝나면 특정 코드를 실행하고 싶다.
// 콜백은 복잡해서 쓰기 싫다
// promise 는 어려워서 쓰기 싫다
// async await 키워드를 사용해보자.

// async
async function 더하기() {
    // async를 함수 앞에 붙이면 함수가 promise의 역할을 할 수 있다.
    // 함수 실행 후에 promise 오브젝트가 남는다
    return 1 + 1;
    // return 문은 then이나 catch의 파라미터로 들어간다
}

더하기().then(function(결과) {
    console.log(`성공이에요! -> ${결과}`);
    // async 함수의 return 값을 파라미터로 받는다.
});



