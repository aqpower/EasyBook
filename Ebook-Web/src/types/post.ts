export type PostType = {
  id: number
  avatar: number
  name: string
  title: string
  contentText: string
  color: number
  lyrics: string
  url: string[]
  likeNum: number
  collectionNum: number
  ip: string
}

export type GetPostResType = {
  count: number
  posts: PostType[]
}

export type PostApiType = {
  type: number
  title: string
  contentText: string
  color: number
  lyrics: null | string
  ip: string
  userId: number
  urls: string[]
}

export type PostDetailResType = {
posts: PostDetailResType | undefined
commentsList: {}
  id: number
  avatar: number
  name: string
  title: string
  contentText: string
  color: number
  lyrics: string | null
  url: string[]
  likeNum: number
  collectionNum: number
  ip: string
}

export type CommentsListType = {
  // 定义评论列表的类型，根据实际数据结构来定义
}

export type UserPostResType = {
  id: number
  avatar: number
  name: string
  title: string
  contentText: string
  color: number
  lyrics: string | null
  url: string[] | null
  likeNum: number
  collectionNum: number
  ip: string
}