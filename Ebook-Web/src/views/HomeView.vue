<script setup lang="ts">
import httpInstance from '../utils/http'

function f1(){
  httpInstance.get('/api/v1/hello')

}



</script>

<template>
  <h1 class="text-3xl font-bold underline" @click="f1()">Hello world!</h1>
</template>
