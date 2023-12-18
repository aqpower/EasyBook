<script setup lang="ts">
import { useUserStore } from '@/stores/userStores'
import { onMounted, ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import { useRouter } from 'vue-router'
const router = useRouter()
const userStore = useUserStore()
const id = userStore.user?.id
const followerList = ref([])
import { avatarList } from '@/utils/icon'
import { getFansApi } from '@/api/user';
onMounted(() => {
  getFans()
})

const getFans = () => {
  getFansApi(id as string).then((res) => {
    console.log(res)
    if (res.code == 200) {
      followerList.value = res.data
    }
  })
}

const navUserProfile = (id: string) => {
  router.push(`/home/profile/${id}`).then(() => {
    window.location.reload()
  })
}

</script>

<template>
  <div class="flex gap-3 flex-col w-full overflow-scroll max-h-96">
    <p>粉丝列表</p>
    <div v-for="(item, index) in followerList" :key="index">
      <!-- {{ item }} -->
      <div class="flex gap-3 items-center justify-between hover:cursor-pointer">
        <div class="flex gap-2 items-center" @click="navUserProfile(item.id)">
          <Icon class="w-9 h-9" :icon="avatarList[item.avatar]"></Icon>
          <p>{{ item.name }}</p>
        </div>
        <div>
          <button class="btn btn-success btn-outline min-h-0 h-9" @click="navUserProfile(item.id)">
            看看
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
