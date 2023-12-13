<template>
  <div class="w-full break-inside-avoid">
    <figure>
      <img
        class="inline-block rounded-2xl AqImage hover:brightness-75 ease-in-out duration-500"
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
        <div class="flex items-center mt-1">
          <Icon class="w-4 h-4 m-1" :icon="avatarList[post.avatar]"></Icon>
          <p class="text-xs dark:text-slate-100 text-slate-600">{{ post.name }}</p>
        </div>
        <div class="flex items-center">
          <Icon
            class="mr-1 w-3 h-3 hover:cursor-pointer active:scale-75 ease-in-out"
            icon="icon-park-outline:like"
          />
          <span class="text-slate-600 text-xs">{{ post.likeNum }}</span>
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

const img = ref()
const props = defineProps({
  post: {
    type: Object as () => PostType,
    required: true
  }
})

const navDetailHandler = () => {
  if (router.currentRoute.value.path.includes('explore')) {
    router.push(`/home/explore/${props.post.id}`)
  } else {
    let path = router.currentRoute.value.path
    path += '/posts/'
    path += props.post.id
    router.push(path)
  }
}
</script>

<style scoped>
.AqImage {
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.08);
}
</style>
