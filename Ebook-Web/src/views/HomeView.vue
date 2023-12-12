<script setup lang="ts">
import { ref } from 'vue'
import NewPost from '@/components/NewPost.vue'
import CardList from '@/components/CardList.vue'
import eventBus from '@/libs/eventBus'
import InfoDialogVue from '@/components/InfoDialog.vue'
const newModelShow = ref(false)
const loadingShow = ref(false)
const newPostRef = ref<any>()
const newPostHandler = () => {
  newPostRef.value.newPost()
  loadingShow.value = true
}

eventBus.on('postFinish', (e) => {
  console.log(e)
  loadingShow.value = false
})
</script>

<template>
  <label for="my_modal_6" class="btn" @click="newModelShow = true">创建帖子</label>






  
  <input type="checkbox" id="my_modal_6" class="modal-toggle" />
  <div class="modal" role="dialog">
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
          <label for="my_modal_6" class="btn mb-3 w-36" @click="newModelShow = false">放弃</label>
        </div>
      </div>
    </div>
  </div>
  <InfoDialogVue :visible="loadingShow">
    <template #content>
      <div class="flex justify-center">
        <span class="loading loading-spinner loading-lg"></span>
      </div>
    </template>
    <template #btn-content>
      <div class="flex justify-center">
        <span class="loading loading-spinner loading-lg"></span>
      </div>
    </template>
  </InfoDialogVue>
  <CardList></CardList>
</template>
