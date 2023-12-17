<template>
  <div>
    <button
      @click="props.click"
      class="min-h-0 h-9 btn btn-primary flex w-full justify-center rounded-md hover:bg-rose-300 px-3 py-1.5 leading-6 text-white shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-rose-600"
    >
      <span v-show="loadingShow" class="loading loading-bars loading-xs h-6"></span>
      <span v-show="!loadingShow" class="h-6">{{ props.content }}</span>
    </button>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const props = defineProps({
  content: {
    type: String,
    required: true
  },
  click: {
    type: Function,
    default: (fun: () => any) => fun()
  }
})
// const props = defineProps({
//   post: {
//     type: Object as () => PostType,
//     required: true
//   }
// })
const loadingShow = ref(false)

function showLoading() {
  loadingShow.value = true
}

function hideLoading() {
  loadingShow.value = false
}

defineExpose({ showLoading, hideLoading })
</script>

<style scoped></style>
