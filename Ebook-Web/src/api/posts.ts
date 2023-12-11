import httpInstance from '@/utils/http'

export const getPostsApi = (data: { id: any; page: any; pageSize: any }): Promise<any> =>
  httpInstance.get('/api/v1/posts', {
    params: {
      id: data.id,
      page: data.page,
      pageSize: data.pageSize
    }
  })
