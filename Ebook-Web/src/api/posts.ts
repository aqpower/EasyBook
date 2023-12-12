import type { type PostApiType } from '@/types/post'
import httpInstance from '@/utils/http'

export const getPostsApi = (data: { id: any; page: any; pageSize: any }): Promise<any> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize
    }
  })

export const uploadImgApi = (data) =>
  httpInstance.post('/api/v1/upload', data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

export const newPostApi = (data: PostApiType) => httpInstance.post('/api/v1/post', data)
