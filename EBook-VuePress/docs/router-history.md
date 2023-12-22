# 面包块保存路由访问记录


具体的实现思路是全局维护一个路由历史记录，同时维护当前活跃路由的下标（为了做到界面上区分活跃非活跃）

那就很简单了，维护路由历史记录，依然是在路由上进行“守卫”。

```typescript
import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useRouterHistoryStore = defineStore('routerHistory', () => {
    const routerHistory = ref([])
    const activeTagIndex = ref(0)

    function isStore(to) {
        return routerHistory.value.some((item) => {
            return item.fullPath === to.fullPath
        })
    }

    function insertRouter(to) {
        if (to.meta.showTag && !isStore(to)) {
            routerHistory.value.push(to)
        }
    }

    function deleteRouter(router) {
        if (isStore(router)) {
            routerHistory.value = routerHistory.value.filter(
                (item) => item.fullPath !== router.fullPath
            )
            activeTagIndex.value = activeTagIndex.value - 1
        }
    }

    function clearRouter() {
        routerHistory.value = [];
    }

    function setActiveIndex(index) {
        activeTagIndex.value = index
    }

    function getActiveTagIndex() {
        return activeTagIndex.value;
    }

    return {routerHistory, activeTagIndex, insertRouter, deleteRouter, clearRouter, setActiveIndex, getActiveTagIndex}
})

```
