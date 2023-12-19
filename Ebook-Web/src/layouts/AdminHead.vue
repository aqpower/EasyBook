<template>
  <div>
    <div class="navbar bg-base-100 shadow">
      <div class="flex-1">
        <a class="text-primary btn btn-ghost text-xl" href="/#/admin/home">EasyBook</a>
        <span class="badge-primary rounded-2xl px-2 py-1 font-medium">管理员</span>
      </div>
      <div class="flex-none">
        <p class="mx-2 text-base">{{ greeting + admin?.name }}</p>
        <ul class="menu menu-horizontal px-1">
          <li><a href="/#/admin/review">审核举报</a></li>
          <li>
            <details>
              <summary>更多</summary>
              <ul
                class="flex flex-col gap-2 p-2 w-36 bg-base-200 rounded-t-none z-50 right-0 top-5 rounded-xl"
              >
                <li>
                  <button
                    class="btn btn-error btn-outline min-h-0 h-9 flex justify-center items-center"
                    @click="logout"
                  >
                    退出登录
                  </button>
                </li>
                <div class="dropdown w-full">
                  <div
                    tabindex="0"
                    role="button"
                    class="btn btn-info btn-outline min-h-0 h-9 w-full"
                  >
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
              </ul>
            </details>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/userStores'
import { computed, ref, watch } from 'vue'
import { useThemeStore } from '@/stores/theme'
import { useRouter } from 'vue-router'
const router = useRouter()
const userStore = useUserStore()
const admin = userStore.user
const selectedTheme = ref('')
const themeStore = useThemeStore()
watch(selectedTheme, () => {
  themeStore.setTheme(selectedTheme.value)
})
const greeting = computed(() => {
  const now = new Date()
  const hour = now.getHours()

  if (hour >= 6 && hour < 12) {
    return '🌞 上午好,'
  } else if (hour >= 12 && hour < 18) {
    return '😊 下午好,'
  } else {
    return '🌙 晚上好,'
  }
})

const logout = () => {
  userStore.setUser({
    id: '',
    name: '',
    email: '',
    token: '',
    avatar: 0
  })
  router.replace('/account/adminLogin')
}
</script>

<style scoped></style>
