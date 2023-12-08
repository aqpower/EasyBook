<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-3 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
      <img class="mx-auto h-60 w-auto" src="../assets/logo-text-nobg.png" alt="Your Company" />
    </div>

    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<style scoped></style>
