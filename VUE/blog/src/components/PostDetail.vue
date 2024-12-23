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
        <h4>{{ postData[postId].title }}</h4>
        <h5>{{ postData[postId].content }}</h5>
        <p>{{ postData[postId].date }}</p>
    </div>
</template>

<style scoped>

</style>