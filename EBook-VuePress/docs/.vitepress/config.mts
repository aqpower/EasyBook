import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: 'EasyBook',
  description: 'Colorful Vibes, Endless Shares.',
  head: [['link', { rel: 'icon', href: '/logo-icon-nobg.png' }]],
  cleanUrls: true,
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    logo: '/logo-icon-nobg.png',
    nav: [
      { text: 'Home', link: '/' },
      { text: 'Details', link: '/readme' },
    ],

    sidebar: [
      {
        text: '目录',
        items: [
          { text: '项目简介', link: '/readme' },
          { text: 'JWT 安全传输信息', link: '/token-sol' },
          { text: '前端路由', link: '/router' },
          { text: 'RESTful API', link: '/restful' },
          { text: '保存路由访问历史', link: 'router-history' },
        ],
      },
    ],

    socialLinks: [
      {
        icon: 'github',
        link: 'https://github.com/aqpower/Web-ScheduleSystem/tree/dev',
      },
    ],
  },
})
