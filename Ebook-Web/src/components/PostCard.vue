<template>
  <div class="w-full m-2 break-inside-avoid">
    <figure>
      <img class="rounded-3xl AqImage" :src="post.url[0]" :alt="post.title" />
    </figure>
    <div class="mx-4 my-3">
      <h1 class="text-sm font-medium font-sans">{{ post.title }}</h1>
      <div class="flex justify-between">
        <div class="flex items-center mt-1">
          <Icon class="w-4 h-4 m-1" :icon="iconList[post.avatar]"></Icon>
          <p class="text-xs dark:text-slate-100 text-slate-600">{{ post.name }}</p>
        </div>
        <div class="flex items-center">
          <Icon class="mr-1 w-3 h-3" icon="icon-park-outline:like" />
          <span class="text-slate-600 text-xs">365</span>
        </div>
      </div>
      <div></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
import { iconList } from '@/utils/icon'
import { type PostType } from '@/types/post'
const props = defineProps({
  post: {
    type: Object as () => PostType,
    required: true
  }
})
</script>

<style scoped>
.AqImage {
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.08);
}
</style>
