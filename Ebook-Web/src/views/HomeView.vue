<script setup lang="ts">
import EasyHead from '@/layouts/EasyHead.vue'
import LeftBar from '@/layouts/LeftBar.vue'
import CardList from '@/components/CardList.vue'
</script>

<template>
  <div class="flex w-full">
    <LeftBar></LeftBar>
    <div class="ml-64">
      <EasyHead></EasyHead>
      <RouterView class="mt-20"></RouterView>
    </div>
  </div>
</template>
