<template>



  <div class="flex justify-center items-center">
    <h2 class="text-lg font-bold">头像:</h2>
    <button
      class="hover:opacity-75 hover:scale-90 ease-in-out duration-300 active:scale-75"
      onclick="my_modal_1.showModal()"
    >
      <Icon class="w-24 h-24 m-3" :icon="iconList[avatarIndex]" />
    </button>
  </div>

  <dialog id="my_modal_1" class="modal">
    <div class="modal-box w-9/12 max-w-5xl">
      <div class="modal-action">
        <form method="dialog" class="flex flex-col justify-center">
          <div class="flex flex-wrap">
            <div v-for="(link, index) in iconList" :key="index">
              <div class="indicator relative">
                <Icon
                  class="w-20 h-20 m-3 hover:opacity-75 ease-in-out duration-300 active:scale-75"
                  :icon="link"
                  :class="{ 'w-21': index == avatarIndex }"
                  @click="handleIconClick(link, index)"
                />
                <div class="absolute m-20" v-show="index == avatarIndex">✔</div>
              </div>
            </div>
          </div>
          <button class="btn">确定</button>
        </form>
      </div>
    </div>
  </dialog>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
import { iconList } from '@/utils/icon'
import { ref } from 'vue'

const avatarIndex = ref(1)

const handleIconClick = (link: String, index: number) => {
  console.log('点击了图标:', link, '索引:', index)
  avatarIndex.value = index
  // 根据 link 和 index 做相应的处理
}
</script>

<style scoped></style>
