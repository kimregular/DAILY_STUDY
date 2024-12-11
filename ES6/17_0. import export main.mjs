// 자바스크립트 코드가 길어지면 다른 파일로 쪼개는 습관을 들이도록 하자.
// 다른 파일로 쪼개놓고 그걸 첨부해서 사용하는 방법을 알아보자.
// es6 import export 를 사용하면 내가 원하는 변수, 함수, class만 다른 파일로 보낼 수 있다.
// import 는 사용만 가능하고 수정은 불가능하다 (readOnly)

import a from "./17_1. importTarget.mjs";
// 17_1. importTarget.mjs 에서 기본 export 대상이 a이다.
// 기본 설정이 되어 있기 때문에 마음대로 이름을 바꿔도 된다.

import {b as B} from "./17_1. importTarget.mjs";
// export default 외에는 이런식으로 import 해와야한다.
// 이름을 바꿀 수는 있다. as ~ 식으로 바꿔서 별명을 지어주면 된다.

// 위의 import 두 줄을 한 줄로 바꿀 수도 있다.
// import a, {b} from "./17_1. importTarget.mjs";
// export default 항목을 먼저 서술해야한다.

console.log(a);
console.log(B);