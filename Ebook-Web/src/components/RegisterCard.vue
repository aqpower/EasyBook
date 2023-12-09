<template>
    <h2 class="text-base-content mt-1 text-center text-2xl font-bold leading-9 tracking-tight">
      请输入你的邮箱号:
    </h2>
    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <div class="space-y-6">
        <div>
          <label for="email" class="text-base-content block text-sm font-medium leading-6"
            >Email</label
          >
          <div class="mt-2">
            <input
              id="email"
              class="input input-bordered input-primary w-full 
               sm:text-sm sm:leading-5 h-10"
            />
          </div>
        </div>
        <div>
          <button
            class="flex w-full justify-center rounded-md bg-rose-400 active:bg-rose-200 hover:bg-rose-300 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-rose-600"
          >
            点击发送验证码
          </button>
        </div>
      </div>
    </div>
  </template>
  

<script setup lang="ts">

</script>

<style scoped>

</style>