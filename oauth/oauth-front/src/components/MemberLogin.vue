<script setup>

import {ref} from "vue";
import axios from "axios";

const email = ref('');
const password = ref('');

const googleOAuthInfo = {
  googleUrl : "https://accounts.google.com/o/oauth2/auth",
  googleClientId : "212981540254-68busrutb7tt68gn355kabn68d5oa0vh.apps.googleusercontent.com",
  googleRedirectUri : "http://localhost:3000/oauth/google/redirect",
  googleScope : "openid email profile"
}

const memberLogin = async () => {
  const loginData = {
    email: email.value,
    password: password.value
  }

  let response = await axios.post("http://localhost:8080/member/doLogin", loginData);
  const {token} = response.data;
  localStorage.setItem('token', token);
  window.location.href = "/";
}

const googleLogin = () => {
  window.location.href = `${googleOAuthInfo.googleUrl}?client_id=${googleOAuthInfo.googleClientId}&redirect_uri=${googleOAuthInfo.googleRedirectUri}&scope=${googleOAuthInfo.googleScope}&response_type=code`;
}

const kakaoLogin = () => {
  alert("kakao!");
}

</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col md="4">
        <v-card>
          <v-card-title class="text-h5 text-center">로그인</v-card-title>
          <v-card-text>
            <v-form>
              <v-text-field label="email" v-model="email"></v-text-field>
              <v-text-field label="password" v-model="password" type="password"></v-text-field>
              <v-btn color="primary" block @click="memberLogin()">로그인</v-btn>
            </v-form>
            <br>
            <v-row>
              <v-col cols="6" class="d-flex justify-center" @click="googleLogin()">구글 로그인</v-col>
              <v-col cols="6" class="d-flex justify-center" @click="kakaoLogin()">카카오 로그인</v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>