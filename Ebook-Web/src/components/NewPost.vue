<template>
  <div class="w-full h-full">
    <div
      class="flex overflow-x-auto overflow-y-hidden border-b border-gray-200 dark:border-gray-700"
    >
      <button
        class="inline-flex font-note font-medium h-12 px-2 py-2 -mb-px text-center bg-transparent sm:px-4 -px-1 dark:border-blue-400 dark:text-blue-300 whitespace-nowrap focus:outline-none"
        :class="{
          'text-primary border-b-4 border-primary': tabImgValue,
          'text-gray-400': !tabImgValue
        }"
        @click="tabImgValue = true"
      >
        <Icon class="w-6 h-6 mr-1" icon="line-md:image" />
        <span class="mx-1 text-sm sm:text-base"> 上传图文 </span>
      </button>

      <button
        class="ml-5 inline-flex font-note h-12 px-2 py-2 -mb-px text-center sm:px-4 -px-1 cursor-base focus:outline-none"
        :class="{
          'text-primary border-b-4 border-primary': tabImgValue == false,
          'text-gray-400': tabImgValue
        }"
        @click="tabImgValue = false"
      >
        <Icon icon="line-md:mastodon" class="w-6 h-6 mr-1" />
        <span class="mx-1 text-sm sm:text-base"> 上传歌词 </span>
      </button>
    </div>
    <div class="m-3 h-full">
      <NewImgPost v-if="tabImgValue" ref="imagePostRef"></NewImgPost>
      <NewLyicPost v-else></NewLyicPost>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { ref, watch } from 'vue'
import NewImgPost from './NewImgPost.vue'
import NewLyicPost from './NewLyicPost.vue'
const tabImgValue = ref(true)

const imagePostRef = ref<any>()

const newPost = () => {
  imagePostRef.value.newImgPost()
}

defineExpose({ newPost })
</script>

<style scoped></style>
