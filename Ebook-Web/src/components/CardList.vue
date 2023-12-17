<template>
  <div>
    <div>
      <span
        v-for="(item, index) in colorList"
        :key="index"
        class="m-2 p-1 rounded-badge border-2"
        >{{ item.name }}</span
      >
    </div>
    <div class="pr-6 mt-4">
      <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
        <!-- <div show="skeletonShow" class="skeleton w-28 h-28"></div> -->
        <PostCard v-for="(item, index) in postList" :key="index" :post="item"></PostCard>
      </div>
    </div>
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import { getPostsApi } from '@/api/posts'
import type { GetPostResType } from '@/types/post'
import { onMounted, ref } from 'vue'
import PostCard from './PostCard.vue'
import eventBus from '@/libs/eventBus'
import { useUserStore } from '@/stores/userStores'
import { colorList } from '@/utils/var'
const page = ref(1)
const pageSize = ref(100)
const postList = ref([])
const userStore = useUserStore()
const skeletonShow = ref(true)
eventBus.on('postFinish', (e) => {
  console.log(e)
  if (e == true) {
    getPosts()
  }
})

const getPosts = () => {
  const data = {
    id: userStore.user.id,
    page: page.value,
    pageSize: pageSize.value
  }
  getPostsApi(data).then((res) => {
    const data: GetPostResType = res.data
    if (res.code == 200) {
      skeletonShow.value = false
      console.log(data)
      postList.value = data.posts
    }
  })
}
onMounted(() => {
  getPosts()
})
</script>

<style scoped></style>
