<script>
export default {
    name: "PostList",
    props: {
        posts: Array
    }
}
</script>

<template>
    <h2>Post List</h2>
    <div v-for="post in posts" :key="post">
        <router-link :to="`/detail/${post.number}`">
            <h5>{{ post.title }}</h5>
            <p>{{ post.date }}</p>
        </router-link>
    </div>
</template>

<style scoped>

</style>