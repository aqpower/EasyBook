<script setup lang="ts">
import EasyHead from '@/layouts/EasyHead.vue'
import LeftBar from '@/layouts/LeftBar.vue'




</script>

<template>
  <div class="flex w-full">
    <LeftBar></LeftBar>
    <div class="ml-64 w-full">
      <!-- <EasyHead></EasyHead> -->
      <RouterView class="mt-5"></RouterView>
    </div>
  </div>
</template>
