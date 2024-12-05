<template>
    <div class="foot-container">
        <div class="author">EcoNest 生态交易平台</div>
    </div>
</template>

<script>
    export default {
        name: "FooterComponent"
    }
</script>

<style scoped>
    .foot-container {
        width: 100%;
        display: flex;
        justify-content: center;
        height: 40px;
        padding-top: 10px;
    }

    .author {
        color: #999999;
        font-size: 14px;
    }
</style>