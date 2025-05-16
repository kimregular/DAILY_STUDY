<script setup>

import {ref} from "vue";
import axios from "axios";

const email = ref('');
const password = ref('');

const memberCreate = async () => {
  const registerData = {
    email: email.value,
    password: password.value
  }

  await axios.post("http://localhost:8080/member/create", registerData);
  window.location.href = "/";
}

</script>

<template>
  <v-container>
    <v-row justify="center">
      <v-col md="6">
        <v-card>
          <v-card-title class="text-h5 text-center">회원가입</v-card-title>
          <v-card-text>
            <v-form>
              <v-text-field label="email" v-model="email"></v-text-field>
              <v-text-field label="password" v-model="password" type="password"></v-text-field>
              <v-btn color="primary" block @click="memberCreate()">등록</v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>