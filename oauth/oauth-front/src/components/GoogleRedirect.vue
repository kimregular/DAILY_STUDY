<script setup >
import {onMounted} from "vue";
import axios from "axios";

onMounted(() => {
  const code = new URL(window.location.href).searchParams.get('code');
  sendCodeToServer(code);
})

const sendCodeToServer = async (code) => {
  const response =  await axios.post("http://localhost:8080/member/google/doLogin", {code})
  const {token} = response;
  localStorage.setItem('token', token);
  window.location.href = "/";
}
</script>

<template>
<div>로그인 진행 중</div>
</template>

<style scoped>

</style>