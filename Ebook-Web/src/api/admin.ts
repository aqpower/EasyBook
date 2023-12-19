import type { ResType } from '@/types'
import httpInstance from '../utils/http'

export type adminLoginRequest = {
  id: string
  password: string
  [property: string]: any
}

export const adminLoginApi = (data: adminLoginRequest): Promise<ResType<any>> =>
  httpInstance.post('/api/v1/login/admins', data)
