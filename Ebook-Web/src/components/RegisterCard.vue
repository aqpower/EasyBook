<template>
  <h2 class="text-base-content mt-1 text-center text-2xl font-bold leading-9 tracking-tight">
    {{ tipText }}
  </h2>
  <div v-show="!emailSubmit" class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <div class="space-y-6">
      <div>
        <label for="email" class="text-base-content block text-sm font-medium leading-6"
          >Email</label
        >
        <input
          id="email"
          v-model="emailInput"
          placeholder="请输入您的邮箱号"
          class="mt-2 input input-bordered input-primary w-full sm:text-sm sm:leading-5 h-10"
        />
      </div>
      <div>
        <button
          @click="emailVerify"
          class="min-h-0 h-9 btn btn-primary flex w-full justify-center rounded-md hover:bg-rose-300 px-3 py-1.5 leading-6 text-white shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-rose-600"
        >
          <span v-if="loadingShow" class="loading loading-bars loading-xs h-6"></span>
          <span v-show="!loadingShow">点击发送验证码</span>
        </button>
      </div>
    </div>
  </div>
  <div v-show="emailSubmit" class="mt-5 sm:mx-auto sm:w-full sm:max-w-xl">
    <p class="w-full">
      ^_^ 验证码已经成功发送至 <strong> {{ emailInput }} </strong>请在下方输入收到的验证码~
    </p>
  </div>
  <div v-show="emailSubmit" class="space-y-6 sm:mx-auto sm:w-full sm:max-w-sm mt-6">
    <div class="flex justify-center items-center">
      <input
        id="email"
        v-model="verifyCodeInput"
        placeholder="请输入验证码"
        class="input input-bordered input-primary w-full sm:text-sm sm:leading-5 h-10"
      />
      <div
        class="w-10/12 ml-3 h-9 badge flex"
        :class="{
          ' text-white hover:cursor-pointer hover:bg-rose-300 active:scale-90 badge-primary':
            timeCount == 0,
          'badge-outline': timeCount != 0
        }"
        @click="timeCount == 0 ? sendCode() : 1"
      >
        <Icon
          v-show="timeCount != 0"
          class="h-10 w-5 mr-1"
          icon="svg-spinners:clock"
          color="#fb7185"
        />
        <p v-show="timeCount != 0">{{ timeCount }} 秒后可重新发送</p>
        <span v-if="loadingCodeShow" class="loading loading-bars loading-xs h-6"></span>
        <p v-show="timeCount == 0">点击重新发送</p>
      </div>
    </div>
    <button
      class="h-9 min-h-0 btn btn-primary flex w-full justify-center rounded-md hover:bg-rose-300 px-3 py-1.5 leading-6 text-white shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-rose-600"
    >
      <span @click="checkVerifyCode">验证</span>
    </button>
  </div>
</template>

<script setup lang="ts">
import { emailVerifyApi } from '@/api/user'
import { ref } from 'vue'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
import { Icon } from '@iconify/vue'
const emailInput = ref('')
const verifyCodeInput = ref('')
const tipText = ref('注册账号')
const emailSubmit = ref(false)
const dialog = useCommandComponent(InfoDialog)
const loadingShow = ref(false)
const loadingCodeShow = ref(false)
const timeCount = ref(6)
let timer = null

const isValidEmail = (email: string) => {
  // 电子邮箱的正则表达式
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailRegex.test(email)
}

const emailVerify = () => {
  loadingShow.value = true
  if (!isValidEmail(emailInput.value)) {
    dialog({
      title: '💢',
      content: '邮箱格式错误',
      btnContent: '👌我现在就改正',
      onClose: () => {
        loadingShow.value = false
      }
    })
  } else {
    sendCode()
  }
}

const startTimer = () => {
  const timer = setInterval(() => {
    if (timeCount.value > 0) {
      timeCount.value-- // 每秒减1
    } else {
      clearInterval(timer) // 停止计时器
      // 执行其他操作或者调用其他方法
      executeAnotherOperation()
    }
  }, 1000) // 每1000毫秒（即1秒）执行一次

  return timer
}

const executeAnotherOperation = () => {
  // 在倒计时结束后执行的操作
}

const sendCode = () => {
  emailVerifyApi(emailInput.value)
    .then(() => {
      dialog({
        title: '🎉',
        content: '验证码已经发送到你的邮箱，请注意查收',
        btnContent: '👌',
        onClose: () => {
          loadingShow.value = false
          emailSubmit.value = true
        }
      })
      timeCount.value = 6
      startTimer()
    })
    .catch(() => {
      loadingShow.value = false
    })
}

const checkVerifyCode = () => {}
</script>

<style scoped></style>
