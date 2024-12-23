<script>
import PostVue from "@/components/PostVue.vue";

export default {
    name : "ContainerVue",
    components : {
        PostVue
    },
    props : {
        posts : Array
    }
}
</script>

<template>
    <div>
        <PostVue :posts="posts"/>
    </div>
</template>

<style scoped>

</style>