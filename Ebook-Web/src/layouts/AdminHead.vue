<template>
  <div>
    <div class="navbar bg-base-100">
      <div class="flex-1">
        <a class="text-primary btn btn-ghost text-xl" href="/#/admin/home">EasyBook</a>
        <span class=" badge-primary rounded-2xl px-2 py-1 font-medium">管理员</span>
      </div>
      <div class="flex-none">
        <ul class="menu menu-horizontal px-1">
          <li><a href="/#/admin/review">审核举报</a></li>
          <li>
            <details>
              <summary>Parent</summary>
              <ul class="p-2 bg-base-100 rounded-t-none z-50">
                <li><a>Link 1</a></li>
                <li><a>Link 2</a></li>
              </ul>
            </details>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped></style>
