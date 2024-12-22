# vuedongsan

## 데이터 바인딩

- JS 데이터를 HTML에 꽂아넣는 문법

**바닐라js에서 하던 데이터바인딩**

```js
document.getElementById(어쩌구).innerHTML = 데이터;
```

바닐라 js에서는 길게 한줄을 써야 데이터바인딩이 가능했다.

**vue에서 하는 데이터바인딩**

1. 일단 데이터를 어딘가에 보관하고
2. 보관한 데이터를 `{{데이터}}` 문법으로 html 중간중간에 꽂아 넣는다.

Q. 보관함은 어디에 있나요?

```vue

<script>
    export default {
        name: 'App',
        data() {
            return {
                price1: 60,
                price2: 100
            }
        },
        components: {}
    }
</script>
```

script 태그 안에 `data(){return{}}` 을 선헌한 후 데이터를 Object 형식으로 저장하면 된다.

이 부분이 Vue의 데이터 보관함, 변수 보관함이다.

### HTML 속성도 데이터바인딩이 가능하다.

```html
style=""
class=""
```

이런 속성에도 데이터바인딩이 가능하다.

```vue

<template>
    <div>
        <h4 :style="스타일">XX 원룸</h4>
        <p>XX 만원</p>
    </div>
    <div>
        <h4>XX 원룸</h4>
        <p>XX 만원</p>
    </div>
</template>

<script>
    export default {
        name: 'App',
        data() {
            return {
                price1: 60,
                스타일: 'color:red'
            }
        }
    }

</script>
```

color : red 데이터도 원하는 곳에 넣을 수 있다.
대신 `:style` 처럼 문법이 다르다.

## HTML 반복문

v-for 문법을 사용한다.
사용하면 원하는 만큼 html 태그를 반복할 수 있다.

```vue

<div class="menu">
    <a v-for="menu in menus" :key="menu">{{menu}}</a>
</div>
```

1. 원하는 태그에 `v-for="작명 in 반복할횟수"`를 작성한다.
2. `:key="작명"`을 추가한다.

그러면 이제 html 태그가 원하는 만큼 반복 생성된다.
`:key` 속성은 반복문을 돌릴 때 꼭 필요하다.
반복한 요소들을 각각 구분하기 위한 속성이다.

## VUE 이벤트 핸들러

버튼을 누르면 기능을 실행하고 싶다고 가정하자.
자바스크립트에서는 `onclick=""` 이라는 이벤트 핸들러를 html 태그에 달았다.
뷰에서는 `@click=""`이라고 사용한다.
이렇게하면 따옴표 안에 자바스크립트를 자유롭게 입력할 수 있다.

### 코드가 길 경우 함수를 만들어서 사용하면 된다.

함수 만드는 자리는 정해져있다.

```vue

<script>
    export default {
        name: 'App',
        data() {
            return {
                신고수: 0,
                menus: ["Home", "Shop", "About"],
                products: ["역삼동원룸", "천호동원룸", "마포구원룸"]
            }
        },
        methods: {
            increase() {
                this.신고수++;
            },
        },
        components: {}
    }
</script>
```

`methods : {}` 항목을 신설해서 함수를 정의한다.
만들 때는 `함수이름(){}` 형식을 따르면 된다.
여기서 데이터를 가져다쓰고 싶으면 꼭 `this.데이터이름` 이라고 사용해야 한다.

## v-if

모달창을 구현하려면 상태를 구분해야할 필요가 있다.
html 태그 안에 `v-if="조건식"` 을 사용하면 조건식이 참일 때만 html을 보여준다.

```vue

<div v-if="조건이참이니 == true">
    <h4>조건이 참이면 이 내용이 보인다!</h4>
</div>
```

이렇게 하면 `조건이참이니` 데이터가 true일 때만 div를 보여준다.

## Components

HTML을 만들다보면 div 태그가 수십개 나온다.
이게 싫다면 컴포넌트를 만들어서 사용하면 된다.
원하는 html 덩어리를 한 글자로 축약할 수 있게 보와주는 문법이다.

