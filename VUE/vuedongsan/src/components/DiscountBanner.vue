<script>
export default {
    name: "DiscountBanner",
    data() {
        return {
            salePercent: 20
        }
    },
    watch: {
        salePercent(value) {
            if (value === 0) {
                clearInterval(this.interval);
            }
        }
    },
    mounted() {
        this.interval = setInterval(() => {
            this.salePercent--;
        }, 1000);
    }
}
</script>

<template>
    <div class="discount" :class="{hide : salePercent === 0}">
        <h4>지금 결제하면 {{ salePercent }}% 할인</h4>
    </div>
</template>

<style scoped>
.discount {
    background: #eeeeee;
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
}

.hide {
    display: none;
}
</style>