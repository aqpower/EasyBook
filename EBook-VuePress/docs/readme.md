## 整体背景
随着云计算、大数据以及人工智能等新兴技术的发展，劳动力管理从最初的手工经
验化管理，到电子流程化管理，正逐渐升级过渡到精细化智能化的管理。
对于服务和零售等行业，快速应对市场变化、及时准确的满足客户需求、提供高品
质的服务，才能在日趋激烈的竞争中占据优势，为此通过科学的手段优化劳动力资源、
控制劳动力成本、提高员工体验和客户满意度将越来越重要。
对劳动力管理者而言，需要关注如何将掌握特定技能的员工安排到与技能最匹
配的任务上，如何更有效的评估劳动力需求、最大化员工工作效率、最大化精简用
工成本。
解决问题的关键是将劳动力与业务需求最优化匹配，将合适数量的员工在合适的时
间放在合适的位置上。

## 业务背景
以零售行业为例，某公司在某区域有 3 家销售门店，主要销售其品牌运动鞋，
公司目前有业务预测系统可以对各家门店销售情况进行预测，需要基于预测数据以及业
务波动，细化每天不同时间段不同技能的门店劳动力需求量，保证业务高峰时人员充
足，同时避免业务低谷时的人力浪费。

## 目录树

```
.
├── env.d.ts 
├── index.html
├── package.json
├── package-lock.json
├── pnpm-lock.yaml
├── public
│   └── favicon.ico
├── README.md
├── src
│   ├── api
│   │   └── user.ts
│   ├── App.vue
│   ├── assets
│   │   ├── app.css
│   │   ├── background-img.png
│   │   ├── logo.png
│   │   └── logo.svg
│   ├── components
│   │   ├── Chart
│   │   │   ├── BaseBarChart.vue
│   │   │   └── BaseLineChart.vue
│   │   ├── Login
│   │   │   └── LoginCard.vue
│   │   ├── MyAbout.vue
│   │   ├── SchedMgmt
│   │   │   ├── ScheduleCalendar.vue
│   │   │   ├── ScheduleRuleView.vue
│   │   │   ├── ScheduleRule.vue
│   │   │   └── UpdateScheduleRule.vue
│   │   ├── StaffInfoUpdate.vue
│   │   ├── StaffInsert.vue
│   │   ├── StoreInfoUpdate.vue
│   │   ├── StoreMgmt
│   │   │   └── StoreInformation.vue
│   │   ├── StuffSchedule.vue
│   │   └── UnderlyingSetting
│   │       ├── OvertimeAndLeave.vue
│   │       ├── ShiftManagement.vue
│   │       ├── SkillManagement.vue
│   │       └── SpecialDate.vue
│   ├── layouts
│   │   ├── BaseIcon.vue
│   │   ├── FooterVue.vue
│   │   ├── FullScreenIcon.vue
│   │   ├── RouterTag.vue
│   │   ├── SidebarItem.vue
│   │   ├── SidebarVue.vue
│   │   ├── ThemeSwitch.vue
│   │   └── TopbarVue.vue
│   ├── main.js
│   ├── mock
│   │   ├── mock.js
│   │   └── response
│   │       ├── scheTable.ts
│   │       ├── store.js
│   │       └── user.ts
│   ├── pages
│   │   ├── DashBoard.vue
│   │   ├── DemoSche.vue
│   │   ├── StaffInfo.vue
│   │   └── StaffList.vue
│   ├── router
│   │   └── index.ts
│   ├── stores
│   │   ├── collapse.js
│   │   ├── dark.js
│   │   ├── index.js
│   │   ├── routerHistory.ts
│   │   └── userStore.ts
│   ├── styles
│   │   ├── common.css
│   │   └── dark.css
│   ├── type
│   │   └── type.ts
│   ├── utils
│   │   ├── event-utils.js
│   │   ├── http.ts
│   │   └── user-utils.ts
│   └── views
│       ├── HomeView.vue
│       └── LoginView.vue
├── tree.md
├── tsconfig.json
├── vite.config.js
├── yarn-error.log
└── yarn.lock
```

## 安装pinia

```
npm install pinia
```

## 安装pinia持久化插件

```
pnpm i pinia-plugin-persistedstate
```

## 安装VueUse插件

```
npm i @vueuse/core
```

```
npm install @formkit/auto-animate
```

```shell
npm install echarts
```
<!-- **Output**

::: info
This is an info box.
:::

::: tip
This is a tip.
:::

::: warning
This is a warning.
:::

::: danger
This is a dangerous warning.
:::

::: details
This is a details block.
::: -->

