<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { deletePostApi, getPostApi, newCommentApi } from '@/api/posts'
import type { PostDetailResType } from '@/types/post'
import type { ResType } from '@/types'
import InfoDialogVue from '@/components/InfoDialog.vue'
import { avatarList } from '@/utils/icon'
import { Icon } from '@iconify/vue/dist/iconify.js'
import { useUserStore } from '@/stores/userStores'
import useCommandComponent from '@/hooks/useCommandComponent'
import { formatTime } from '@/utils/time'
import { violationTypes } from '@/utils/var'
import { collectPostApi, violationPostApiRequest, violationPostApi } from '@/api/user'
const route = useRoute()
const router = useRouter()
const showDig = ref(true)
const showArrowIcon = ref(false)
const imgIndex = ref(0)
console.log(route)
const userStore = useUserStore()
const post = ref()
const commentList = ref({})
const commentInput = ref('')
const showVio = ref(false)
const dialog = useCommandComponent(InfoDialogVue)
const userId = userStore.user?.id as string
const postId = route.params.postId as string
const changeImg = (value: number) => {
  console.log(imgIndex.value, value, post.value?.url.length)
  let t = imgIndex.value + value
  if (t >= 0 && t < post.value?.url.length) {
    imgIndex.value = t
  }
}

const imgLeftVal = computed(() => {
  let t = imgIndex.value - 1
  if (t >= 0 && t < post.value?.url.length) {
    return true
  }
  return false
})

const imgRightVal = computed(() => {
  let t = imgIndex.value + 1
  if (t >= 0 && t < post.value?.url.length) {
    return true
  }
  return false
})

onMounted(() => {
  getPost()
})

const getPost = () => {
  const postId = route.params.postId as string
  getPostApi(postId).then((res: ResType<PostDetailResType>) => {
    console.log(res)
    if (res.code == 200) {
      post.value = res.data.posts
      commentList.value = res.data.commentsList
      console.log(post)
    }
  })
}

const isMe = computed((): boolean => {
  if (route.params.userId != null) {
    if (route.params.userId == userStore.user?.id) {
      return true
    }
  }
  return false
})

const violationPost = (reason: string) => {
  showVio.value = false
  let data: violationPostApiRequest = {
    userId: userId,
    postId: postId,
    violationReason: reason
  }
  violationPostApi(data).then((res) => {
    if (res.code == 200) {
      dialog({
        content: '举报帖子成功',
        btnContent: '👌'
      })
    }
  })
}

const collectPost = () => {
  let data = {
    userId: userStore.user?.id as string,
    postId: route.params.postId as string
  }
  collectPostApi(data).then((res) => {
    if (res.code == 200) {
      dialog({ content: '收藏帖子成功', btnContent: '👌' })
    } else {
      dialog({ content: res.msg, btnContent: '👌' })
    }
  })
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
      commentInput.value = ''
      dialog({ content: '帖子评论成功', btnContent: '👌' })
      getPost()
    }
  })
}

const handleClose = () => {
  router.go(-1)
}

const navUserProfile = (id) => {
  router.push(`/home/profile/${id}`).then(() => {
    window.location.reload()
  })
}
</script>

<template>
  <InfoDialogVue :visible="showDig" :big-dialog-enable="true">
    <template #content>
      <div v-if="post != null" class="flex w-full h-full">
        <div
          class="w-full h-full flex-1 relative"
          @mouseenter="showArrowIcon = true"
          @mouseleave="showArrowIcon = false"
        >
          <div class="w-full h-full flex items-center justify-center">
            <img
              class="rounded-2xl AqImage object-cover m-auto max-w-full max-h-full"
              :src="post?.url[imgIndex]"
              :alt="post.title"
            />
          </div>
          <div class="absolute top-1/2 left-3" @click="changeImg(-1)">
            <Icon
              v-if="showArrowIcon && imgLeftVal"
              class="w-10 h-10 p-1 text-primary bg-slate-600 rounded-full opacity-70"
              icon="line-md:arrow-small-left"
            />
          </div>
          <div class="absolute top-1/2 right-3" @click="changeImg(1)">
            <Icon
              v-if="showArrowIcon && imgRightVal"
              class="w-10 h-10 p-1 text-primary bg-slate-600 rounded-full opacity-70"
              icon="line-md:arrow-small-right"
            />
          </div>
        </div>
        <div class="flex flex-col flex-1 overflow-y-auto">
          <div class="ml-4 mr-1">
            <div class="flex justify-between items-center gap-1">
              <div
                class="flex items-center gap-2 hover:cursor-pointer"
                @click="navUserProfile(post.userId)"
              >
                <Icon class="w-8 h-8 m-1" :icon="avatarList[post.avatar]"></Icon>
                <p class="font-medium">{{ post.name }}</p>
              </div>
              <div class="flex flex-row justify-center items-center gap-3">
                <button class="btn btn-error min-h-0 h-10" v-show="isMe == true" @click="deletePost">
                  删除帖子
                </button>
                <button class="btn btn-success min-h-0 h-10" v-show="isMe == false" @click="collectPost">
                  收藏
                </button>

                <div class="relative">
                  <button
                    for="vio_modal"
                    class="btn btn-warning min-h-0 h-10"
                    v-show="isMe == false"
                    @click="showVio = true"
                  >
                    举报
                  </button>
                  <div class="absolute top-14 right-0" v-show="showVio == true">
                    <div class="bg-warning w-96 rounded-3xl shadow-xl p-3">
                      <h3 class="text-warning-content font-bold text-lg text-center mb-4">
                        请选择违规类型
                      </h3>
                      <div class="flex flex-col gap-3">
                        <button for="vio_modal" class="btn w-full" @click="showVio = false">
                          取消
                        </button>
                        <div v-for="(item, index) in violationTypes" :key="index">
                          <button for="vio_modal" class="btn w-full" @click="violationPost(item)">
                            {{ item }}
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <button class="btn  min-h-0 h-10" @click="handleClose">❌</button>
              </div>
            </div>
            <div class="mt-1">
              <p class="font-bold text-lg">{{ post.title }}</p>
            </div>
            <div class="mt-2">
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
                <div class="flex gap-3 hover:cursor-pointer" @click="navUserProfile(item.userId)">
                  <Icon class="w-6 h-6" :icon="avatarList[item.avatar]"></Icon>
                  <p class="font-medium">{{ item.name }}</p>
                </div>
                <p class="mt-1">{{ item.content }}</p>
                <div class="flex justify-end mr-5">
                  <p class="text-xs text-slate-400">{{ formatTime(item.commentTime) }}</p>
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
