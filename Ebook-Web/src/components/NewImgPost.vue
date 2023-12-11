<template>
  <div>
    <div v-show="!initUpload" class="flex items-center">
      <p class="text-lg font-note font-medium">图片编辑</p>
    </div>
    <label
      v-show="initUpload"
      for="imgInput"
      class="outline-dashed rounded-md outline-1 outline-offset-1 hover:outline-primary outline-neutral-content bg-base-200 w-full h-full flex py-36 flex-col justify-center items-center"
    >
      <Icon class="w-20 h-20 text-primary" icon="line-md:cloud-up" />
      <p>拖拽图片到此或点击上传</p>
      <p class="text-sm text-neutral">(最多支持9张)</p>
    </label>
    <input
      id="imgInput"
      type="file"
      class="hidden"
      accept="image/*"
      @change="handleFileChange"
      multiple
    />
    <div class="flex flex-row flex-nowrap mt-10">
      <div v-for="(imageData, index) in imagePreviews" :key="index">
        <img :src="imageData" alt="Uploaded Image" class="w-48 h-32 object-cover mr-2" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
const imagePreviews = ref([])
import { Icon } from '@iconify/vue/dist/iconify.js'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
const initUpload = ref(true)
const dialog = useCommandComponent(InfoDialog)
function handleFileChange(event: any) {
  if (initUpload.value == true) {
    initUpload.value = false
  }
  const files = event.target.files
  if (files) {
    getImagePreviews(files)
  }
}

function getImagePreviews(files: any) {
  for (let i = 0; i < files.length; i++) {
    const reader = new FileReader()
    reader.onload = () => {
      if (imagePreviews.value.length == 9) {
        dialog({ content: '最多只允许添加9张照片，超出部分未上传!', btnContent: '👌' })
      } else {
        imagePreviews.value.push(reader.result)
      }
    }
    reader.readAsDataURL(files[i])
  }
}
</script>

<style scoped></style>
