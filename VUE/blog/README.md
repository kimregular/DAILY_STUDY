## 라우터 세팅

```
npm install vue-router@4
```

vue-router를 설치한다.

라우터 설정 파일을 아래와같이 작성한다.

```js
// router 설정 파일
import {createWebHistory, createRouter} from "vue-router";
import import한경로 from "@/components/import한경로.vue";

const routes = [
    {
        path: "/원하는경로",
        component: import한경로
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
```

`main.js`에 라우터 설정 사용을 명시한다.

```js
// main.js
import router from "router경로";

createApp(App).use(router).mount("#app");
```

원하는 곳에
`<router-view></router-view>`
태그를 추가한다.
라우터로 구분된 페이지를 이제는 해당 태그가 있는 곳에 렌더링한다.

페이지 이동 링크를 만들고 싶다면
`<router-link to="/원하는경로"></router-link>`
태그를 추가한다.
a태그와 비슷하다.
`to=""` 안에 원하는 경로 설정이 가능하다.