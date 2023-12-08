import { defineStore } from "pinia";
import { type Ref, ref } from "vue";

export interface User {
    id: string;
    name: string;
    email: string;
    token: string;
}

interface UserStore {
    user: Ref<User | null>;
    setUser: (userInfo: User) => void;
    clearUser: () => void;
}

export const useUserStore = defineStore('user', () : UserStore => {
    const user: Ref<User | null> = ref<User | null>(null);

    function setUser(userInfo: User): void {
        user.value = userInfo;
    }

    function clearUser(): void {
        user.value = null;
    }

    return {
        user,
        setUser,
        clearUser,
    };
}, {
    persist: true
});
