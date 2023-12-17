<script setup lang="ts">
import { onMounted } from 'vue'
import { RouterView } from 'vue-router'
import { useThemeStore } from './stores/theme'
onMounted(() => {
  const themeStore = useThemeStore()
  document.documentElement.setAttribute('data-theme', themeStore.getTheme())
})
</script>

<template>
  <RouterView />
</template>
