export interface Color {
  name:
    | '活力橙'
    | '奇迹紫'
    | '生机绿'
    | '激情红'
    | '闪耀黄'
    | '宁静青'
    | '天空蓝'
    | '奇异黑'
    | '纯净白'
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
    name: '生机绿',
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
    name: '天空蓝',
    var: '#87CEEB'
  },
  {
    name: '奇异黑',
    var: '#000000'
  },
  {
    name: '纯净白',
    var: '#F6F7FB'
  }
]
