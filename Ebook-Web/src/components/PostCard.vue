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
const picked = ref(false)
const translateX = ref(0)
const translateY = ref(0)
const newWidth = ref(0)
const img = ref()
const props = defineProps({
  post: {
    type: Object as () => PostType,
    required: true
  }
})

const { innerWidth: winWidth, innerHeight: winHeight } = window

// 计算自适应屏幕的缩放值
function adaptScale() {
  const { offsetWidth: w, offsetHeight: h } = img.value // 获取文档中图片的宽高
  let scale = 0
  scale = winWidth / w
  if (h * scale > winHeight - 80) {
    scale = (winHeight - 80) / h
  }
  return scale
}

const navDetailHandler = () => {
  const { offsetWidth: offsetWidth, offsetHeight: offsetHeight } = img.value // 获取文档中图片的宽高
  const image = document.querySelector('img')
  const { top, left } = image.getBoundingClientRect()

  // 移动图片到屏幕中心位置
  const originalCenterPoint = { x: offsetWidth / 2 + left, y: offsetHeight / 2 + top }
  const winCenterPoint = { x: winWidth / 2, y: winHeight / 2 }
  const offsetDistance = {
    left: winCenterPoint.x - originalCenterPoint.x + left,
    top: winCenterPoint.y - originalCenterPoint.y + top
  }
  const diffs = {
    left: ((adaptScale() - 1) * offsetWidth) / 2,
    top: ((adaptScale() - 1) * offsetHeight) / 2
  }

  translateX.value = offsetDistance.left - left - diffs.left
  translateY.value = offsetDistance.top - top - diffs.top
  newWidth.value = offsetWidth * adaptScale()

  router.push(`/home/explore/${props.post.id}`)
}
</script>

<style scoped>
.AqImage {
  box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.08);
}
</style>
