<template>
    <div>
        <img class="room-img" :src="원룸.image" alt="원룸 이미지">
        <h4 @click="send">{{ 원룸.title }}</h4>
        <p>{{ 원룸.price }}원</p>
    </div>
</template>
<script>
export default {
    name: 'OneRoomInfos',
    props: {
        원룸: Object
    },
    methods : {
        send() {
            this.$emit('openModal', this.원룸.id)
        }
    }
}
</script>
<style>
body {
    margin: 0;
}

div {
    box-sizing: border-box;
}

.menu a {
    color: white;
    padding: 10px;
}

.room-img {
    width: 100%;
    margin-top: 40px;
}


</style>