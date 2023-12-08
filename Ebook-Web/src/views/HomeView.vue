<script setup lang="ts">
import httpInstance from '../utils/http'

  httpInstance.get('/hello')


</script>

<template>
  <h1 class="text-3xl font-bold underline">Hello world!</h1>
</template>
