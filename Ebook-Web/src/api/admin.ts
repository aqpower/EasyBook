import type { ResType } from '@/types'
import httpInstance from '../utils/http'

export type adminLoginRequest = {
  id: string
  password: string
  [property: string]: any
}

export const adminLoginApi = (data: adminLoginRequest): Promise<ResType<any>> =>
  httpInstance.post('/api/v1/login/admins', data)

export type Violation = {
  id: number
  userId: number
  postId: number
  violationTime: string
  violationReason: string
}

export type getViosRes = {
  count: number
  violation: Violation[]
}

export const getViolationsApi = (page: number, pageSize: number): Promise<ResType<getViosRes>> =>
  httpInstance.get('/api/v1/login/reports', {
    params: {
      page: page,
      pageSize: pageSize
    }
  })

export const getPostCountApi = (): Promise<ResType<number>> => httpInstance.get('/api/v1/posts/all')

export type WelcomeAvatar = {
  avatar: number
  count: number
}

export const getPopularAvatarApi = (): Promise<ResType<WelcomeAvatar[]>> =>
  httpInstance.get('/api/v1/user/avatar')

export const getAllPostCountApi = () => httpInstance.get('/api/v1/visit')

export const getUserCountApi = () => httpInstance.get('/api/v1/count')

export const getTodayPostCountApi = () => httpInstance.get('/api/v1/posts/today/count')

export type handleVioRequest = {
  adminId: string
  type: number
  violationId: string
}

export const handleVioApi = (data: handleVioRequest): Promise<ResType<any>> =>
  httpInstance.post('/api/v1/admin/operations', data)
