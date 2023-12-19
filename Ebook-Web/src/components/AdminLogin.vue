<template>
  <div>
    <h2 class="text-base-content mt-1 text-center text-2xl font-bold leading-9 tracking-tight">
      管理员登录
    </h2>
    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <div class="space-y-6">
        <div>
          <div class="flex items-center justify-between">
            <label for="email" class="block text-sm font-medium leading-6 text-base-content"
              >账户</label
            >
            <div class="text-sm">
              <a href="#/account/login" class="font-semibold rounded-xl text-success">用户登录</a>
            </div>
          </div>
          <div class="mt-2">
            <input
              v-model="idInput"
              placeholder="请输入管理员ID"
              class="input input-bordered input-success w-full sm:text-sm sm:leading-5 h-10"
            />
          </div>
        </div>

        <div>
          <div class="flex items-center justify-between">
            <label for="password" class="block text-sm font-medium leading-6 text-base-content"
              >密码</label
            >
            <div class="text-sm">
              <a href="#/account/resetPassword" class="font-semibold text-success">忘记密码?</a>
            </div>
          </div>
          <div class="mt-2">
            <input
              v-model="passwordInput"
              id="password"
              type="password"
              placeholder="请输入密码"
              class="input input-success input-bordered w-full sm:text-sm sm:leading-5 h-10"
            />
          </div>
        </div>

        <div>
          <button
            @click="adminLogin()"
            class="min-h-0 h-9 btn btn-success flex w-full justify-center rounded-md px-3 py-1.5 leading-6 shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2"
          >
            <span v-if="loadingShow" class="loading loading-bars loading-xs h-6"></span>
            <span v-if="!loadingShow" class="h-6">登录</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { adminLoginApi } from '@/api/admin'
import { ref } from 'vue'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
const dialog = useCommandComponent(InfoDialog)
const idInput = ref('')
const passwordInput = ref('')
const loadingShow = ref(false)
import { useRouter } from 'vue-router'
import { useUserStore, type User } from '@/stores/userStores'
const router = useRouter()
const showErrorDialog = (message: string) => {
  dialog({
    title: '😢',
    content: message,
    btnContent: '👌',
    onClose: () => {
      loadingShow.value = false
    }
  })
}

const showError = (errorType: string) => {
  let errorMessage = ''
  if (errorType === 'id') {
    errorMessage = 'ID必须为5位数字'
  } else if (errorType === 'password') {
    errorMessage = '密码必须为至少6位且仅包含字母或数字'
  } else {
    errorMessage = 'ID和密码验证失败'
  }

  showErrorDialog(errorMessage)
}

const adminLogin = () => {
  loadingShow.value = true
  const id = idInput.value
  const password = passwordInput.value

  const idRegex = /^\d{5}$/ // 5位数字的正则表达式
  const passwordRegex = /^[a-zA-Z0-9]{6,}$/ // 至少6位且仅包含字母或数字的正则表达式

  if (!idRegex.test(id)) {
    showError('id')
    return
  }

  if (!passwordRegex.test(password)) {
    showError('password')
    return
  }

  let data = { id, password }
  adminLoginApi(data).then((res) => {
    if (res.code == 200) {
      let data = res.data
      const userStore = useUserStore()
      const user: User = {
        id: data.id,
        name: data.name,
        token: data.token,
        avatar: data.avatar
      }
      userStore.setUser(user)

      dialog({
        title: '😢',
        content: '登录成功！',
        btnContent: '👌',
        onClose: () => {
          loadingShow.value = false
          router.replace('/admin/home')
        }
      })
    } else if (res.code == 401) {
      showErrorDialog(res.msg)
    }
  })
}
</script>

<style scoped></style>
