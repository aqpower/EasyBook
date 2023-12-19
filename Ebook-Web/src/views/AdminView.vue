<template>
  <AdminHead></AdminHead>
  <RouterView class="mt-4 mb-8"></RouterView>
  <AdminFooter></AdminFooter>
</template>

<script setup lang="ts">
import AdminFooter from '@/layouts/AdminFooter.vue';
import AdminHead from '@/layouts/AdminHead.vue'
</script>

<style scoped></style>
