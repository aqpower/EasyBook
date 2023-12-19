<template>
  <div class="h-16 w-full m-1">
    <div class="flex justify-around items-center h-full">
      <div class="join p-28">
        <input
          v-model="searchInput"
          class="h-9 w-96 border-0 rounded-2xl bg-base-200 input join-item text-base-content"
          placeholder="搜索小破书"
        />
        <button
          class="h-9 btn bg-base-200 join-item rounded-r-full min-h-0 text-base-content"
          @click="searchPosts"
        >
          <Icon class="w-5 h-5" icon="iconamoon:search" />
        </button>
      </div>
      <p class="text-2xl text-primary" style="font-family: 'Zhi Mang Xing', cursive">
        分享无止尽！
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
import { computed, onMounted, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from '@/components/InfoDialog.vue'
const dialog = useCommandComponent(InfoDialog)
const router = useRouter()
const route = useRoute()
const searchInput = ref('')

const isSearch = computed(() => {
  return router.currentRoute.value.fullPath.includes('search')
})

onMounted(() => {
  if (isSearch.value == true) {
    searchInput.value = route.params.keyword as string
  }
})

const searchPosts = () => {
  if (searchInput.value.length == 0 || searchInput.value.length > 25) {
    dialog({
      title: '😭',
      content: '搜索的信息可不能为空',
      btnContent: '👌'
    })
    return
  }
  router.push(`/home/search/${searchInput.value}`).then(() => {
    window.location.reload()
  })
}
</script>

<style scoped></style>
