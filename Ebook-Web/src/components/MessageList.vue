<template>
  <div class="w-full">
    <div class="flex justify-center mt-10 text-xl text-gray-400" v-show="messageList.length == 0">
      目前还没有通知哦~
    </div>
    <div class="flex flex-col gap-y-5 justify-center items-center w-full">
      <div v-for="(item, index) in messageList" :key="index" class="w-full">
        <div
          role="alert"
          class="alert shadow-lg w-4/6 m-auto"
          :class="{
            'alert-error': item.content.includes('收藏'),
            'alert-success': item.content.includes('点赞')
          }"
        >
          <Icon
            class="w-7 h-7 hover:cursor-pointer hover:opacity-75 ease-in-out duration-300 active:scale-75"
            :icon="avatarList[item.avatar]"
            @click="navUserProfile(item.userId)"
          ></Icon>
          <div>
            <h3>{{ item.name }}</h3>
            <h3 class="font-bold">{{ item.content }}</h3>
            <span class=" font-extrabold">{{ item.title }}</span>
          </div>
          <button class="btn btn-neutral btn-sm" @click="navPost(item.postId)">看看</button>
        </div>
      </div>
    </div>
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import { getMessageApi, getUserInfoApi } from '@/api/user'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/userStores'
import type { MessageType } from '@/types/message'
import { Icon } from '@iconify/vue/dist/iconify.js'
import { avatarList } from '@/utils/icon'
import { useRouter } from 'vue-router'
import { getPostApi } from '@/api/posts'
const router = useRouter()
const page = ref(1)
const pageSize = ref(10)
const messageList = ref<MessageType[]>([])

const userStore = useUserStore()

const navPost = (postId: number) => {
  router.push(`/home/message/posts/${postId}`)
}

const navUserProfile = (userId: number) => {
  router.push(`/home/profile/${userId}`).then(() => {
    window.location.reload()
  })
}

onMounted(() => {
  let data = {
    id: userStore.user.id,
    page: page.value,
    pageSize: pageSize.value
  }
  const id = userStore.user?.id
  getMessageApi(data).then((res) => {
    console.log(res)
    if (res.code == 200 && res.data.notifies != null) {
      messageList.value = res.data.notifies
      for (let i = 0; i < messageList.value.length; i++) {
        getUserInfoApi(id, messageList.value[i].userId).then((res) => {
          console.log(messageList.value[i])
          messageList.value[i]['avatar'] = res.data.avatar
          messageList.value[i]['name'] = res.data.name
        })
        getPostApi(messageList.value[i].postId).then((res) => {
          console.log(res)
          if (res.code == 200) {
            messageList.value[i]['title'] = res.data.posts.title
          }
        })
      }
    }
  })
})
</script>

<style scoped></style>
