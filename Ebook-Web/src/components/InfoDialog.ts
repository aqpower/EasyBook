import { createApp } from 'vue'
import Dialog from '@/components/InfoDialog.vue'

const createDialog = (title: any, context: any, option = {}) => {
  const mountNode = document.createElement('div')
  let isOpen = true // 默认为打开状态
  const Instance = createApp(Dialog, {
    isOpen: isOpen,
    title,
    context,
    close: () => {
      isOpen = false
      setTimeout(() => {
        Instance.unmount()
        document.body.removeChild(mountNode)
      }, 2000) // 增加延迟时间以保证动画完成
    }
  })

  document.body.appendChild(mountNode)
  Instance.mount(mountNode)
}

export default createDialog
