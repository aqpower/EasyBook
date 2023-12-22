<template>
  <div class="sm:columns-1 md:columns-1 lg:columns-2 xl:columns-3">
    <div v-for="(item, index) in commentList" :key="index" class="ml-2 mr-7 my-6 break-inside-avoid">
      <div class="card w-full bg-base-100 shadow-xl">
        <div class="card-body p-5">
          <p class=" text-xl" style="font-family: LXGWWenKai,sans-serif">{{ item.content }}</p>
          <p class="text-right">{{ formatTime(item.commentTime) }}</p>
          <div class="card-actions justify-end mt-1">
            <button class="btn btn-neutral btn-outline min-h-0 h-9" @click="navPost(item.postId)">
              看看
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { getUserCommentsApi } from '@/api/posts'
import { useUserStore } from '@/stores/userStores'
import { onMounted, ref } from 'vue'
import { type Comment } from '@/api/posts'
import { formatTime } from '@/utils/time'
import { useRouter } from 'vue-router'
const page = ref(1)
const pageSize = ref(30)
const userStore = useUserStore()
const userId = userStore.user?.id
const commentList = ref<Comment[]>([])
const router = useRouter()
onMounted(() => {
  getComment()
})

const getComment = () => {
  getUserCommentsApi(userId, page.value, pageSize.value).then((res) => {
    if (res.code == 200) {
      commentList.value = res.data.comments
    }
  })
}

const navPost = (postId: any) => {
  let path = router.currentRoute.value.fullPath
  path += `/posts/${postId}`
  router.push(path)
}
</script>

<style scoped></style>
