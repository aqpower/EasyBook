# 路由

## 路由权限问题
路由权限主要是前端路由的问题，如果不加以制约的话，岂不是任意页面都可以通过资源定位符URL访问到，即是说后端是一定会去做相应的适配的，但是前端也需要杜绝这种现象。

### 路由守卫
思路很简单，就是在每次浏览器输入完URL后，也就是输入完了路由之后，加一道判断即可。
```js
// 注册一个全局前置守卫，检查用户是否登录，存储路由历史记录
router.beforeEach((to, from) => {
    console.log('to', to)
    if (to.path != '/login') {
        const routers = useRouter()
        const userStore = useUserStore()
        if (userStore.user === null) {
            routers.replace({path: '/login'});
            ElMessageBox.alert('请在登录后访问该界面', '提示', {
                // if you want to disable its autofocus
                // autofocus: false,
                confirmButtonText: 'OK',
            }).then()
        }
    }
    setRouterHistory(to)
})
```



## 路由生成侧边栏解决方案

## 路由历史记录

