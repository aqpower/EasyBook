<template>
  <div class="sm:mx-auto sm:w-full sm:max-w-sm">
    <img class="mx-auto h-60 w-auto" src="../assets/logo-text-nobg.png" alt="Your Company" />
    <h2 class="mt-1 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
      登录你的账户
    </h2>
  </div>

  <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <form class="space-y-6" action="#" method="POST">
      <div>
        <label for="email" class="block text-sm font-medium leading-6 text-gray-900"
          >邮箱地址</label
        >
        <div class="mt-2">
          <input
            id="email"
            name="email"
            type="email"
            autocomplete="email"
            required="true"
            class="peer block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>

      <div>
        <div class="flex items-center justify-between">
          <label for="password" class="block text-sm font-medium leading-6 text-gray-900"
            >密码</label
          >
          <div class="text-sm">
            <a href="#" class="font-semibold text-rose-400 hover:text-rose-300">忘记密码?</a>
          </div>
        </div>
        <div class="mt-2">
          <input
            id="password"
            name="password"
            type="password"
            autocomplete="current-password"
            required="true"
            class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>

      <div>
        <button
          type="submit"
          class="flex w-full justify-center rounded-md bg-rose-400 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-rose-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 f ocus-visible:outline-indigo-600"
        >
          登录
        </button>
      </div>
    </form>

    <p class="mt-10 text-center text-sm text-gray-500">
      还没有账户🤔
      {{ ' ' }}
      <a href="#" class="font-semibold leading-6 text-rose-400 hover:text-rose-300">点击注册！</a>
    </p>
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped></style>
