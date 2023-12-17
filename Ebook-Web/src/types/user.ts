export type UserRegisterType = {
  email: string
  name: string
  password: string
  avatar: string
}

export type UserUpdateType = {
  avatar: number
  id: number
  name: string
  [property: string]: any
}