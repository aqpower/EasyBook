<template>
  <div class="w-full break-inside-avoid">
    <figure>
      <img
        class="max-h-96 w-full object-cover inline-block rounded-2xl AqImage hover:brightness-75 ease-in-out duration-500"
        v-if="post.url != null"
        :src="post.url[0]"
        :alt="post.title"
        @click="navDetailHandler"
        ref="img"
      />
    </figure>

    <div class="mx-4 my-3">
      <h1 class="text-sm font-medium font-sans">{{ post.title }}</h1>
      <div class="flex justify-between">
        <div class="flex items-center mt-1 hover:cursor-pointer" @click="navUserProfile">
          <Icon class="w-4 h-4 m-1" :icon="avatarList[post.avatar]"></Icon>
          <p class="text-xs hover:text-primary ease-in-out duration-200">
            {{ post.name }}
          </p>
        </div>
        <div class="flex items-center">
          <Icon
            class="mr-1 w-3 h-3 hover:cursor-pointer active:scale-75 ease-in-out"
            icon="icon-park-outline:like"
            :class="{ 'text-primary': likeActivate }"
            @click="newPostLike"
          />
          <span class="text-xs">{{ post.likeNum }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
import { avatarList } from '@/utils/icon'
import { type PostType } from '@/types/post'
import { useRouter } from 'vue-router'
import { ref } from 'vue'
const router = useRouter()
import { useUserStore } from '@/stores/userStores'
import { newPostLikeApi } from '@/api/posts'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
const dialog = useCommandComponent(InfoDialog)
const userStore = useUserStore()
const img = ref()
const likeActivate = ref(false)
const props = defineProps({
  post: {
    type: Object as () => PostType,
    required: true
  }
})

const navDetailHandler = () => {
  if (router.currentRoute.value.path.includes('explore')) {
    router.push(`/home/explore/${props.post.id}`)
  } else if (router.currentRoute.value.path.includes('profile')) {
    let path = router.currentRoute.value.fullPath
    path += '/posts/'
    path += props.post.id
    router.push(path)
  } else {
    let path = router.currentRoute.value.fullPath
    path += '/'
    path += props.post.id
    router.push(path)
  }
}

const newPostLike = () => {
  let data = {
    userId: userStore.user?.id,
    postId: props.post.id
  }
  newPostLikeApi(data).then((res) => {
    if (res.code == 200) {
      props.post.likeNum = props.post.likeNum + 1
      likeActivate.value = true
      dialog({
        title: '👍',
        content: '点赞成功',
        btnContent: '👌'
      })
    } else {
      dialog({
        title: '👍',
        content: res.msg,
        btnContent: '👌'
      })
      likeActivate.value = true
    }
  })
}

const navUserProfile = () => {
  router.push(`/home/profile/${props.post.userId}`)
}
</script>

<style scoped>
.AqImage {
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.08);
}
</style>
