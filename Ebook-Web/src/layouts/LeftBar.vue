<template>
  <div class="fixed w-56 h-full">
    <div class="flex justify-start py-4 px-6 items-center">
      <img class="h-12" src="../assets/logo-icon-nobg.png" alt="Your Company" />
      <p class="font-extrabold text-rose-400 text-lg">EasyBook</p>
    </div>
    <div>
      <div class="flex p-3 flex-col space-y-3">
        <button
          class="btn h-10 min-h-min w-56 rounded-3xl justify-start items-center gap-3"
          @click="menuHandler"
          :class="{ '': menuVar == 1, ' bg-transparent shadow-transparent border-0': menuVar != 1 }"
        >
          <Icon class="ml-1 w-5 h-5" icon="line-md:home-md" />
          <span class="text-base font-medium">发现</span>
        </button>
        <label
          for="my_modal_6"
          class="btn h-10 min-h-min w-56 rounded-3xl justify-start items-center gap-3"
          @click="menuHandler"
          :class="{
            'btn ': menuVar == 2,
            'border-0 shadow-transparent bg-transparent': menuVar != 2
          }"
        >
          <Icon class="ml-1 w-5 h-5" icon="line-md:clipboard-plus" />
          <span class="text-base font-medium font-sans">发布</span>
        </label>
        <button
          class="btn h-10 min-h-min w-56 rounded-3xl justify-start items-center gap-3"
          :class="{ '': menuVar == 3, ' bg-transparent shadow-transparent border-0': menuVar != 3 }"
          @click="menuHandler"
        >
          <Icon class="ml-1 w-5 h-5" icon="line-md:telegram" />
          <span class="text-base font-medium font-sans">通知</span>
        </button>
        <button
          class="btn h-10 min-h-min w-56 rounded-3xl justify-start items-center gap-3"
          :class="{ '': menuVar == 4, ' bg-transparent shadow-transparent border-0': menuVar != 4 }"
          @click="menuHandler"
        >
          <Icon class="ml-1 w-5 h-5" :icon="avatarList[userStore.user?.avatar]" />

          <span class="text-base font-medium font-sans">我</span>
        </button>
      </div>

      <div class="absolute bottom-2 pl-3">
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

    <InfoDialogVue :visible="loadingShow" title="正在上传，请耐心等待！😍">
      <template #content>
        <div class="flex justify-center mt-6">
          <span class="loading loading-spinner loading-lg"></span>
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
import { ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import NewPost from '@/components/NewPost.vue'
import eventBus from '@/libs/eventBus'
import InfoDialogVue from '@/components/InfoDialog.vue'
import { avatarList } from '@/utils/icon'
import { useUserStore } from '@/stores/userStores'
const newModelShow = ref(false)
const newPostRef = ref<any>()
const loadingShow = ref(false)
const menuVar = ref(1)

const newPostHandler = () => {
  newPostRef.value.newPost()
  loadingShow.value = true
}

const userStore = useUserStore()

eventBus.on('postFinish', (e) => {
  console.log(e)
  loadingShow.value = false
})

const menuHandler = (e: any) => {
  console.log(e)
  switch (e.srcElement.innerText) {
    case '发现':
      menuVar.value = 1
      break
    case '发布':
      newModelShow.value = true
      break
    case '通知':
      menuVar.value = 3
      break
    case '我':
      menuVar.value = 4
      break
    case '更多':
      menuVar.value = 5
  }
}
</script>

<style scoped></style>
