<template>
  <div class="flex flex-col justify-center items-center space-y-7">
    <div class="flex justify-center flex-col items-center">
      <p class="text-lg font-semibold">请填写用户基本信息完成注册</p>
    </div>
    <AvatarSelector></AvatarSelector>
    <input
      type="text"
      v-model="nameInput"
      placeholder="请输入你的用户名"
      class="input input-bordered input-primary w-full max-w-sm h-10"
    />
    <input
      type="password"
      v-model="passwordInput"
      placeholder="请输入你的密码"
      class="input input-bordered input-primary w-full max-w-sm h-10"
    />
    <button class="btn btn-primary w-full max-w-sm min-h-0 h-10" @click="navToLogin">确定</button>
    <div class="flex items-center flex-col justify-center">
      <p>系统提供可选的头像，点击头像可以进行选择</p>
      <p>用户密码不少于6位并且至少只能由数字字符或者字母字符组成</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { provide, ref } from 'vue'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
import { useRoute } from 'vue-router'
import router from '@/router'
import type { UserRegisterType } from '@/types/user'
import { userRegisterApi } from '@/api/user'
import AvatarSelector from './AvatarSelector.vue'
const nameInput = ref('')
const passwordInput = ref('')
const dialog = useCommandComponent(InfoDialog)
const route = useRoute()
const avatarIndex = ref(0)
// 声明一个响应性变量并 provide 其自身
// 孙组件获取后可以保持响应性
provide('avatarIndex', avatarIndex)
function validateInputs() {
  const passwordRegex = /^(?=.*[A-Za-z\d])[A-Za-z\d]{6,}$/
  const isValidPassword = passwordRegex.test(passwordInput.value)

  if (!isValidPassword) {
    return {
      valid: false,
      msg: '密码位数至少为6位字符，且仅含数字字符或字母字符。'
    }
  }

  return { valid: true, msg: '验证成功' }
}

const navToLogin = () => {
  let v = validateInputs()
  if (v.valid == false) {
    dialog({
      title: '😞',
      content: v.msg,
      btnContent: '👌'
    })
    return
  }
  let user: UserRegisterType = {
    email: route.params.email,
    name: nameInput.value,
    password: passwordInput.value,
    avatar: avatarIndex.value
  }
  userRegisterApi(user).then((res) => {
    if (res.code == 200) {
      dialog({ title: '🥳', content: '用户注册成功,欢迎来到EasyBook~', btnContent: '🎉' })
      router.replace('/account/login')
    }
  })
}
</script>

<style scoped></style>
