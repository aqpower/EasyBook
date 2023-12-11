<script setup lang="ts">
import { getPostsApi } from '@/api/posts'
import httpInstance from '../utils/http'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/userStores'
import PostCard from '@/components/PostCard.vue'
import { type GetPostResType, type PostType } from '@/types/post'
import NewPost from '@/components/NewPost.vue'
const newModelShow = ref(false)
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
  <label for="my_modal_6" class="btn" @click="newModelShow = true">创建帖子</label>

  <input type="checkbox" id="my_modal_6" class="modal-toggle" />
  <div class="modal" role="dialog">
    <div class="modal-box w-11/12 h-11/12 max-w-5xl p-0" style="height: 80%">
      <div class="modal-action justify-start flex-col w-full h-full m-0">
        <div class="w-full h-full">
          <div class="m-5" v-if="newModelShow">
            <NewPost></NewPost>
          </div>
        </div>
        <label for="my_modal_6" class="btn mb-3" @click="newModelShow = false">提交！</label>
      </div>
    </div>
  </div>

  <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
    <PostCard v-for="(item, index) in postList" :key="index" :post="item"></PostCard>
  </div>
</template>
