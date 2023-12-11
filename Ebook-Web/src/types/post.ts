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
  postShows: PostType[]
}
