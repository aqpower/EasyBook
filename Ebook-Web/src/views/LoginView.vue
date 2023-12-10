<template>
  <div
    class="flex flex-col min-h-full flex-1 justify-center px-6 py-9 lg:px-8"
  >
    <div class="sm:mx-auto sm:w-full sm:max-w-sm basis-1/4">
      <img class="mx-auto h-60 w-auto" src="../assets/logo-text-nobg.png" alt="Your Company" />
    </div>
    <div class="basis-3/4">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup lang="ts">
import { RouterView, onBeforeRouteUpdate } from 'vue-router'

onBeforeRouteUpdate((to, from) => {
  console.log('路由发生变化啦！从', from.fullPath, '到', to.fullPath)
})
</script>

<style scoped></style>
