<template>
  <div class="rounded-xl ease-linear duration-300">
    <div
      class="card card-compact w-56 bg-200"
      style="
        box-shadow:
          0 4px 32px 0 rgba(0, 0, 0, 0.08),
          0 1px 4px 0 rgba(0, 0, 0, 0.04);
      "
    >
      <div class="card-body">
        <div class="dropdown">
          <div tabindex="0" role="button" class="btn btn-info btn-outline min-h-0 h-9 w-full">
            更换主题
            <svg
              width="12px"
              height="12px"
              class="h-2 w-2 fill-current opacity-60 inline-block"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 2048 2048"
            >
              <path d="M1799 349l242 241-1017 1017L7 590l242-241 775 775 775-775z"></path>
            </svg>
          </div>
          <ul
            tabindex="0"
            class="right-0 dropdown-content z-[1] p-2 shadow-2xl bg-base-200 rounded-box w-44"
          >
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="😊 默认"
                value="light"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="🧛‍♂️ 吸血鬼"
                value="dracula"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="🌳 森林"
                value="forest"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="💖 情人节"
                value="valentine"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="💦 海洋"
                value="aqua"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="🕰 复古"
                value="retro"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="⚡ 赛博朋克"
                value="cyberpunk"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="🧁 纸杯蛋糕"
                value="cupcake"
                v-model="selectedTheme"
              />
            </li>
          </ul>
        </div>
        <button class="btn btn-success btn-outline min-h-0 h-9" @click="logout">退出登录</button>
        <button class="btn btn-error btn-outline min-h-0 h-9" @click="showUserDelete = true">
          注销账号
        </button>

        <figure>
          <img class="w-32" src="../assets//logo-text-nobg.png" />
        </figure>
        <div class="flex justify-center items-center flex-col">
          <p class="text-success">DESIGNED BY</p>
          <p class="text-success">@aqpower</p>
          <p class="text-success">@zhuxinr</p>
          <p class="text-success">@wzbmkbk</p>
          <p class="text-success">Copyright©2023 EasyBook</p>
        </div>
      </div>
    </div>
    <InfoDialog :visible="showUserDelete">
      <template #content>
        <div class="flex justify-center flex-col items-center gap-5">
          <p class="font-extrabold text-error">👋 账号注销 👋</p>
          <p class="text">账号注销后不能再被找回，请三思</p>
          <input
            type="password"
            v-model="passwordInput"
            class="input input-error w-full"
            placeholder="请输入账号密码"
          />
          <div class="flex flex-row justify-center w-full gap-5">
            <button class="btn btn-success w-20" @click="deleteUser">确定</button>
            <button class="btn btn-error w-20" @click="showUserDelete = false">取消</button>
          </div>
        </div>
      </template>
    </InfoDialog>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { useThemeStore } from '@/stores/theme'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStores'
import InfoDialog from './InfoDialog.vue'
import { deleteUserApi } from '@/api/user'
import useCommandComponent from '@/hooks/useCommandComponent'
const dialog = useCommandComponent(InfoDialog)
const userStore = useUserStore()
const router = useRouter()
const themeStore = useThemeStore()
const selectedTheme = ref(themeStore.getTheme())
const showUserDelete = ref(false)
const passwordInput = ref('')
watch(selectedTheme, () => {
  themeStore.setTheme(selectedTheme.value)
})

const logout = () => {
  userStore.setUser({
    id: '',
    name: '',
    email: '',
    token: '',
    avatar: 0
  })
  router.replace('/')
}

const deleteUser = () => {
  let data = {
    id: userStore.user?.id,
    password: passwordInput.value
  }
  deleteUserApi(data).then((res) => {
    console.log(res)
    if (res.code == 200) {
      dialog({
        title: '👋',
        content: '账户注销成功，再见了我的朋友！EasyBook永远记得你！',
        btnContent: '👌',
        onClose: () => {
          userStore.setUser({
            id: '',
            name: '',
            email: '',
            token: '',
            avatar: 0
          })
          router.replace('/')
        }
      })
    } else if (res.code == 401) {
      dialog({
        title: '👋',
        content: '密码输入错误',
        btnContent: '👌'
      })
    }
  })
}
</script>

<style scoped></style>
