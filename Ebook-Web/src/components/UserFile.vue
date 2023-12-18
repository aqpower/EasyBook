<template>
  <div>
    <div class="flex justify-center mt-14 items-center">
      <Icon v-if="avatarIndex != -1" class="w-28 h-28 m-1" :icon="avatarList[avatarIndex]"></Icon>
      <div v-if="user" class="ml-3 flex flex-col gap-3">
        <div class="badge badge-primary">ID: {{ user.id }}</div>
        <div class="flex items-center gap-1">
          <p class="text-xl font-bold">{{ user.name }}</p>
          <div class="cursor-pointer" v-show="isMe == true" @click="showUpdateModal = true">
            <Icon icon="solar:pen-2-bold" />
          </div>
        </div>
        <div class="flex ml-2 gap-2">
          <p
            @click="isMe == true && (showFollowersModal = true)"
            :class="{ 'hover:cursor-pointer': isMe == true }"
          >
            <span class="font-extrabold mr-1">{{ user.followNum }}</span
            >关注
          </p>
          <p
            @click="isMe == true && (showFansModal = true)"
            :class="{ 'hover:cursor-pointer': isMe == true }"
          >
            <span class="font-extrabold mr-1">{{ user.fansNum }}</span
            >粉丝
          </p>
        </div>
      </div>
      <div class="flex gap-3 ml-4">
        <button
          class="btn btn-info btn-outline min-h-0 h-8"
          @click="followUser"
          v-show="isMe == false && follow == false && black == false"
        >
          关注
        </button>
        <button
          class="btn btn-success btn-outline min-h-0 h-8"
          @click="cancelFollow"
          v-show="isMe == false && follow == true"
        >
          ✅已关注
        </button>
        <button
          class="btn btn-error btn-outline min-h-0 h-8"
          v-show="isMe == false && black == false && follow == false"
          @click="newBlack"
        >
          拉黑
        </button>
        <button
          class="btn btn-error btn-outline min-h-0 h-8"
          v-show="isMe == false && black == true"
          @click="cancelBlack"
        >
          ⭕已拉黑
        </button>
      </div>
    </div>
    <div class="divider text-gray-400">帖子</div>
    <div class="pr-6 mt-5">
      <div class="flex justify-center mt-10 text-xl text-gray-400" v-show="userPosts.length == 0">
        该用户没有发送过帖子
      </div>
      <div class="sm:columns-3 md:columns-3 lg:columns-4 xl:columns-5">
        <PostCard v-for="(item, index) in userPosts" :key="index" :post="item"></PostCard>
      </div>
    </div>
  </div>
  <InfoDialog :visible="showUpdateModal">
    <template #content>
      <div class="flex flex-col gap-7 items-center font-bold">
        <p>修改个人信息</p>
        <AvatarSelector></AvatarSelector>
        <input class="input input-primary h-12 w-full" v-model="userNameInput" />
        <button class="btn btn-primary min-h-0 h-9 w-full" @click="updateUserInfo">确定</button>
      </div>
    </template>
  </InfoDialog>
  <InfoDialog :visible="showFollowersModal">
    <template #content>
      <div class="flex flex-col items-center font-bold">
        <FollowList></FollowList>
        <button class="mt-4 btn min-h-0 h-9 w-full" @click="showFollowersModal = false">
          确定
        </button>
      </div>
    </template>
  </InfoDialog>
  <InfoDialog :visible="showFansModal">
    <template #content>
      <div class="flex flex-col items-center font-bold">
        <FansList></FansList>
        <button class="mt-4 btn min-h-0 h-9 w-full" @click="showFansModal = false">确定</button>
      </div>
    </template>
  </InfoDialog>
  <RouterView></RouterView>
</template>

