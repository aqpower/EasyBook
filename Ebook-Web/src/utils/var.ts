export interface Color {
  name: string
  var: string
}

export const colorList: Color[] = [
  {
    name: '活力橙',
    var: '#FFA500'
  },
  {
    name: '奇迹紫',
    var: '#AC92ED'
  },
  {
    name: '跃动绿',
    var: '#7FFF00'
  },
  {
    name: '激情红',
    var: '#ED5466'
  },
  {
    name: '闪耀黄',
    var: '#FFFF00'
  },
  {
    name: '宁静青',
    var: '#008B8B'
  },
  {
    name: '安全蓝',
    var: '#87CEEB'
  },
  {
    name: '浩瀚黑',
    var: '#000000'
  },
  {
    name: '暖暖粉',
    var: '#EC87BF'
  }
]

// 定义内容违规类型
export type ContentViolationType =
  | '暴力'
  | '色情'
  | '辱骂'
  | '违法信息'
  | '诈骗'
  | '侵权'
  | '骚扰'
  | '恐怖主义'

// 创建内容违规类型数组
export const violationTypes: ContentViolationType[] = [
  '暴力',
  '色情',
  '辱骂',
  '违法信息',
  '诈骗',
  '侵权',
  '骚扰',
  '恐怖主义'
]
