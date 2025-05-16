<script setup>

import {onMounted, ref} from "vue";

const isLogin = ref(false);

onMounted(() => {
  if (localStorage.getItem('token')) {
    isLogin.value = true;
  }
});

const doLogout = () => {
  localStorage.clear();
  window.location.reload();
}

</script>

<template>
  <v-app-bar app dark>
    <v-container>
      <v-row align="center">
        <v-col class="d-flex justify-end">
          <v-btn v-if="!isLogin" :to="{path:'/member/create'}">회원가입</v-btn>
          <v-btn v-if="!isLogin" :to="{path: '/member/login'}">로그인</v-btn>
          <v-btn v-if="isLogin" @click="doLogout()">로그아웃</v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-app-bar>
</template>

<style scoped>

</style>