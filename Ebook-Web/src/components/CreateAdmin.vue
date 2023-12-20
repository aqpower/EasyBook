<template>
  <div class="pt-16">
    <h1 class="text-center font-bold text-3xl">创建管理员</h1>
    <div class="flex gap-5 flex-col justify-center items-center mt-14">
      <input
        v-model="nameInput"
        placeholder="请输入管理员的姓名"
        class="input input-bordered input-info w-96"
      />
      <button class="w-96 btn btn-info btn-outline min-h-0 h-10" @click="createAdmin()">
        确定
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { createAdminApi } from '@/api/admin'
import useCommandComponent from '@/hooks/useCommandComponent'
import { ref } from 'vue'
import InfoDialog from './InfoDialog.vue'
import { useUserStore } from '@/stores/userStores'
const nameInput = ref('')
const dialog = useCommandComponent(InfoDialog)
const userStore = useUserStore()
const createAdmin = () => {
  if (nameInput.value.length == 0) {
    dialog({
      title: '😢',
      content: '输入不可以为空',
      btnContent: '👌'
    })
    return
  }
  if (nameInput.value.length > 10) {
    dialog({
      title: '😢',
      content: '姓名过长',
      btnContent: '👌'
    })
    return
  }
  let data = {
    adminId: userStore.user?.id,
    admin: {
      name: nameInput.value
    }
  }
  createAdminApi(data).then((result) => {
    if (result.code == 200) {
      let res = result.data
      const resDescription = `id: ${res.id}, name: "${res.name}", password: "${res.password}"!`
      dialog({
        title: '🥳',
        content: `创建管理员成功, ${resDescription}`,
        btnContent: '👌'
      })
    }
  })
}
</script>

<style scoped></style>
