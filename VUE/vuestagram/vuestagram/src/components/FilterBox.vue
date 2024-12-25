<script>
export default {
    name: "FilterBox",
    props: {
        newImage: String,
        filterType : String
    }
}

</script>

<template>
    <div class="filter-item" :class="filterType" :style="{backgroundImage : `url(${newImage})`}">
        <span>{{filterType}}</span>
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