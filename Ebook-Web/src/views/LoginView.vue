<template>
  <div
    class="flex flex-col min-h-full flex-1 justify-center px-6 py-9 lg:px-8"
    style="height: 80vh"
  >
    <div class="sm:mx-auto sm:w-full sm:max-w-sm basis-1/4">
      <img
        v-show="isAdmin == false"
        class="mx-auto h-60 w-auto"
        src="../assets/logo-text-nobg.png"
        alt="logo"
      />
      <img
        v-show="isAdmin == true"
        class="mx-auto h-60 w-auto"
        src="../assets/logo-admin-nobg.png"
        alt="logo"
      />
    </div>
    <div class="basis-3/4">
      <RouterView></RouterView>
    </div>
  </div>
  <AdminFooter></AdminFooter>
</template>

<script setup lang="ts">
import AdminFooter from '@/layouts/AdminFooter.vue'
import router from '@/router'
import { useUserStore } from '@/stores/userStores'
import { computed, onBeforeMount, onMounted } from 'vue'
import { RouterView, onBeforeRouteUpdate } from 'vue-router'
import { useRoute } from 'vue-router'
const route = useRoute()

const isAdmin = computed((): boolean => {
  if (route.fullPath.includes('adminLogin')) {
    return true
  } else {
    return false
  }
})

onBeforeMount(() => {
  const userStore = useUserStore()
  let id = String(userStore.user.id)
  console.log(userStore.user)
  if (id != '') {
    if (id.length == 8) {
      router.push('/home/explore')
    } else if (id.length == 5) {
      router.push('/admin/home')
    }
  }
})

onMounted(() => {})

onBeforeRouteUpdate((to, from) => {
  console.log('路由发生变化啦！从', from.fullPath, '到', to.fullPath)
})
</script>

<style scoped></style>
