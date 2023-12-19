<template>
  <div>
    <div class="overflow-x-auto">
      <table class="table">
        <thead>
          <tr>
            <th>最受欢迎头像</th>
            <th>Icon</th>
            <th>使用次数</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in welAvatarList" :key="index">
            <th class="text-center">{{ index + 1 }}</th>
            <td><Icon class="w-9 h-9" :icon="avatarList[item.avatar]"></Icon></td>
            <td class="text-center">{{ item.count }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { getPopularAvatarApi, type WelcomeAvatar } from '@/api/admin'
import { onMounted, ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import { avatarList } from '@/utils/icon'
onMounted(() => {
  getWelcomeAvatar()
})

const welAvatarList = ref<WelcomeAvatar[]>()

const getWelcomeAvatar = () => {
  getPopularAvatarApi().then((res) => {
    if (res.code == 200) {
      welAvatarList.value = res.data
    }
  })
}
</script>

<style scoped></style>
