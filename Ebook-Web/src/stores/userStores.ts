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
  user: Ref<User>
  setUser: (userInfo: User) => void
  clearUser: () => void
  updateInfo: (avatar: number, name: string) => void
}

export const useUserStore = defineStore(
  'user',
  (): UserStore => {
    const user: Ref<User> = ref<User>({
      id: '',
      name: '',
      email: '',
      token: '',
      avatar: 0
    })

    function setUser(userInfo: User): void {
      user.value = userInfo
    }

    function clearUser(): void {
      user.value = {
        id: '',
        name: '',
        email: '',
        token: '',
        avatar: 0
      }
    }

    function updateInfo(avatar: number, name: string): void {
      user.value.avatar = avatar
      user.value.name = name
    }

    return {
      user,
      setUser,
      clearUser,
      updateInfo
    }
  },
  {
    persist: true
  }
)
