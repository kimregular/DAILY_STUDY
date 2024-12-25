<script>
export default {
    name: "FilterBox",
    props: {
        newImage: String
    }
}

</script>

<template>
    <div class="filter-item" :style="{backgroundImage : `url(${newImage})`}"></div>
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