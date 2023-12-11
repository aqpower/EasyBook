<template>
  <div>
    <div
      class="flex overflow-x-auto overflow-y-hidden border-b border-gray-200 dark:border-gray-700"
    >
      <button
        class="inline-flex items-center h-10 px-2 py-2 -mb-px text-center text-primary bg-transparent border-b-4 border-primary sm:px-4 -px-1 dark:border-blue-400 dark:text-blue-300 whitespace-nowrap focus:outline-none"
      >
        <Icon class="w-6 h-6 mr-1" icon="line-md:image" />
        <span class="mx-1 text-sm sm:text-base"> 上传图文 </span>
      </button>

      <button
        class="inline-flex items-center h-10 px-2 py-2 -mb-px text-center text-gray-400 bg-transparent border-b-2 border-transparent sm:px-4 -px-1 dark:text-white whitespace-nowrap cursor-base focus:outline-none hover:border-gray-400"
      >
        <Icon icon="line-md:mastodon" class="w-6 h-6 mr-1" />
        <span class="mx-1 text-sm sm:text-base"> 上传歌词 </span>
      </button>
    </div>

    <div class="flex items-center">
      <p class="text-lg font-note font-medium">图片编辑</p>
    </div>
    <input
      type="file"
      class="file-input file-input-bordered w-full max-w-xs"
      accept="image/*"
      @change="handleFileChange"
      multiple
    />
    <div class="flex flex-row flex-wrap mt-10">
      <div v-for="(imageData, index) in imagePreviews" :key="index">
        <img :src="imageData" alt="Uploaded Image" class="w-48 h-32 object-cover mr-2" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Icon } from '@iconify/vue'
const imagePreviews = ref([])

function handleFileChange(event: any) {
  const files = event.target.files
  if (files) {
    getImagePreviews(files)
  }
}

function getImagePreviews(files: any) {
  for (let i = 0; i < files.length; i++) {
    const reader = new FileReader()
    reader.onload = () => {
      imagePreviews.value.push(reader.result)
    }
    reader.readAsDataURL(files[i])
  }
}
</script>

<style scoped></style>
