<template>
  <div class="p-3">
    <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
      <PostCard v-for="(item, index) in postList" :key="index" :post="item"></PostCard>
    </div>
  </div>
</template>

<script setup lang="ts">
import { getPostsApi } from '@/api/posts'
import type { GetPostResType } from '@/types/post'
import { onMounted, ref } from 'vue'
import PostCard from './PostCard.vue'
import eventBus from '@/libs/eventBus'
const page = ref(1)
const pageSize = ref(100)

const postList = ref([])

eventBus.on('postFinish', (e) => {
  console.log(e)
  if (e == true) {
    getPosts()
  }
})

const getPosts = () => {
  const data = {
    id: 10000002,
    page: page.value,
    pageSize: pageSize.value
  }
  getPostsApi(data).then((res) => {
    const data: GetPostResType = res.data
    if (res.code == 200) {
      console.log(data)
      postList.value = data.postShows
    }
  })
}
onMounted(() => {
  getPosts()
})
</script>

<style scoped></style>
