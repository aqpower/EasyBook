<template>
  <div>
    <div class="flex justify-center mt-14 items-center">
      <Icon v-if="avatarIndex != -1" class="w-28 h-28 m-1" :icon="avatarList[avatarIndex]"></Icon>
      <div v-if="user" class="ml-3 flex flex-col gap-3">
        <div class="badge badge-primary">ID: {{ user.id }}</div>
        <p class="text-xl font-bold">{{ user.name }}</p>
      </div>
    </div>
    <div class="divider text-gray-400">帖子</div>
    <div class="pr-6 mt-5">
      <div class="flex justify-center mt-10 text-xl text-gray-400" v-show="userPosts.length == 0">该用户没有发送过帖子</div>
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
import { useRoute } from 'vue-router'
import PostCard from './PostCard.vue'
import { avatarList } from '@/utils/icon'
import { getUserInfoApi } from '@/api/user'
const userPosts = ref<UserPostResType[]>([])
const route = useRoute()
const avatarIndex = ref(-1)
const user = ref()
onMounted(() => {
  console.log(route)
  const userId = route.params.userId
  getUserInfoApi(userId).then((res) => {
    console.log(res)
    if (res.code == 200) {
      user.value = res.data
      avatarIndex.value = user.value.avatar
    }
  })
  getUserPostApi(userId).then((res) => {
    console.log(res)
    if (res.code == 200) {
      userPosts.value = res.data
    }
  })
})
</script>

<style scoped></style>
