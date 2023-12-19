<script setup lang="ts">
import EasyHead from '@/layouts/EasyHead.vue'
import LeftBar from '@/layouts/LeftBar.vue'
</script>

<template>
  <div class="flex w-full">
    <LeftBar></LeftBar>
    <div class="ml-64 w-full flex flex-col" style="height: 100vh">
      <EasyHead></EasyHead>
      <RouterView class="flex-1 overflow-scroll"></RouterView>
    </div>
  </div>
</template>
