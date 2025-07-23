// 日期工具函数，解决时区显示问题

/**
 * 将任意日期格式转换为 YYYY-MM-DD 格式
 * 避免时区转换问题
 */
export function formatDateToString(date) {
  if (!date) return '';
  
  // 如果是字符串格式的日期
  if (typeof date === 'string') {
    // 如果是 YYYY-MM-DD 格式，直接返回
    if (date.match(/^\d{4}-\d{2}-\d{2}$/)) {
      return date;
    }
    // 如果是 ISO 格式（包含T），提取日期部分
    if (date.includes('T')) {
      return date.split('T')[0];
    }
    // 如果是其他格式，尝试提取年月日
    const dateMatch = date.match(/(\d{4})-(\d{1,2})-(\d{1,2})/);
    if (dateMatch) {
      const [, year, month, day] = dateMatch;
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
    }
  }
  
  // 如果是 Date 对象，使用本地时区方法（不使用UTC）
  const d = new Date(date);
  const year = d.getFullYear();
  const month = d.getMonth() + 1;
  const day = d.getDate();
  return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
}

/**
 * 将日期转换为中文格式：YYYY年MM月DD日
 */
export function formatDateToChinese(date) {
  const dateStr = formatDateToString(date);
  if (!dateStr) return '';
  
  const arr = dateStr.split('-');
  return `${arr[0]}年${arr[1]}月${arr[2]}日`;
}

/**
 * 创建一个本地日期对象，避免时区转换
 * 输入: "2025-07-23" 或 Date 对象
 * 输出: 本地时区的 Date 对象，日期部分不变
 */
export function createLocalDate(date) {
  if (!date) return null;
  
  if (typeof date === 'string') {
    const dateStr = formatDateToString(date);
    const [year, month, day] = dateStr.split('-').map(Number);
    // 创建本地时区的日期，不进行UTC转换
    return new Date(year, month - 1, day);
  }
  
  // 如果已经是 Date 对象，返回一个新的本地日期
  const d = new Date(date);
  return new Date(d.getFullYear(), d.getMonth(), d.getDate());
}