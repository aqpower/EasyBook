import type { ResType } from '@/types'
import type { PostApiType,  GetPostResType, PostDetailResType } from '@/types/post'
import httpInstance from '@/utils/http'

export const getPostsApi = (data: {
  id: any
  page: any
  pageSize: any
  color?: number
  text?: string
}): Promise<any> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize,
      color: data.color,
      text: data.text
    }
  })

export const getColorPostsApi = (data: {
  id: any
  page: any
  pageSize: any
  color: number
  text?: string
}): Promise<any> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize,
      color: data.color,
      text: data.text
    }
  })

export const getUserPostApi = (
  id: any,
  page: number,
  pageSize: number
): Promise<ResType<GetPostResType>> =>
  httpInstance.get('/api/v1/posts/users', {
    params: {
      id: id,
      page: page,
      pageSize: pageSize
    }
  })

export const getPostApi = (postId: String): Promise<ResType<PostDetailResType>> =>
  httpInstance.get(`/api/v1/posts/${postId}`)

export const uploadImgApi = (data: FormData) =>
  httpInstance.post('/api/v1/upload', data, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

export const newPostApi = (data: PostApiType): Promise<ResType<any>> =>
  httpInstance.post('/api/v1/post', data)

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

export const searchPostsApi = (data: {
  id: any
  page: any
  pageSize: any
  text: string
  color: number
}): Promise<ResType<any>> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize,
      text: data.text,
      color: data.color
    }
  })

export const getLikePostsApi = (
  id: any,
  page: any,
  pageSize: any
): Promise<ResType<GetPostResType>> =>
  httpInstance.get('/api/v1/posts/users/like', {
    params: {
      id: id,
      page: page,
      pageSize: pageSize
    }
  })

export const getStarPostsApi = (
  id: any,
  page: any,
  pageSize: any
): Promise<ResType<GetPostResType>> =>
  httpInstance.get('/api/v1/posts/users/collection', {
    params: {
      id: id,
      page: page,
      pageSize: pageSize
    }
  })

export type Comment = {
  id: number
  userId: number
  postId: number
  content: string
  commentTime: string
}

export type CommentData = {
  code: number
  msg: string
  data: {
    pageCount: number
    comments: Comment[]
  }
}

export const getUserCommentsApi = (id: any, page: any, pageSize: any): Promise<CommentData> =>
  httpInstance.get('/api/v1/user/comments', {
    params: {
      id: id,
      page: page,
      pageSize: pageSize
    }
  })
