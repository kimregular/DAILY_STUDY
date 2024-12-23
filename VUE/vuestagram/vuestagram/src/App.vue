<template>
    <div class="header">
        <ul class="header-button-left">
            <li v-if="step != 0" @click="cancelUpload($event)">Cancel</li>
        </ul>
        <ul class="header-button-right">
            <li v-if="step==1" @click="step = 2">Next</li>
            <li v-if="step==2" @click="publish($event)">Publish</li>
        </ul>
        <img src="./assets/logo.png" class="logo"/>
    </div>

    <ContainerVue :posts="posts" :step="step" :newImage="newImage" @write="content = $event"/>

    <div class="footer">
        <ul class="footer-button-plus">
            <input type="file" id="file" class="inputfile" @change="upload($event)"/>
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
            step: 0,
            posts: postData,
            click: 0,
            newImage: '',
            content : ''
        }
    },
    watch: {
        click(value) {
            if (value === 2) this.click = 0;
        }
    },
    methods: {
        more() {
            axios.get(`https://codingapple1.github.io/vue/more${this.click++}.json`)
                .then((result) => {
                    this.posts.push(result.data);
                });
        },
        upload(e) {
            let image = e.target.files[0];
            let imageUrl = URL.createObjectURL(image);
            this.step = 1;
            this.newImage = imageUrl;
        },
        cancelUpload() {
            this.newImage = '';
            this.step = 0;
        },
        publish(e) {
            console.log(e);
            const newPost = {
                name: "vue Uploader",
                userImage: this.newImage,
                postImage: this.newImage,
                likes: 0,
                date: this.$_getMonthAndDate(),
                liked: false,
                content: this.content,
                filter: "dummy",
            };
            this.posts.unshift(newPost);
            this.step = 0;
        },
        $_getMonthAndDate: () => {
            const monthName = new Intl.DateTimeFormat('en-US', { month: 'short' }).format(new Date());
            return `${monthName} ${String(new Date().getDate()).padStart(2, '0')}`;
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
