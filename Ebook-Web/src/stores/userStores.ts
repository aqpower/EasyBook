import { defineStore } from 'pinia'
import { type Ref, ref } from 'vue'

export interface User {
  id: string
  name: string
  email: string
  token: string
  avatar: number
}

interface UserStore {
  user: Ref<User | null>
  setUser: (userInfo: User) => void
  clearUser: () => void
  updateInfo: (avatar: number, name: string) => void
}

export const useUserStore = defineStore(
  'user',
  (): UserStore => {
    const user: Ref<User | null> = ref<User | null>(null)

    function setUser(userInfo: User): void {
      user.value = userInfo
    }

    function clearUser(): void {
      user.value = null
    }

    function updateInfo(avatar: number, name: string): void {
      user.value.avatar  = avatar
      user.value.name = name
    }

    return {
      user,
      setUser,
      clearUser,
      updateInfo,
    }
  },
  {
    persist: true
  }
)
