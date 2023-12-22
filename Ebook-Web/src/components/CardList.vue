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
    <div ref="scrollContainer" @scroll="handleScroll" class="pr-6 flex-1 overflow-scroll">
      <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
        <!-- <div show="skeletonShow" class="skeleton w-28 h-28"></div> -->
        <PostCard v-for="(item, index) in postList" :key="index" :post="item"></PostCard>
      </div>
    </div>
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts">
import {  getFollowerPostApi, getPostsApi, searchPostsApi } from '@/api/posts'
import type { GetPostResType, PostType } from '@/types/post'
import { computed, onMounted, ref } from 'vue'
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
const pageSize = ref(35)
const postList = ref<PostType[]>([])
const userStore = useUserStore()
const colorIndex = ref(-2)
const scrollContainer = ref(null) // 创建一个 ref 来获取滚动容器的引用
eventBus.on('postFinish', (e) => {
  console.log(e)
  if (e == true) {
    getPosts()
  }
})
onMounted(() => {
  getPosts()
})

// 锁🔒
let isFetching = false // 增加一个标志位来表示是否正在获取数据

const handleScroll = () => {
  if (!isFetching) {
    // 如果没有在获取数据，则执行操作
    const container = scrollContainer.value
    if (container) {
      const { scrollTop, clientHeight, scrollHeight } = container
      // 检查是否滚动到页面底部（这里假设滚动到离底部 200px 的地方）
      if (scrollTop + clientHeight >= scrollHeight) {
        isFetching = true // 设置标志位为 true，表示开始获取数据
        // 更新页面值
        page.value += 1
        getPosts()
      }
    }
  }
}

const isSearch = computed(() => {
  return router.currentRoute.value.fullPath.includes('search')
})

const changeColorIndex = (index: number) => {
  page.value = 1
  colorIndex.value = index
  if (index == -2) {
    getPosts()
  } else if (index == -1) {
    getFollowPosts()
  } else {
    getPosts()
  }
}

const getPosts = () => {
  const data: {
    id: any
    page: any
    pageSize: any
    color?: number
    text?: string
  } = {
    id: userStore.user?.id,
    page: page.value,
    pageSize: pageSize.value,
    color: undefined,
    text: undefined
  }
  if (colorIndex.value >= 0) {
    data.color = colorIndex.value // 仅在颜色索引值存在时才赋值
  }
  if (isSearch.value == true) {
    console.log(router)
    data.text = route.params.keyword as string
    searchPostsApi(data).then((res) => {
      const data: GetPostResType = res.data
      if (res.code == 200) {
        console.log(data)
        if (data.posts.length != 0) {
          if (isFetching) {
            data.posts.forEach((newPost) => {
              postList.value.push(newPost)
            })
          } else {
            postList.value = data.posts
          }
        } else {
          if (isFetching) {
            noPostDia('已经没有帖子啦')
          } else {
            noPostDia('当前分区没有帖子哦~')
          }
        }
      }
      isFetching = false
    })
  } else {
    getPostsApi(data).then((res) => {
      const data: GetPostResType = res.data
      if (res.code == 200) {
        console.log(data)
        if (data.posts.length != 0) {
          if (isFetching) {
            data.posts.forEach((newPost) => {
              postList.value.push(newPost)
            })
          } else {
            postList.value = data.posts
          }
        } else {
          if (isFetching) {
            noPostDia('已经没有帖子啦')
          } else {
            noPostDia('当前分区没有帖子哦~')
          }
        }
      }
      isFetching = false
    })
  }
}

const noPostDia = (msg) => {
  dialog({
    title: '😞',
    content: msg,
    btnContent: '👌'
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
</script>

<style scoped></style>
