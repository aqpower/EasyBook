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
          <div tabindex="0" role="button" class="btn btn-primary btn-outline min-h-0 h-9 w-full">
            Theme
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
            class="dropdown-content z-[1] p-2 shadow-2xl bg-base-200 rounded-box w-52"
          >
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="Default"
                value="light"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="dracula"
                value="dracula"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="forest"
                value="forest"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="Valentine"
                value="valentine"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="Aqua"
                value="aqua"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="pastel"
                value="pastel"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="cyberpunk"
                value="cyberpunk"
                v-model="selectedTheme"
              />
            </li>
            <li>
              <input
                type="radio"
                name="theme-dropdown"
                class="theme-controller btn btn-sm btn-block btn-ghost justify-start"
                aria-label="coffee"
                value="coffee"
                v-model="selectedTheme"
              />
            </li>
          </ul>
        </div>
        <button class="btn btn-primary btn-outline min-h-0 h-9" @click="logout">退出登录</button>
        <figure>
          <img class="w-32" src="../assets//logo-text-nobg.png" />
        </figure>
        <div class="flex justify-center items-center flex-col">
          <p class="text-success">DESIGNED</p>
          <p class="text-success">BY</p>
          <p class="text-success">@aqpower</p>
          <p class="text-success">@zhuxinr</p>
          <p class="text-success">@wzbmkbk</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import { useThemeStore } from '@/stores/theme'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStores'
import { log } from 'console'
const userStore = useUserStore()
const router = useRouter()
const selectedTheme = ref('')
const themeStore = useThemeStore()
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
</script>

<style scoped></style>
