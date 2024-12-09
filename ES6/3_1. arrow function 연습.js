// 1. 간단한 메서드 만들기
// 오브젝트의 이름을 출력하는 화살표 함수를 만들어보자.
var 사람 = {
    name: "tester",
    sayHi: function () {
        console.log(`안녕 내 이름은 ${this.name}`);
    }
};

사람.sayHi();

// 2. 오브젝트 내의 데이터를 전부 더해주는 메서드 만들기
var 자료 = {
    data: [1, 2, 3, 4, 5]
}

자료.전부더하기 = function () {
    var result = 0;
    this.data.forEach((a) => result += a);
    return result;
};

console.log(자료.전부더하기());