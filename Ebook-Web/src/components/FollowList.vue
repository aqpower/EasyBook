<script setup lang="ts">
import { cancelUserFollowApi, getFollowersApi } from '@/api/user'
import { useUserStore } from '@/stores/userStores'
import { onMounted, ref } from 'vue'
import { Icon } from '@iconify/vue/dist/iconify.js'
import { useRouter } from 'vue-router'
import useCommandComponent from '@/hooks/useCommandComponent'
import InfoDialog from './InfoDialog.vue'
const dialog = useCommandComponent(InfoDialog)
const router = useRouter()
const userStore = useUserStore()
const id = userStore.user?.id
const followerList = ref([])
import { avatarList } from '@/utils/icon'
onMounted(() => {
  getFollowers()
})

const getFollowers = () => {
  getFollowersApi(id as string).then((res) => {
    console.log(res)
    if (res.code == 200) {
      followerList.value = res.data
    }
  })
}

const navUserProfile = (id: string) => {
  router.push(`/home/profile/${id}`).then(() => {
    window.location.reload()
  })
}

const cancelFollow = (cancerId: string) => {
  let data = {
    careUserId: id,
    caredUserId: cancerId
  }
  cancelUserFollowApi(data).then((res) => {
    if (res.code == 200) {
      dialog({
        title: '🥳',
        content: '取消关注成功！',
        btnContent: '👌',
        onClose: () => {
          getFollowers()
        }
      })
    }
  })
}
</script>

<template>
  <div class="flex gap-3 flex-col w-full overflow-scroll max-h-96">
    <p>关注列表</p>
    <div class="divider" v-show="followerList.length == 0">你还没有关注过用户哦~🙏</div>
    <div v-for="(item, index) in followerList" :key="index">
      <!-- {{ item }} -->
      <div class="flex gap-3 items-center justify-between hover:cursor-pointer">
        <div class="flex gap-2 items-center" @click="navUserProfile(item.id)">
          <Icon class="w-9 h-9" :icon="avatarList[item.avatar]"></Icon>
          <p>{{ item.name }}</p>
        </div>
        <div>
          <button class="btn btn-warning btn-outline min-h-0 h-9" @click="cancelFollow(item.id)">
            取消关注
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
