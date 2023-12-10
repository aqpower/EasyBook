<template>
  <h2 class="text-base-content mt-1 text-center text-2xl font-bold leading-9 tracking-tight">
    请输入账户的邮箱号:
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
        class="h-9 min-h-0 btn btn-primary flex w-full justify-center rounded-md
          hover:bg-rose-300 px-3 py-1.5 
           leading-6 text-white shadow-sm 
         focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2
          focus-visible:outline-rose-600">
          确定
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped></style>
