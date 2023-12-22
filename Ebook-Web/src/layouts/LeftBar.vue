<template>
  <div class="fixed w-56 h-full">
    <div class="flex justify-start py-4 px-6 items-center">
      <img class="h-12" src="../assets/logo-icon-nobg.png" alt="Your Company" />
      <p class="font-extrabold text-rose-400 text-lg">EasyBook</p>
    </div>
    <div>
      <div class="flex p-3 flex-col space-y-3">
        <button
          class="btn btn-neutral h-10 min-h-min w-56 justify-start items-center gap-3"
          @click="menuHandler"
          :class="{ '': menuVar == 1, ' bg-transparent shadow-transparent border-0': menuVar != 1 }"
        >
          <Icon
            class="ml-1 w-5 h-5"
            icon="line-md:home-md"
            :class="{
              '': menuVar == 1,
              'text-base-content': menuVar != 1
            }"
          />
          <span
            class="text-base font-medium"
            :class="{
              '': menuVar == 1,
              'text-base-content': menuVar != 1
            }"
            >发现</span
          >
        </button>
        <label
          for="my_modal_6"
          class="btn btn-neutral h-10 min-h-min w-56  justify-start items-center gap-3"
          @click="menuHandler"
          :class="{
            '': menuVar == 2,
            'border-0 shadow-transparent bg-transparent': menuVar != 2
          }"
        >
          <Icon
            class="ml-1 w-5 h-5"
            icon="line-md:clipboard-plus"
            :class="{
              '': menuVar == 2,
              'text-base-content': menuVar != 2
            }"
          />
          <span
            class="text-base font-medium font-sans"
            :class="{
              '': menuVar == 2,
              'text-base-content': menuVar != 2
            }"
            >发布</span
          >
        </label>
        <button
          class="btn btn-neutral h-10 min-h-min w-56 justify-start items-center gap-3"
          :class="{ '': menuVar == 3, 'bg-transparent shadow-transparent border-0': menuVar != 3 }"
          @click="menuHandler"
        >
          <Icon
            class="ml-1 w-5 h-5"
            icon="line-md:telegram"
            :class="{
              '': menuVar == 3,
              'text-base-content': menuVar != 3
            }"
          />
          <span
            class="text-base font-medium font-sans"
            :class="{
              '': menuVar == 3,
              'text-base-content': menuVar != 3
            }"
            >通知</span
          >
        </button>
        <button
          class="btn btn-neutral h-10 min-h-min w-56 justify-start items-center gap-3"
          :class="{ '': menuVar == 4, ' bg-transparent shadow-transparent border-0': menuVar != 4 }"
          @click="menuHandler"
        >
          <Icon
            class="ml-1 w-5 h-5"
            :icon="avatarList[userStore.user?.avatar]"
            :class="{
              '': menuVar == 4,
              'text-base-content': menuVar != 4
            }"
          />

          <span
            class="text-base font-medium font-sans"
            :class="{
              '': menuVar == 4,
              'text-base-content': menuVar != 4
            }"
            >我</span
          >
        </button>
      </div>

      <div class="absolute bottom-2 pl-3">
        <EasyMenuVue class="mb-3" v-show="showMenu == true"></EasyMenuVue>
        <button
          class="btn h-10 min-h-min w-56 rounded-3xl justify-start items-center gap-3"
          @click="menuHandler"
          :class="{ '': menuVar == 5, ' bg-transparent shadow-transparent border-0': menuVar != 5 }"
        >
          <Icon class="ml-1 w-5 h-5" icon="line-md:close-to-menu-alt-transition" />
          <span class="text-base font-medium font-sans">更多</span>
        </button>
      </div>

      <input type="checkbox" id="my_modal_6" class="modal-toggle" />
      <div class="modal" role="dialog" style="margin: 0">
        <div class="modal-box w-11/12 h-11/12 max-w-5xl p-0" style="height: 80%">
          <div class="modal-action justify-start flex-col w-full h-full m-0">
            <div class="w-full h-full">
              <div class="m-5" v-if="newModelShow">
                <NewPost ref="newPostRef"></NewPost>
              </div>
            </div>
            <div class="space-x-5 flex" style="margin-left: 2rem">
              <label for="my_modal_6" class="btn btn-primary mb-3 w-36" @click="newPostHandler()"
                >提交</label
              >
              <label for="my_modal_6" class="btn mb-3 w-36" @click="newModelShow = false"
                >放弃</label
              >
            </div>
          </div>
        </div>
      </div>
    </div>

    <InfoDialogVue :visible="loadingShow" title="😊">
      <template #content>
        <div class="flex justify-center">
          <p>正在上传，请耐心等待~ 😊</p>
          <span class="loading loading-spinner loading-md"></span>
        </div>
      </template>
      <template #btn-content>
        <div class="flex justify-center">
          <span class="loading loading-spinner loading-md"></span>
        </div>
      </template>
    </InfoDialogVue>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import NewPost from '@/components/NewPost.vue'
import eventBus from '@/libs/eventBus'
import InfoDialogVue from '@/components/InfoDialog.vue'
import { avatarList } from '@/utils/icon'
import { useUserStore } from '@/stores/userStores'
import { useRouter } from 'vue-router'
import EasyMenuVue from '@/components/EasyMenu.vue'
const newModelShow = ref(false)
const newPostRef = ref<any>()
const loadingShow = ref(false)
const menuVar = ref(1)
const router = useRouter()
const showMenu = ref(false)
const newPostHandler = () => {
  newPostRef.value.newPost()
}

onMounted(() => {
  if (router.currentRoute.value.fullPath.includes('profile')) {
    menuVar.value = 4
  } else if (router.currentRoute.value.fullPath.includes('message')) {
    menuVar.value = 3
  }
})

const userStore = useUserStore()

eventBus.on('startUpload', (e) => {
  loadingShow.value = true
})

eventBus.on('postFinish', (e) => {
  loadingShow.value = false
})

const menuHandler = (e: any) => {
  switch (e.srcElement.innerText) {
    case '发现':
      router.push('/home/explore').then(() => {
        window.location.reload()
      })
      menuVar.value = 1
      break
    case '发布':
      newModelShow.value = true
      break
    case '通知':
      menuVar.value = 3
      router.push('/home/message')
      break
    case '我':
      menuVar.value = 4
      router.push(`/home/profile/${userStore.user?.id}`).then(() => {
        window.location.reload()
      })
      break
    case '更多':
      showMenu.value = !showMenu.value
  }
}
</script>

<style scoped></style>
