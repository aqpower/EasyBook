<template>
  <input />
  <input />
  <div>请选择你的头像:</div>

  <input />
  <input />

  <!-- Open the modal using ID.showModal() method -->
  <button class="btn" onclick="my_modal_1.showModal()">open modal</button>
  <dialog id="my_modal_1" class="modal">
    <div class="modal-box">
      <h3 class="font-bold text-lg">Hello!</h3>
      <p class="py-4">Press ESC key or click the button below to close</p>
      <div class="modal-action">
        <form method="dialog" class="flex flex-col justify-center">
          <!-- if there is a button in form, it will close the modal -->
          <div class="flex flex-wrap">
            <Icon  v-for="link in iconList" :key="link" class="w-16 h-16 m-1" :icon="link"
            :class="{'bg-primary': ,}"
            />
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

const avatarLink = ref('')

</script>

<style scoped></style>
