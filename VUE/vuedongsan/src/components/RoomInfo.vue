<script>
export default {
    name: "RoomInfo",
    props : {
        원룸들 : Array,
        누른거: Number,
        모달창열렸니 : Boolean
    }
}
</script>

<template>
    <div class="black-bg" v-if="모달창열렸니">
        <div class="white-bg">
            <h4>{{ 원룸들[누른거].title }}</h4>
            <img :src="원룸들[누른거].image" alt="방 사진"/>
            <p>{{ 원룸들[누른거].content }}</p>
            <p>{{ 원룸들[누른거].price }}원</p>
            <button @click="$emit('closeModal')">닫기</button>
        </div>
    </div>
</template>

<style scoped>
.black-bg {
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    position: fixed;
    padding: 20px;
}

.white-bg {
    width: 100%;
    background: white;
    border-radius: 8px;
    padding: 20px;
}

</style>