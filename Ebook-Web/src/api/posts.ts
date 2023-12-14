import type { ResType } from '@/types'
import type { PostApiType, UserPostResType, PostType } from '@/types/post'
import httpInstance from '@/utils/http'

export const getPostsApi = (data: { id: any; page: any; pageSize: any }): Promise<any> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize
    }
  })

export const getUserPostApi = (userId: any): Promise<ResType<UserPostResType>> =>
  httpInstance.get(`/api/v1/posts/users/${userId}`)

export const getPostApi = (postId: String): Promise<ResType<PostType>> =>
  httpInstance.get(`/api/v1/posts/${postId}`)

export const uploadImgApi = (data: FormData) =>
  httpInstance.post('/api/v1/upload', data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

export const newPostApi = (data: PostApiType) => httpInstance.post('/api/v1/post', data)

// export const newPostLikeApi = 