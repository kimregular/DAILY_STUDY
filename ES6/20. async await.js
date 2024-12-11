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

더하기1().then(function (결과) {
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
    어려운연산.then(function (결과) {
        console.log(결과);
    });
}

더하기2();

// 이렇게 하면 된다.
// 그런데  then() 이 너무 복잡해서 보기 싫으니까 await 키워드를 사용해보자.

async function 더하기3() {
    var 어려운연산 = new Promise((성공, 실패) => {
        var 결과 = 1 + 1;
        성공(결과);
    });
    var 결과 = await 어려운연산;
    console.log(결과);
}

더하기3();
// 더하기2() 함수와 비슷한 문법이다.
// 정확한 뜻은 어려운연산 promise를 기다린 다음에 완료되면 결과를 변수에 담아달라 이다.

// await 은 실패하면 에러가 나고 코드가 멈춘다.
// promise가 실패하는 코드를 만들어보자.
/*async function 더하기4() {
    var 어려운연산 = new Promise((성공, 실패) => {
        var 결과 = 1 + 1;
        실패(결과); // 실패한다
    });
    var 결과 = await 어려운연산;
    console.log(결과);
}
더하기4();*/
// await 어려운연산이 실패할 경우 에러가 나고 코드실행을 멈춘다
// 그렇게 된다면 await 한단에 있는 코드들은 더 이상 실행되지 않는다.
// 그래서 promise가 실패할 경우 코드실행을 멈추고 싶지 않다면 대응이 필요하다.
async function 더하기5() {
    var 어려운연산 = new Promise((성공, 실패) => {
        var 결과 = 1 + 1;
        실패(); // 실패한다
    });
    try {
        var 결과 = await 어려운연산;
        console.log(결과);
        // 일단 시도해보고나서
    } catch {
        // 실패한다면 여기를 실행해라
        console.log("실패해버렸네;;");
    }

}

더하기5();