<template>
  <div>
    <div class="overflow-x-auto">
      <table class="table">
        <thead>
          <tr>
            <th>举报项编号</th>
            <th>被举报帖子ID</th>
            <th>举报用户ID</th>
            <th>举报时间</th>
            <th>举报原因</th>
            <th>处理</th>
          </tr>
        </thead>
        <tbody>
          <tr class="hover" v-for="(item, index) in vioList" :key="index">
            <th>{{ item.id }}</th>
            <th>
              {{ item.postId }}
            </th>
            <th>{{ item.userId }}</th>
            <th>{{ formatTime(item.violationTime) }}</th>
            <th>{{ item.violationReason }}</th>
            <th class="flex gap-3">
              <button class="btn btn-success btn-outline min-h-0 h-8">查看帖子详情</button>
              <button class="min-h-0 h-8 btn btn-info btn-outline" @click="vioHandler(1, item.id)">
                删帖
              </button>
              <button
                class="min-h-0 h-8 btn btn-warning btn-outline"
                @click="vioHandler(2, item.id)"
              >
                禁言
              </button>
              <button class="min-h-0 h-8 btn btn-error btn-outline" @click="vioHandler(3, item.id)">
                封禁
              </button>
            </th>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="flex justify-center mt-3">
      <div class="join">
        <button
          class="join-item btn"
          v-for="item in pageCount"
          :key="item"
          :class="{ 'btn-active': item == page }"
          @click="changePage(item)"
        >
          {{ item }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { getViolationsApi, type Violation } from '@/api/admin'
import { formatTime } from '@/utils/time'
import { onMounted, ref } from 'vue'

const page = ref(1)
const pageSize = ref(10)
const vioList = ref<Violation[]>()
const pageCount = ref(0)

const changePage = (newPage: number) => {
  page.value = newPage
  getVios()
}

const getVios = () => {
  getViolationsApi(page.value, pageSize.value).then((res) => {
    if (res.code == 200) {
      pageCount.value = Math.ceil(res.data.count / pageSize.value)
      vioList.value = res.data.violation
    }
  })
}

onMounted(() => {
  getVios()
})

const vioHandler = (type: number, vioId: number) => {
  console.log(type, vioId)
}
</script>

<style scoped></style>
