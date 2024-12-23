<script>
export default {
    name: "PostDetail",
    data() {
        return {
            postId: this.$route.params.id
        }
    },
    props: {
        posts: Array
    }
}
</script>

<template>
    <div>
        <h4>{{ posts[postId].title }}</h4>
        <h5>{{ posts[postId].content }}</h5>
        <p>{{ posts[postId].date }}</p>
    </div>
</template>

<style scoped>

</style>