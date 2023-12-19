<template>
  <h2 class="text-base-content mt-1 text-center text-2xl font-bold leading-9 tracking-tight">
    登录你的账户
  </h2>
  <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
    <div class="space-y-6">
      <div>
        <div class="flex items-center justify-between">
          <label for="email" class="block text-sm font-medium leading-6 text-base-content"
            >账户</label
          >
          <div class="text-sm">
            <a href="#/account/adminLogin" class="font-semibold text-rose-400 hover:text-rose-300"
              >管理员登录</a
            >
          </div>
        </div>
        <div class="mt-2">
          <input
            id="email"
            v-model="emailIdInput"
            autocomplete="off"
            placeholder="请输入邮箱或用户ID"
            class="input input-bordered input-primary w-full sm:text-sm sm:leading-5 h-10"
          />
        </div>
      </div>

      <div>
        <div class="flex items-center justify-between">
          <label for="password" class="block text-sm font-medium leading-6 text-base-content"
            >密码</label
          >
          <div class="text-sm">
            <a
              href="#/account/resetPassword"
              class="font-semibold text-rose-400 hover:text-rose-300"
              >忘记密码?</a
            >
          </div>
        </div>
        <div class="mt-2">
          <input
            v-model="passwordInput"
            id="password"
            type="password"
            placeholder="请输入密码"
            class="input input-bordered input-primary w-full sm:text-sm sm:leading-5 h-10"
          />
        </div>
      </div>

      <div>
        <button
          class="min-h-0 h-9 btn btn-primary flex w-full justify-center rounded-md px-3 py-1.5 leading-6 shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2"
          @click="userLogin"
        >
          <span v-if="loadingShow" class="loading loading-bars loading-xs h-6"></span>
          <span v-if="!loadingShow" class="h-6">登录</span>
        </button>
      </div>
    </div>

    <p class="mt-3 text-base-content text-center text-sm">
      还没有账户🤔
      {{ ' ' }}
      <a
        href="#/account/register"
        class="font-semibold leading-6 text-rose-400 hover:text-rose-300 hover:cursor-pointer"
        >点击注册！</a
      >
    </p>
  </div>
  <InfoDialog
    :visible="dialogOpen"
    :title="dialogTitle"
    :content="dialogContent"
    @update:visible="dialogOpen = $event"
    :btnContent="dialogBtnContent"
    :onClose="dialogCloseHandler"
  ></InfoDialog>
</template>

<script setup lang="ts">
import { UserLoginApi } from '@/api/user'
import { ref } from 'vue'
import InfoDialog from './InfoDialog.vue'
import { useUserStore, type User } from '@/stores/userStores'
import { useRouter } from 'vue-router'
const emailIdInput = ref('')
const passwordInput = ref('')
const loadingShow = ref(false)
const dialogOpen = ref(false)
const dialogTitle = ref('')
const dialogContent = ref('')
const dialogBtnContent = ref('👌')
let dialogCloseHandler = () => {
  loadingShow.value = false
}
const router = useRouter()

function validateInputs() {
  const passwordRegex = /^(?=.*[A-Za-z\d])[A-Za-z\d]{6,}$/
  const emailRegex = /^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/
  const isValidEmailId = emailIdInput.value.length === 8 || emailRegex.test(emailIdInput.value)
  const isValidPassword = passwordRegex.test(passwordInput.value)

  if (!isValidEmailId && !isValidPassword) {
    return {
      valid: false,
      msg: '账户必须是8个字符长的ID或有效的邮箱。密码位数至少为6位字符，且仅含数字字符或字母字符。'
    }
  } else if (!isValidEmailId) {
    return {
      valid: false,
      msg: '账户必须是8个字符长的ID或有效的邮箱。'
    }
  } else if (!isValidPassword) {
    return {
      valid: false,
      msg: '密码位数至少为6位字符，且仅含数字字符或字母字符。'
    }
  }

  return { valid: true, msg: '验证成功' }
}

const userLogin = () => {
  loadingShow.value = true
  const validationResult = validateInputs()

  if (!validationResult.valid) {
    dialogTitle.value = '🙁'
    dialogContent.value = validationResult.msg
    dialogOpen.value = true
  } else {
    let userLoginForm: { password: string; email?: string; id?: string } = {
      password: passwordInput.value
    }

    if (emailIdInput.value.includes('@')) {
      userLoginForm.email = emailIdInput.value
    } else {
      userLoginForm.id = emailIdInput.value
    }
    UserLoginApi(userLoginForm)
      .then((res) => {
        console.log(res)
        const data = res.data
        if (res.msg == 'success') {
          dialogTitle.value = '😊'
          dialogContent.value = '登录成功'
          dialogOpen.value = true

          const userStore = useUserStore()
          const user: User = {
            id: data.id,
            name: data.name,
            email: data.email,
            token: data.token,
            avatar: data.avatar
          }
          userStore.setUser(user)
          dialogCloseHandler = () => {
            router.push('/home/explore')
          }
        } else {
          dialogTitle.value = '😥'
          dialogContent.value = res.msg
          dialogOpen.value = true
        }
      })
      .finally(() => {
        setTimeout(() => {
          loadingShow.value = false
        }, 3000)
      })
  }
}
</script>

<style scoped></style>
