<template>
  <div>
    <div class="flex justify-center mt-24">
      <Icon class="w-28 h-28 m-1" :icon="avatarList[userStore.user.avatar]"></Icon>
    </div>

    <div class="pr-6">
      <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
        <PostCard v-for="(item, index) in userPosts" :key="index" :post="item"></PostCard>
      </div>
    </div>
  </div>
  <RouterView></RouterView>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/userStores'
import { getUserPostApi } from '@/api/posts'
import { onMounted, ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import type { UserPostResType } from '@/types/post'
import PostCard from './PostCard.vue'
import { avatarList } from '@/utils/icon'
const userStore = useUserStore()
const userPosts = ref<UserPostResType[]>([])

onMounted(() => {
  const id = userStore.user?.id
  getUserPostApi(id).then((res) => {
    console.log(res)
    if (res.code == 200) {
      userPosts.value = res.data
    }
  })
})
</script>

<style scoped></style>
