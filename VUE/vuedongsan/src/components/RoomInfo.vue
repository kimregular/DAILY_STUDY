<script>
export default {
    name: "RoomInfo",
    data() {
      return {
          month : 1,
      }
    },
    watch : {
      month(value) {
          if (isNaN(value)) {
              alert("숫자만 입력하세요");
              this.month = 1;
          }
      }
    },
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
<!--            <input @input="this.month = $event.target.value">-->
            <input v-model.number="month">
            <p> {{ month }} 개월 선택함 : {{ 원룸들[누른거].price * month }}원</p>
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