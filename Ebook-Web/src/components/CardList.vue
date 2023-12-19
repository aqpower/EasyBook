<template>
  <div class="flex flex-col h-full">
    <div class="flex mb-3">
      <span
        class="badge mx-2 px-3 py-4 hover:font-bold hover:bg-base-200 hover:cursor-pointer"
        :class="{ 'bg-base-300 font-bold': colorIndex == -2 }"
        @click="changeColorIndex(-2)"
      >
        梦想彩
      </span>
      <span
        class="badge mx-2 px-3 py-4 hover:bg-base-200 hover:cursor-pointer hover:font-bold"
        :class="{ 'bg-base-300 font-bold': colorIndex == -1 }"
        @click="changeColorIndex(-1)"
        v-show="isSearch == false"
      >
        我的关注
      </span>
      <div v-for="(item, index) in colorList" :key="index">
        <span
          class="badge mx-2 px-3 py-4 hover:bg-base-200 hover:cursor-pointer hover:font-bold"
          :class="{ 'bg-base-300 font-bold': colorIndex == index }"
          @click="changeColorIndex(index)"
        >
          {{ item.name }}
        </span>
      </div>
    </div>
    <div class="pr-6 flex-1 overflow-scroll">
      <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
        <!-- <div show="skeletonShow" class="skeleton w-28 h-28"></div> -->
        <PostCard v-for="(item, index) in postList" :key="index" :post="item"></PostCard>
      </div>
    </div>
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import { getColorPostsApi, getFollowerPostApi, getPostsApi, searchPostsApi } from '@/api/posts'
import type { GetPostResType } from '@/types/post'
import { computed, onMounted, ref, watch } from 'vue'
import PostCard from './PostCard.vue'
import eventBus from '@/libs/eventBus'
import { useUserStore } from '@/stores/userStores'
import { colorList } from '@/utils/var'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
const dialog = useCommandComponent(InfoDialog)
const page = ref(1)
const pageSize = ref(100)
const postList = ref([])
const userStore = useUserStore()
const skeletonShow = ref(true)
const colorIndex = ref(-2)
eventBus.on('postFinish', (e) => {
  console.log(e)
  if (e == true) {
    getPosts()
  }
})

const isSearch = computed(() => {
  return router.currentRoute.value.fullPath.includes('search')
})

const changeColorIndex = (index: number) => {
  colorIndex.value = index
  if (index == -2) {
    getPosts()
  } else if (index == -1) {
    getFollowPosts()
  } else {
    getColorPosts()
  }
}

const getPosts = () => {
  if (isSearch.value == true) {
    console.log(router)
    let data: { id: any; page: any; pageSize: any; text: string; color: number } = {
      id: userStore.user?.id,
      page: page.value,
      pageSize: pageSize.value,
      text: route.params.keyword as string
    }
    searchPostsApi(data).then((res) => {
      const data: GetPostResType = res.data
      if (res.code == 200) {
        skeletonShow.value = false
        console.log(data)
        if (data.posts.length != 0) {
          postList.value = data.posts
        } else {
          dialog({
            title: '😞',
            content: '当前分区没有帖子哦~',
            btnContent: '👌'
          })
          postList.value = []
        }
      }
    })
  } else {
    const data = {
      id: userStore.user.id,
      page: page.value,
      pageSize: pageSize.value
    }
    getPostsApi(data).then((res) => {
      const data: GetPostResType = res.data
      if (res.code == 200) {
        skeletonShow.value = false
        console.log(data)
        if (data.posts != null) {
          postList.value = data.posts
        } else {
          dialog({
            title: '😞',
            content: '当前分区没有帖子哦~',
            btnContent: '👌'
          })
          postList.value = []
        }
      }
    })
  }
}

const getColorPosts = () => {
  const data = {
    id: userStore.user.id,
    page: page.value,
    pageSize: pageSize.value,
    color: colorIndex.value
  }
  if (isSearch.value == true) {
    data.text = route.params.keyword
  }
  getColorPostsApi(data).then((res) => {
    const data: GetPostResType = res.data
    if (res.code == 200) {
      if (data.posts.length != 0) {
        postList.value = data.posts
      } else {
        console.log('a')
        dialog({
          title: '😞',
          content: '当前分区没有帖子哦~',
          btnContent: '👌'
        })
        postList.value = []
      }
    }
  })
}

const getFollowPosts = () => {
  getFollowerPostApi(userStore.user?.id, page.value, pageSize.value).then((res) => {
    const data: GetPostResType = res.data
    if (res.code == 200) {
      if (data != null) {
        postList.value = data.posts
      } else {
        dialog({
          title: '😞',
          content: '当前分区没有帖子哦~',
          btnContent: '👌'
        })
        postList.value = []
      }
    }
  })
}
onMounted(() => {
  getPosts()
})
</script>

<style scoped></style>
