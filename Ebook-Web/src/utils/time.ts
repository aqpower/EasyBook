export function formatTime(timeString: string) {
  const date = new Date(timeString)
  // 这里你可以使用各种 JavaScript 的日期格式方法来处理日期显示方式
  // 例如：date.toLocaleString() 或 date.toISOString()，根据你想要的格式进行调整
  return date.toLocaleString() // 以本地时间格式返回日期时间字符串
}
