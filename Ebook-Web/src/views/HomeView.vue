<script setup lang="ts">
import EasyHead from '@/layouts/EasyHead.vue'
import LeftBar from '@/layouts/LeftBar.vue'
import CardList from '@/components/CardList.vue'
</script>

<template>
  <EasyHead></EasyHead>

  <div class="flex w-full">
    <LeftBar></LeftBar>
    <CardList class="ml-64"></CardList>
  </div>
</template>
