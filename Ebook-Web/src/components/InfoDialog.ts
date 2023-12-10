import { createApp, ref } from 'vue'
import Dialog from '@/components/InfoDialog.vue'

const createDialog = (title: any, context: any, option = {}) => {
  const mountNode = document.createElement('div')
  const Instance = createApp(Dialog, {
    visible: true,
    title,
    context,
    close: () => {
        Instance.unmount()
        document.body.removeChild(mountNode)
    }
  })

  document.body.appendChild(mountNode)
  Instance.mount(mountNode)
}

export default createDialog
