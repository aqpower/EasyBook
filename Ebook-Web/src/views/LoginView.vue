<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-3 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
    <img class="mx-auto h-60 w-auto" src="../assets/logo-text-nobg.png" alt="Your Company" />
    <h2 class="mt-1 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
      登录你的账户
    </h2>
  </div>
    <LoginCard></LoginCard>
  </div>
</template>

<script setup lang="ts">
import LoginCard from '@/components/LoginCard.vue';
</script>

<style scoped>

</style>