<script setup lang="ts">
import InfoDialog from './InfoDialog.vue'
import { getUserPostApi } from '@/api/posts'
import { computed, onMounted, provide, ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import type { UserPostResType } from '@/types/post'
import { useRoute } from 'vue-router'
import PostCard from './PostCard.vue'
import { avatarList } from '@/utils/icon'
import {
cancelBlackApi,
  cancelUserFollowApi,
  followUserApi,
  getUserInfoApi,
  newUserBlackApi,
  updateUserInfoApi
} from '@/api/user'
import AvatarSelector from './AvatarSelector.vue'
import { useUserStore } from '@/stores/userStores'
import useCommandComponent from '@/hooks/useCommandComponent'
import FollowList from '@/components/FollowList.vue'
import FansList from './FansList.vue'
const userStore = useUserStore()
const dialog = useCommandComponent(InfoDialog)
const userNameInput = ref<string>(userStore.user?.name as string)
const avatarIndex = ref(-1)
const showUpdateModal = ref(false)
const showFollowersModal = ref(false)
const follow = ref(false)
const black = ref(false)
const showFansModal = ref(false)
const page = ref(1)
const pageSize = ref(20)
// 声明一个响应性变量并 provide 其自身
// 孙组件获取后可以保持响应性
provide('avatarIndex', avatarIndex)
const userPosts = ref<UserPostResType[]>([])
const route = useRoute()
const user = ref()
const id = userStore.user?.id as string
const userId = route.params.userId
onMounted(() => {
  getUserInfoApi(id, userId).then((res) => {
    console.log(res)
    if (res.code == 200) {
      user.value = res.data
      follow.value = user.value.followed
      black.value = user.value.blacked
      avatarIndex.value = user.value.avatar
    }
  })
  getUserPostApi(userId, page.value, pageSize.value).then((res) => {
    console.log(res)
    if (res.code == 200) {
      userPosts.value = res.data.posts
    }
  })
})

const isMe = computed((): boolean => {
  if (route.params.userId != null) {
    if (route.params.userId == userStore.user?.id) {
      return true
    }
  }
  return false
})

const followUser = () => {
  let data = {
    careUserId: id,
    caredUserId: userId
  }
  followUserApi(data).then((res) => {
    if (res.code == 200) {
      dialog({
        title: '🥳',
        content: '关注成功！',
        btnContent: '👌'
      })
      follow.value = true
    } else {
      dialog({
        title: '😢',
        content: res.msg,
        btnContent: '👌'
      })
    }
  })
}

const newBlack = () => {
  let data = {
    userId: id,
    blackUserId: userId
  }
  newUserBlackApi(data).then((res) => {
    if (res.code == 200) {
      dialog({
        title: '🥳',
        content: '拉黑成功！',
        btnContent: '👌'
      })
      black.value = true
    }
  })
}

const cancelBlack = () => {
  let data = {
    userId: id,
    blackUserId: userId
  }
  cancelBlackApi(data).then(res => {
    if(res.code == 200){
      dialog({
        title: '🥳',
        content: '取消拉黑成功！',
        btnContent: '👌'
      })
      black.value = false
    }
  })
}

const cancelFollow = () => {
  let data = {
    careUserId: id,
    caredUserId: userId
  }
  cancelUserFollowApi(data).then((res) => {
    if (res.code == 200) {
      dialog({
        title: '🥳',
        content: '取消关注成功！',
        btnContent: '👌'
      })
      follow.value = false
    }
  })
}

const updateUserInfo = () => {
  if (userNameInput.value.length == 0) {
    dialog({
      title: '😢',
      content: '用户名不可以为空',
      btnContent: '👌👌👌'
    })
    return
  }
  let data = {
    id: userStore.user?.id as string,
    avatar: avatarIndex.value,
    name: userNameInput.value
  }
  updateUserInfoApi(data).then((res) => {
    console.log(res)
    if (res.code == 200) {
      userStore.updateInfo(avatarIndex.value, userNameInput.value)
      user.value.name = userNameInput.value
      dialog({
        title: '😊',
        content: '用户信息更改成功',
        btnContent: '👌👌👌',
        onClose: () => {
          showUpdateModal.value = false
        }
      })
    }
  })
}
</script>

<style scoped></style>
