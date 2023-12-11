<script setup lang="ts">
import { getPostsApi } from '@/api/posts'
import httpInstance from '../utils/http'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/userStores'
import PostCard from '@/components/PostCard.vue'
import { type GetPostResType, type PostType } from '@/types/post'
import NewPost from '@/components/NewPost.vue'
function f1() {
  httpInstance.get('/api/v1/hello')
}

const page = ref(1)
const pageSize = ref(10)
const userStore = useUserStore()
const postList = ref([])

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

<template>
  <NewPost></NewPost>
  <!-- <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
    <PostCard v-for="(item, index) in postList" :key="index" :post="item"></PostCard>
  </div> -->
</template>
