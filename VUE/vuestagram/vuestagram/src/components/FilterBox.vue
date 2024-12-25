<script>
export default {
    name: "FilterBox",
    props: {
        newImage: String,
        filterType : String
    },
    methods : {
        fire() {
            this.emitter.emit('filteredImage', '데이터전송!');
        }
    }
}

</script>

<template>
    <div class="filter-item" :class="filterType" :style="{backgroundImage : `url(${newImage})`}">
        <slot></slot>
        <button @click="fire">버튼</button>
    </div>
</template>

<style>
.filter-item {
    width: 100px;
    height: 100px;
    margin: 10px 10px 10px auto;
    padding: 8px;
    display: inline-block;
    color: white;
    background-size: cover;
    background-position: center;
}
</style>