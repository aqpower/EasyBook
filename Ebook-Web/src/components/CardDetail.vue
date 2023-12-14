<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { deletePostApi, getPostApi, newCommentApi } from '@/api/posts'
import type { PostDetailResType, PostType } from '@/types/post'
import type { ResType } from '@/types'
import InfoDialogVue from '@/components/InfoDialog.vue'
import { avatarList } from '@/utils/icon'
import { Icon } from '@iconify/vue/dist/iconify.js'
import { useUserStore } from '@/stores/userStores'
import { ArrowTopRightOnSquareIcon } from '@heroicons/vue/20/solid'
import useCommandComponent from '@/hooks/useCommandComponent'
const route = useRoute()
const router = useRouter()
const showDig = ref(true)
const imgIndex = ref(0)
console.log(route)
const userStore = useUserStore()
const post = ref<PostType>()
const commentList = ref({})
const commentInput = ref('')
const dialog = useCommandComponent(InfoDialogVue)
const changeImg = (value) => {
  console.log(imgIndex.value, value, post.value?.url.length)
  let t = imgIndex.value + value
  if (t >= 0 && t < post.value?.url.length) {
    imgIndex.value = t
  }
}

onMounted(() => {
  const postId = route.params.postId as string
  getPostApi(postId).then((res: ResType<PostDetailResType>) => {
    console.log(res)
    if (res.code == 200) {
      post.value = res.data.posts
      commentList.value = res.data.commentsList
      console.log(post)
    }
  })
})

const showDelete = (): boolean => {
  if (route.params.userId != null) {
    if (route.params.userId == userStore.user?.id) {
      return true
    }
  }
  return false
}

const deletePost = () => {
  deletePostApi(route.params.postId).then((res) => {
    dialog({ content: '删除帖子成功', btnContent: '👌' })
    router.go(-1)
  })
}

const newComment = () => {
  let data = {
    userId: userStore.user?.id,
    postId: route.params.postId,
    content: commentInput.value
  }
  newCommentApi(data).then((res) => {
    if (res.code == 200) {
      dialog({ content: '帖子评论', btnContent: '👌' })
      router.go(0)
    }
  })
}

const handleClose = () => {
  router.go(-1)
}
</script>

<template>
  <InfoDialogVue :visible="showDig" :big-dialog-enable="true">
    <template #content>
      <div v-if="post != null" class="flex w-full h-full">
        <div class="w-full h-full flex-1 relative">
          <img
            class="h-full w-full rounded-2xl AqImage object-cover"
            :src="post?.url[imgIndex]"
            :alt="post.title"
          />
          <div class="absolute top-1/2 left-3" @click="changeImg(-1)">
            <Icon class="w-10 h-10 text-slate-400" icon="line-md:arrow-small-left" />
          </div>
          <div class="absolute top-1/2 right-3" @click="changeImg(1)">
            <Icon class="w-10 h-10 text-primary" icon="line-md:arrow-small-right" />
          </div>
        </div>
        <div class="flex flex-col flex-1">
          <div class="ml-4 mr-1">
            <div class="flex justify-between items-center gap-1">
              <div class="flex items-center gap-2">
                <Icon class="w-8 h-8 m-1" :icon="avatarList[post.avatar]"></Icon>
                <p class="font-medium">{{ post.name }}</p>
              </div>
              <div class="flex flex-row justify-center items-center gap-3">
                <button class="btn btn-primary" v-show="showDelete()" @click="deletePost">
                  删除帖子
                </button>
                <button class="btn" @click="handleClose">❌</button>
              </div>
            </div>
            <div class="mt-3">
              <p class="whitespace-pre-line">{{ post.contentText }}</p>
            </div>
            <div class="divider">评论</div>
            <div class="flex gap-3">
              <input
                v-model="commentInput"
                class="input w-full input-primary"
                placeholder="请在这里输入评论"
              />
              <button class="btn btn-primary" @click="newComment">提交</button>
            </div>
            <div class="flex flex-col mt-1">
              <div v-for="(item, index) in commentList" :key="index" class="flex flex-col p-3">
                <div class="flex gap-3">
                  <Icon class="w-6 h-6" :icon="avatarList[item.avatar]"></Icon>
                  <p class="font-medium">{{ item.name }}</p>
                </div>
                <p class="mt-1">{{ item.content }}</p>
                <div class="flex justify-end mr-5">
                  <p class="text-xs text-slate-400">{{ item.commentTime }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
  </InfoDialogVue>
</template>

<style scoped></style>
