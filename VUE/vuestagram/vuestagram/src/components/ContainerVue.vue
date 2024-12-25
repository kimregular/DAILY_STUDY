<script>
import PostVue from "@/components/PostVue.vue";
import FilterBox from "@/components/FilterBox.vue";

export default {
    name: "ContainerVue",
    emits: ['write'],
    components: {
        PostVue,
        FilterBox
    },
    data() {
        return {
            filterTypes: [
                "aden", "_1977", "brannan", "brooklyn", "clarendon", "earlybird", "gingham", "hudson",
                "inkwell", "kelvin", "lark", "lofi", "maven", "mayfair", "moon", "nashville", "perpetua",
                "reyes", "rise", "slumber", "stinson", "toaster", "valencia", "walden", "willow", "xpro2"
            ],
        }
    },
    props: {
        posts: Array,
        step: Number,
        newImage: String
    }
}
</script>

<template>
    <div v-if="step==0">
        <PostVue :posts="posts"/>
        <button @click="more()">더보기</button>
    </div>

    <!-- 필터선택페이지 -->
    <div v-if="step==1">
        <div class="upload-image" :style="{backgroundImage : `url(${newImage})`}"></div>
        <div class="filters">
            <FilterBox :newImage="newImage" :filterType="filterType" v-for="(filterType, i) in filterTypes" :key="i">
            </FilterBox>
        </div>
    </div>

    <!-- 글작성페이지 -->
    <div v-if="step==2">
        <div class="upload-image" :style="{backgroundImage : `url(${newImage})`}"></div>
        <div class="write">
            <textarea class="write-box" @input="$emit('write', $event.target.value)">write!</textarea>
        </div>
    </div>
</template>

<style scoped>
.upload-image {
    width: 100%;
    height: 450px;
    background: cornflowerblue;
    background-size: cover;
}

.filters {
    overflow-x: scroll;
    white-space: nowrap;
}

.filter-1 {
    width: 100px;
    height: 100px;
    background-color: cornflowerblue;
    margin: 10px 10px 10px auto;
    padding: 8px;
    display: inline-block;
    color: white;
    background-size: cover;
}

.filters::-webkit-scrollbar {
    height: 5px;
}

.filters::-webkit-scrollbar-track {
    background: #f1f1f1;
}

.filters::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 5px;
}

.filters::-webkit-scrollbar-thumb:hover {
    background: #555;
}

.write-box {
    border: none;
    width: 90%;
    height: 100px;
    padding: 15px;
    margin: auto;
    display: block;
    outline: none;
}
</style>