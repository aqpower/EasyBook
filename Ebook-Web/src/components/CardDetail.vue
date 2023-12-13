<template>
  aaaaa
  <div>{{ post }}</div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { getPostApi } from '@/api/posts'
import type { PostDetailResType } from '@/types/post'
import type { ResType } from '@/types'
const route = useRoute()
const post = ref()
console.log(route)

onMounted(() => {
  const postId = route.params.postId as string
  getPostApi(postId).then((res: ResType<PostDetailResType>) => {
    console.log(res)
    if (res.code == 200) {
      post.value = res.data
    }
  })
})
</script>

<style scoped></style>
