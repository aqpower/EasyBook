<template>
  <div>
    <div v-for="(item, index) in messageList" :key="index">{{ item }}</div>
  </div>
</template>

<script setup lang="ts">
import { getMessageApi } from '@/api/user'
import { onMounted, ref } from 'vue'
import { useUserStore } from '@/stores/userStores'
import type { MessageType } from '@/types/message'
const page = ref(1)
const pageSize = ref(10)
const messageList = ref<MessageType[]>([])

const userStore = useUserStore()
onMounted(() => {
  let data = {
    id: userStore.user.id,
    page: page.value,
    pageSize: pageSize.value
  }
  getMessageApi(data).then((res) => {
    console.log(res)
    if (res.code == 200) {
      messageList.value = res.data.notifies
    }
  })
})
</script>

<style scoped></style>
