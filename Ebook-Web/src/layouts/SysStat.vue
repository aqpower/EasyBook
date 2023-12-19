<template>
  <div>
    <div class="flex justify-center items-center flex-col m-3">
      <TodayPost></TodayPost>
      <WelcomeAvatarVue class="mt-8"></WelcomeAvatarVue>
    </div>
  </div>
</template>

<script setup lang="ts">
import TodayPost from '@/components/TodayPost.vue'
import WelcomeAvatarVue from '@/components/WelcomeAvatar.vue';
</script>

<style scoped></style>
