# RESTful API

本项目按照RESTful风格设计路由，做到了
- HTTP请求方式代表接口的操作
- URL表示请求的资源

```go
// 新建职工
authed.POST("users", api.NewUser)
// 查询用户详情
authed.GET("users/:userId", api.UserInfo)
//	分页获取职工信息
authed.GET("users", api.GetUsers)
// 	修改职工信息
authed.PUT("users/:userId", api.UpdateUser)
// 修改权限
/authed.PUT("change_puis", api.ChangePuis)
// --- 排班时间 ---
authed.GET("scheduleTime/:userId", api.GetScheduleTime)
authed.POST("setScheduleTime", api.SetScheduleTime)

// --- 排班规则 ----
// 获取排班规则
authed.GET("schedulerules/:storeId",api.ScheduleRulesInfo)
// 新建排班规则
authed.POST("schedule-rules", api.NewSchedule)
authed.PUT("schedulerules/:storeId",api.UpdateSchedueRule)

// --- 门店 ---
// 获取门店名称列表
authed.GET("stores/nameList", api.StoreNameList)

// TODO 分页获取所有门店信息
//authed.GET("stores")
// 获取门店详情
v1.GET("stores/:storeId", api.StoreInfo)

// 新建门店
v1.POST("stores", api.NewStore)

// 注册路由，使用路由参数 :storeId 和 :startTime
v1.GET("/schedule-tables/:storeId/:startTime", api.GetScheduleTable)
```