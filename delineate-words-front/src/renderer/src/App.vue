<template>
  <!-- 以前这里写死的“这是工作区”可以删掉了 -->
  <!-- 加上这个标签，Home.vue 的内容就会自动在这里显示出来！ -->
  <router-view></router-view>
</template>

<script setup>
// 你之前写的 pinia 测试代码可以保留，不影响
import { useStore } from '@/store'
const num = useStore();
console.log(num);
</script>

<style>
/* 这里可以写一些全局的样式，暂时不写也行 */
</style>