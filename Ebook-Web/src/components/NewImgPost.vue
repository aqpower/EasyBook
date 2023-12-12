<template>
  <div>
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
    <div class="flex mt-1" v-show="!initUpload">
      <div class="carousel rounded-box">
        <div v-for="(imageData, index) in imagePreviews" :key="index" class="carousel-item">
          <img
            :src="imageData"
            alt="Uploaded Image"
            class="w-48 h-32 object-cover mr-2 rounded-md AqImage"
          />
        </div>
      </div>
    </div>
    <div class="space-y-3 mt-5 flex flex-col justify-center" v-show="!initUpload">
      <input
        v-model="titleInput"
        type="text"
        placeholder="填写标题，会有更多赞哦"
        class="input input-bordered w-full h-10"
      />
      <textarea
        v-model="contentInput"
        class="textarea textarea-bordered"
        placeholder="填写更全面的描述信息"
      >
      </textarea>
      <label>请选择颜色分区</label>
      <div class="flex">
        <div
          v-for="(item, index) in colorList"
          :key="index"
          class="btn flex mr-3 p-1 items-center gap-1"
          :style="hovered === index || index == activateColor ? { backgroundColor: item.var } : {}"
          @mouseover="hovered = index"
          @click="activateColor = index"
          @mouseleave="hovered = -1"
        >
          <div :style="{ backgroundColor: item.var }" class="h-2 w-2 p-2 rounded-lg"></div>
          <div class="badge">
            <p>{{ item.name }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
import { colorList } from '@/utils/var'
import { uploadImgApi, newPostApi } from '@/api/posts'
import { type PostApiType } from '@/types/post'
import { useUserStore } from '@/stores/userStores'
const imagePreviews = ref([])
let imageFiles = ref([])
const initUpload = ref(true)
const dialog = useCommandComponent(InfoDialog)
const hovered = ref(-1)
const activateColor = ref(-1)
const titleInput = ref('')
const contentInput = ref('')
const handleFileChange = (event: any) => {
  if (initUpload.value == true) {
    initUpload.value = false
  }
  const files = event.target.files
  console.log(files)
  if (files) {
    imageFiles.value = files
    getImagePreviews(files)
  }
}

const userStore = useUserStore()

const getImagePreviews = (files: any) => {
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
  console.log(imagePreviews.value)
}

const newImgPost = () => {
  const formData = new FormData()
  console.log('files: ', imageFiles.value)
  for (let i = 0; i < imageFiles.value.length; i++) {
    formData.append('image', imageFiles.value[i])
  }
  uploadImgApi(formData).then((res) => {
    console.log(res)
    const userId: number = userStore.user?.id // 假设默认值为 0
    const data: PostApiType = {
      type: 0,
      title: titleInput.value,
      contentText: contentInput.value,
      color: activateColor.value,
      lyrics: null,
      ip: '',
      userId: parseInt(userStore.user?.id),
      urls: res.data
    }
    newPostApi(data).then((res) => {
      console.log(res)
    })
  })
}

defineExpose({ newImgPost })
</script>

<style scoped></style>
