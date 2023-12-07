<template>
  <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <form class="space-y-6" @submit.prevent="userLogin">
      <div>
        <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email</label>
        <div class="mt-2">
          <input
            id="email"
            name="email"
            type="email"
            autocomplete="email"
            required="true"
            class="peer block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-rose-500 sm:text-sm sm:leading-6"
          />
        </div>
      </div>

      <div>
        <div class="flex items-center justify-between">
          <label for="password" class="block text-sm font-medium leading-6 text-gray-900"
            >密码</label
          >
          <div class="text-sm">
            <a href="#/resetPassword" class="font-semibold text-rose-400 hover:text-rose-300">忘记密码?</a>
          </div>
        </div>
        <div class="mt-2">
          <input
            id="password"
            name="password"
            type="password"
            autocomplete="current-password"
            required="true"
            class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-rose-500 sm:text-sm sm:leading-6"
          />
        </div>
      </div>

      <div>
        <button
          class="flex w-full justify-center rounded-md bg-rose-400 active:bg-rose-200 hover:bg-rose-300 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm  focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-rose-600"
        >
          登录
        </button>
      </div>
    </form>

    <p class="mt-10 text-center text-sm text-gray-500">
      还没有账户🤔
      {{ ' ' }}
      <a
        @click="userLogin()"
        class="font-semibold leading-6 text-rose-400 hover:text-rose-300 hover:cursor-pointer"
        >点击注册！</a
      >
    </p>
  </div>
</template>

<script setup lang="ts">
import { UserLoginApi } from '@/api/user'

const obj = {
  user_id: '12345678',
  email: '123456@qq.com',
  password: '123456'
}

const userLogin = () => {
  UserLoginApi(obj).then((data) => {
    console.log(data)
  })
}
</script>

<style scoped></style>
