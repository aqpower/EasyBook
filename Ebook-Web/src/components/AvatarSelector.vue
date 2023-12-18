<template>
  <div>
    <div class="flex justify-center items-center">
      <button
        class="hover:opacity-75 hover:scale-90 ease-in-out duration-300 active:scale-75 -mt-3"
        onclick="my_modal_1.showModal()"
      >
        <Icon class="w-20 h-20" :icon="avatarList[avatarIndex]" />
      </button>
    </div>

    <dialog id="my_modal_1" class="modal">
      <div class="modal-box w-9/12 max-w-5xl">
        <div class="modal-action">
          <form method="dialog" class="flex flex-col justify-center">
            <div class="flex flex-wrap">
              <div v-for="(link, index) in avatarList" :key="index">
                <div class="indicator relative">
                  <Icon
                    class="w-20 h-20 m-3 hover:cursor-pointer hover:opacity-75 ease-in-out duration-300 active:scale-75"
                    :icon="link"
                    :class="{ 'w-21': index == avatarIndex }"
                    @click="handleIconClick(link, index)"
                  />
                  <div class="absolute m-16" v-if="index == avatarIndex">
                    <Icon class="text-primary h-10 w-10" icon="line-md:confirm" />
                  </div>
                </div>
              </div>
            </div>
            <button class="btn mt-5">确定</button>
          </form>
        </div>
      </div>
    </dialog>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
import { avatarList } from '@/utils/icon'
import { inject, type Ref } from 'vue'

// 获取响应式变量
const avatarIndex = inject('avatarIndex') as Ref<number>
const handleIconClick = (link: String, index: number) => {
  avatarIndex.value = index
}
</script>

<style scoped></style>
