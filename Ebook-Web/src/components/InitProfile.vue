<template>
  <div class="flex flex-col justify-center items-center space-y-7">
    <div class="flex justify-center flex-col items-center">
      <p class="text-lg font-semibold">请填写用户基本信息完成注册</p>
    </div>
    <div class="flex justify-center items-center">
      <button
        class="hover:opacity-75 hover:scale-90 ease-in-out duration-300 active:scale-75 -mt-3"
        onclick="my_modal_1.showModal()"
      >
        <Icon class="w-20 h-20" :icon="avatarList[avatarIndex]" />
      </button>
    </div>

    <dialog id="my_modal_1" class="modal">
      <div class="modal-box w-9/12 max-w-5xl">
        <div class="modal-action">
          <form method="dialog" class="flex flex-col justify-center">
            <div class="flex flex-wrap">
              <div v-for="(link, index) in avatarList" :key="index">
                <div class="indicator relative">
                  <Icon
                    class="w-20 h-20 m-3 hover:cursor-pointer hover:opacity-75 ease-in-out duration-300 active:scale-75"
                    :icon="link"
                    :class="{ 'w-21': index == avatarIndex }"
                    @click="handleIconClick(link, index)"
                  />
                  <div class="absolute m-16" v-if="index == avatarIndex">
                    <Icon class="text-primary h-10 w-10" icon="line-md:confirm" />
                  </div>
                </div>
              </div>
            </div>
            <button class="btn mt-5">确定</button>
          </form>
        </div>
      </div>
    </dialog>
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
    <div class="flex items-center flex-col">
      <p>系统提供一些可选的头衔，点击头像可以进行选择</p>
      <p>用户密码不少于6位并且至少只能由数字字符或者字母字符组成</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
import { avatarList } from '@/utils/icon'
import { ref } from 'vue'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
import { useRoute } from 'vue-router'
import router from '@/router'
import type { UserRegisterType } from '@/types/user'
import { userRegisterApi } from '@/api/user'
const nameInput = ref('')
const passwordInput = ref('')
const avatarIndex = ref(1)
const dialog = useCommandComponent(InfoDialog)
const route = useRoute()
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

const handleIconClick = (link: String, index: number) => {
  avatarIndex.value = index
}

const navToLogin = () => {
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
