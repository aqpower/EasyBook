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

export const getColorPostsApi = (data: {
  id: any
  page: any
  pageSize: any
  color: number
}): Promise<any> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize,
      color: data.color
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

export const newPostLikeApi = (data: any): Promise<ResType<any>> =>
  httpInstance.post('/api/v1/posts/like', data)

export const deletePostApi = (postId: number) => httpInstance.delete(`/api/v1/posts/${postId}`)

export const newCommentApi = (data: {
  userId: string | undefined
  postId: string | string[]
  content: string
}): Promise<ResType<any>> => httpInstance.post('/api/v1/posts/comment', data)

export const getFollowerPostApi = (
  id: string,
  page: number,
  pageSize: number
): Promise<ResType<any>> =>
  httpInstance.get('/api/v1/posts/care', {
    params: {
      id: id,
      page: page,
      pageSize: pageSize
    }
  })
