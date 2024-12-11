// 자바스크립트 코드가 길어지면 다른 파일로 쪼개는 습관을 들이도록 하자.
// 다른 파일로 쪼개놓고 그걸 첨부해서 사용하는 방법을 알아보자.
// es6 import export 를 사용하면 내가 원하는 변수, 함수, class만 다른 파일로 보낼 수 있다.
// import 는 사용만 가능하고 수정은 불가능하다 (readOnly)

import a from "./17_1. importTarget.mjs";

console.log(a);