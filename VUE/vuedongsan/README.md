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

## v-model

사용자가 입력한 정보에 따라서 실시간으로 바뀌는 기능을 구현하고 싶다.
당연히 `data()` 로 저장해두고 필요할 때 `{{데이터바인딩}}` 기능을 사용하면 된다.
그래서 사용자가 input 에 입력한 값을 data로 저장하고 싶다면 아래와 같이 작성하면 된다.

```vue

<template>
    <input @input="month = $event.target.value">
</template>

<script>
    export default {
        data() {
            return {
                month: 0
            }
        }
    }
</script>
```

1. `@input` 은 input에 입력할 때 동작하는 이벤트핸들러이다. @change 같은 비슷한 기능을 가진 핸들러도 참고
2. `$event`는 vue가 제공하는 특별한 변수이다. `event object`를 의미한다. 자바스크립트 이벤트리스너에서 `addEventListener('click', function(e){})` e와 같은
   의미이다.
3. 밑에 정의한 month라는 data항목에 저장하라는 코드이다. 그러면 이제 input에 뭔가 값이 입력될 때마다 month에 입력한 값이 저장된다.

다른 방법도 있다.

```vue

<template>
    <input @input="month = $event.target.value">
</template>
```

이 코드를

```vue

<template>
    <input v-model="month">
</template>
```
이렇게 수정해도 된다. 
v-model은 입력된 값을 data로 바로 저장해라 라는 문법이다.
따옴표 안에 미리 저장해둔 data 변수 이름을 적어주면 된다.

## 데이터를 감시하고 싶으면 watch
```vue
<script>
    export default {
        data() {
            return {
                month : 1
            }
        },
       watch : {
            month() {
                // month가 변경될 때 실행할 코드
            }
       }
    }
</script>
```

watch 항목에는 감시자들을 만들 수 있다.
그리고 여기에는 함수를 넣을 수 있다.
함수명을 내가 감시하고 싶은 데이터명으로 작성해야만 한다.
month() 라고 함수명을 지으면 month 데이터 감시자가 되는 것이다.
그리고 그 함수 안에 month가 변할 때 마다 실행하고 싶은 코드를 작성하면 된다.
참고로 month() 안에는 파라미터를 두 개 까지 넣을 수 있다.
첫 번째는 변경될 값, 두 번째는 변경 전 값을 의미한다.

## vue 에서 제공하는 <Transition> 애니메이션
1. 애니메이션 주고 싶은 UI를 `<Transition name="작명"></Transition>`으로 감싼다.
2. CSS로 다음과 같이 스타일을 주면 된다.
```css
.작명-enter-from {애니메이션 동작 전 상태}
.작명-enter-active {애니메이션 동작 중 상태, 대부분 transition}
.작명-enter-to {애니메이션 동작 후 상태}
```
* 퇴장 시에는 enter를 leave로 바꾸면 된다.

예시로 투명도가 0에서 1로 변하는 애니메이션이다.
```html
<Transition name="fade">
   <div>이건 투명하다가 눈에 보여요~</div>
</Transition>
```

```css
.fade-enter-to{
   opacity: 0;
}
.fade-enter-active {
   transition : all, 1s;
}
.fade-enter-to {
   opacity: 1;
}
```

## VUE 라이프사이클
컴포넌트가 생성되고 사라지고 업데이트되는 과정을 설명하는 단어이다.
1. beforeCreate
2. created
3. beforeMount
4. mounted
5. beforeUpdate
6. updated
7. beforeUnmount
8. unmounted

이 순서대로 컴포넌트가 생성되고 사라진다고 이해하면 된다.
이걸 왜 알아야 하나면 `lifecycle hook`이란 기능을 사용하기 위해서이다.

위에 열거한 순서대로 해당 과정에 실행하고 싶은 기능이 있다면 맞춰서 코드를 작성하면 된다.
예를 들어 mount 되기 전에 ajax 요청으로 서버에서 데이터를 가져오던가, update 하기 전에 코드를 실행해서 데이터를 검증하거나 하는 식이다.

예시는 아래와 같다.

```vue
<script>
   export default {
       data() {
           
       },
      mounted() {
           // 마운트 된 후 실행할 동작 작성
      }
   }
</script>
```

위와 같이 코딩하면 mount 되고 난 이후 작성된 코드를 실행한다.

특히 서버가 있다면 서버에서 데이터 가져오는 일이 잦은데 이 코드를 mounted() 혹은 created() 여기에 보통 작성한다.