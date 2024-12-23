import {createWebHistory, createRouter} from "vue-router";
import PostList from "@/components/PostList.vue";
import MainHome from "@/components/MainHome.vue";
import PostDetail from "@/components/PostDetail.vue";

const routes = [
    {
        path: "/list",
        component: PostList,
        props: true
    }, {
        path: "/",
        component: MainHome
    }, {
        path: "/detail/:id",
        component: PostDetail,
        props: true
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;