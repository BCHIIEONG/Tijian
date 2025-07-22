// Session存储工具函数
export function getSessionStorage(key) {
  try {
    const value = sessionStorage.getItem(key);
    return value ? JSON.parse(value) : null;
  } catch (error) {
    console.error('获取session存储失败:', error);
    return null;
  }
}

export function setSessionStorage(key, value) {
  try {
    sessionStorage.setItem(key, JSON.stringify(value));
    return true;
  } catch (error) {
    console.error('设置session存储失败:', error);
    return false;
  }
}

export function removeSessionStorage(key) {
  try {
    sessionStorage.removeItem(key);
    return true;
  } catch (error) {
    console.error('删除session存储失败:', error);
    return false;
  }
}

// 域名检测和URL生成工具函数
export function getCurrentDomain() {
  if (typeof window !== 'undefined') {
    return window.location.origin;
  }
  // 服务端环境下返回null，让后端处理
  return null;
}

export function generatePaymentUrl(params) {
  const { subject, traceNo, totalAmount } = params;
  const currentDomain = getCurrentDomain();
  
  // 将当前域名作为参数传递给后端
  let paymentUrl = `/api/alipay/pay?subject=${encodeURIComponent(subject)}&traceNo=${traceNo}&totalAmount=${totalAmount}`;
  
  // 只在浏览器环境下添加域名参数
  if (currentDomain) {
    paymentUrl += `&returnDomain=${encodeURIComponent(currentDomain)}`;
  }
  
  return paymentUrl;
}