<template>
  <div class="flex flex-col gap-3 items-center justify-center">
    <div class="stat shadow w-56 h-56 flex flex-col justify-center items-center">
      <div class="stat-title mb-5 font-medium">今日发帖个数</div>
      <div class="flex items-center">
        <div class="stat-value text-warning" style="font-size: 5rem">9</div>
        <div class="stat-figure text-warning">
          <Icon class="w-20 h-20" icon="iconamoon:lightning-2" />
        </div>
      </div>
      <div class="stat-desc mt-4 font-medium">{{ formattedTime }}</div>
    </div>
    <div class="stats shadow">
      <div class="stat">
        <div class="stat-figure text-primary">
          <Icon class="w-10 h-10" icon="line-md:lightbulb" />
        </div>
        <div class="stat-title">总发帖数</div>
        <div class="stat-value text-primary">31K</div>
        <div class="stat-desc">2023.12.10 - {{ formattedTime }}</div>
      </div>

      <div class="stat">
        <div class="stat-figure text-success">
          <Icon class="w-9 h-9" icon="ph:cursor-click-bold" />
        </div>
        <div class="stat-title">帖子总点击量</div>
        <div class="stat-value text-success">4,200</div>
        <div class="stat-desc">↗︎ 400 (22%)</div>
      </div>

      <div class="stat">
        <div class="stat-figure text-info">
          <Icon class="w-9 h-9" icon="iconoir:user-badge-check" />
        </div>
        <div class="stat-title">用户总数</div>
        <div class="stat-value text-info">1,200</div>
        <div class="stat-desc">↘︎ 90 (14%)</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue/dist/iconify.js'
const now = new Date() // 创建一个表示当前时间的 Date 对象

// 如果你希望将当前时间转换为特定格式的字符串，可以使用以下方法：
const year = now.getFullYear() // 获取年份
const month = now.getMonth() + 1 // 获取月份（注意月份是从 0 开始的，所以要加 1）
const day = now.getDate() // 获取日期

// 你可以根据需要自定义时间的格式
const formattedTime = `${year}.${month}.${day}`
</script>

<style scoped></style>
