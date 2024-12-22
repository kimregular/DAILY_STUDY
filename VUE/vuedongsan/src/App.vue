<template>

    <RoomInfo @closeModal="모달창열렸니 = false" :원룸들="원룸들" :누른거="누른거" :모달창열렸니="모달창열렸니"></RoomInfo>

    <div class="menu">
        <a v-for="menu in menus" :key="menu"> {{ menu }} </a>
    </div>

    <DiscountBanner />

    <OneRoomInfos @openModal="모달창열렸니 = true; 누른거 = $event" :원룸="원룸들[i]" v-for="(원룸, i) in 원룸들" :key="i"/>
</template>

<script>
import data from "./oneroom"
import DiscountBanner from "@/components/DiscountBanner.vue";
import RoomInfo from "@/components/RoomInfo.vue";
import OneRoomInfos from "@/OneRoomInfos.vue";

export default {
    name: 'App',
    data() {
        return {
            누른거: 0,
            원룸들: data,
            모달창열렸니: false,
            신고수: [0, 0, 0],
            menus: ["Home", "Shop", "About"],
            products: ["역삼동원룸", "천호동원룸", "마포구원룸"]
        }
    },
    methods: {
        increase(i) {
            this.신고수[i]++;
        },
        openRoomInfo(i) {
            this.모달창열렸니 = true;
            this.누른거 = i;
        }
    },
    components: {OneRoomInfos, RoomInfo, DiscountBanner}
}
</script>

<style>
body {
    margin: 0;
}

div {
    box-sizing: border-box;
}

.menu {
    background: darkslateblue;
    padding: 15px;
    border-radius: 5px;
}

.menu a {
    color: white;
    padding: 10px;
}


</style>
