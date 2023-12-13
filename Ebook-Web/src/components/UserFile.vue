<template>
  <div>这里是个人主页！！！</div>
  <Icon class="w-4 h-4 m-1" :icon="avatarList[userStore.user.avatar]"></Icon>

  <div class="pr-6">
    <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
      <PostCard v-for="(item, index) in userPosts" :key="index" :post="item"></PostCard>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/userStores'
import { getUserPostApi } from '@/api/posts'
import { onMounted, ref } from 'vue'
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