### 만드는 방법

`작명.vue` 파일을 Components폴더(아무곳에나 두어도 됨) 안에 만든 후 그 안에 축약할 html을 작성한다.
`.veu` 파일은 항상 아래와 같은 형식으로 만들어져야 한다.

```vue

<template>
    <!--축약할 html 코드-->
</template>

<script>
    export default {
        name: '작명'
    }
</script>

<style>
    //    넣을 스타일
</style>
```

이 vue 파일을 하나의 컴포넌트라고 부른다.
그 다음 원하는 곳에서 `작명.vue` 파일을 1. import, 2. 등록, 3. 사용 하면 된다.

### 컴포넌트 사용 방법

1. import

```js
import DiscountBanner from "./src/components/DiscountBanner.vue";
```

2. 등록

```vue

<script>
    export default {
        components: {
            DiscountBanner
        }
    }
</script>
```

3. 사용

```html

<DiscountBanner></DiscountBanner>
```

이런 식으로 원하는 곳 아무데나 사용 가능하다.

## PROPS

자식이 부모가 가진 데이터를 쓰려면 PROPS 문법으로 데이터를 전송해줘야 한다.
`SOMETHING` 이라는 데이터를 `Modal.vue`로 보내보자.

```vue

<Modal :SOMETHING="SOMETHING"/>
```

이렇게 데이터바인딩 문법을 쓰면 데이터를 <Modal>로 보낼 수 있다.

데이터를 받는 자식 컴포넌트, Modal.vue 에서는 데이터를 받는다고 등록을 해줘야한다.

```vue

<script>
    export default {
        name: "Modal",
        props: {
            SOMETHING: Array
        }
    }
</script>
```

자식컴포넌트는 데이터를 받으면 위처럼 props로 데이터를 등록해야한다.
`props:{}` 입력 후 `{데이터이름 : 자료형}` 형식에 맞춰 작성하면 된다.

## Custom Event

자식컴포넌트가 부모가 가진 데이터를 바꾸고 싶을 때가 있다.
하지만 불가능하다.
애초에 다른 파일에 있는 데이터이기 때문이다.
그리고 props로 전해준 데이터도 변경할 수 없다.
read-only 데이터이기 때문에 변경이 불가능하다.

그래서 부모가 가진 데이터를 변경하려면 자식컴포넌트는 부모에게 메시지를 줘야한다.
부모가 메시지를 수신하면 부모쪽에서 데이터를 변경하도록 로직을 구성하면된다.
이걸 custom event 문법이라고 한다.

1. 자식은 `$emit(작명, 전달할자료)` 이렇게 부모에게 메시지를 보낼 수 있다. 부모까지 자료를 전달하고 싶으면 선택적으로 기입 가능하다.
2. 부모는 `@작명="데이터변경하는js코드"` 이렇게 메시지를 수신해서 원하는 데이터를 변경하도록 코드를 구성한다.

```vue
<!--자식 컴포넌트 CardButton.vue-->

<template>
    <div>
        <button @click="$emit('openModal')">버튼!</button>
    </div>
</template>
```
이렇게 작성하면 버튼을 눌렀을 때 부모 컴포넌트에게 메시지를 보내게 된다.

```vue
<template>
    <CardButton @openModal="JS코드입력"></CardButton>
</template>
```
그럼 부모 컴포넌트에서는 이벤트를 받고 원하는 로직을 수행하면 된다.

### 커스텀 이벤트로 부모에게 데이터 보내기
`$emit()`을 사용할 때 둘째 파라미터 자리에는 원하는 자료를 아무거나 입력 가능하다.
그러면 자료가 부모까지 전달된다.
```vue
<!--자식 컴포넌트 CardButton.vue-->

<template>
    <div>
        <button @click="$emit('openModal', 1)">버튼!</button>
    </div>
</template>
```

부모는 $event 라는 변수를 사용하면 자식이 보낸 자료가 담겨있다.
```vue
<template>
    <CardButton @openModal="JS코드입력; 보낸자료=$event"></CardButton>
</template>
```