<template>
    <div class="header">
        <ul class="header-button-left">
            <li>Cancel</li>
        </ul>
        <ul class="header-button-right">
            <li>Next</li>
        </ul>
        <img src="./assets/logo.png" class="logo"/>
    </div>

    <button class="btn0" disabled>버튼 0</button>
    <button class="btn1">버튼 1</button>
    <button class="btn2">버튼 2</button>
    <div class="index0">내용 0</div>
    <div class="index1 hide">내용 1</div>
    <div class="index2 hide">내용 2</div>

    <ContainerVue :posts="posts"/>
    <button @click="more()">더보기</button>



    <div class="footer">
        <ul class="footer-button-plus">
            <input type="file" id="file" class="inputfile"/>
            <label for="file" class="input-plus">+</label>
        </ul>
    </div>
</template>

<script>
import ContainerVue from "@/components/ContainerVue.vue";
import postData from "@/assets/postData";
import axios from "axios";

export default {
    name: 'App',
    components: {
        ContainerVue
    },
    data() {
        return {
            posts: postData,
            click : 0
        }
    },
    watch : {
      click(value) {
          if(value === 2) this.click = 0;
      }
    },
    methods: {
        more() {
            axios.get(`https://codingapple1.github.io/vue/more${this.click++}.json`)
                .then((result) => {
                    this.posts.push(result.data);
                });
        }
    }
}
</script>

<style>
body {
    margin: 0;
}

ul {
    padding: 5px;
    list-style-type: none;
}

.logo {
    width: 22px;
    margin: auto;
    display: block;
    position: absolute;
    left: 0;
    right: 0;
    top: 13px;
}

.header {
    width: 100%;
    height: 40px;
    background-color: white;
    padding-bottom: 8px;
    position: sticky;
    top: 0;
}

.header-button-left {
    color: skyblue;
    float: left;
    width: 50px;
    padding-left: 20px;
    cursor: pointer;
    margin-top: 10px;
}

.header-button-right {
    color: skyblue;
    float: right;
    width: 50px;
    cursor: pointer;
    margin-top: 10px;
}

.footer {
    width: 100%;
    position: sticky;
    bottom: 0;
    padding-bottom: 10px;
    background-color: white;
}

.footer-button-plus {
    width: 80px;
    margin: auto;
    text-align: center;
    cursor: pointer;
    font-size: 24px;
    padding-top: 12px;
}

.sample-box {
    width: 100%;
    height: 600px;
    background-color: bisque;
}

.inputfile {
    display: none;
}

.input-plus {
    cursor: pointer;
}

#app {
    box-sizing: border-box;
    font-family: "consolas";
    margin-top: 60px;
    width: 100%;
    max-width: 460px;
    margin: auto;
    position: relative;
    border-right: 1px solid #eee;
    border-left: 1px solid #eee;
}
</style>
