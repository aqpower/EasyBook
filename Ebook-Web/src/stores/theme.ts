import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useThemeStore = defineStore(
  'theme',
  () => {
    const theme = ref('default')

    function setTheme(newTheme: string) {
      theme.value = newTheme
    }

    function getTheme(): string {
      return theme.value
    }

    return { theme, setTheme, getTheme }
  },
  {
    persist: true
  }
)
