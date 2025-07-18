<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-home" @click="goHome"></i>
      <p>预约成功</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <div class="success-icon">
        <i class="fa fa-check-circle"></i>
      </div>
      <div class="success-message">
        <h2>支付成功!</h2>
        <p>您的体检预约已成功完成</p>
      </div>
      
      <div class="order-info">
        <h3>预约信息</h3>
        <div class="info-item">
          <span class="label">预约时间:</span>
          <span class="value">{{ orderInfo.date || '请查看预约详情' }}</span>
        </div>
        <div class="info-item">
          <span class="label">预约状态:</span>
          <span class="value success">已支付</span>
        </div>
      </div>

      <div class="tips">
        <h3>温馨提示</h3>
        <ul>
          <li>请按时到达体检机构</li>
          <li>体检前请空腹8-12小时</li>
          <li>如需取消或修改预约，请及时联系客服</li>
          <li>体检完成后，报告将在3-5个工作日内生成</li>
        </ul>
      </div>
    </section>

    <div class="bottom-btn">
      <div class="btn-group">
        <button class="btn-secondary" @click="goHome">返回首页</button>
        <button class="btn-primary" @click="viewOrders">查看预约</button>
      </div>
    </div>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import { reactive, toRefs, onMounted } from "vue";
import { useRouter } from "vue-router";
import Footer from "@/components/Footer.vue";

export default {
  name: "AppointmentSuccessView",
  components: {
    Footer
  },
  setup() {
    const router = useRouter();
    
    const state = reactive({
      orderInfo: {
        date: null,
        status: 'paid'
      }
    });

    onMounted(() => {
      // 可以从路由参数或本地存储获取预约信息
      const selectedDay = sessionStorage.getItem('selectedDay');
      if (selectedDay) {
        state.orderInfo.date = selectedDay;
      }
    });

    function goHome() {
      router.push('/');
    }

    function viewOrders() {
      router.push('/personal');
    }

    return {
      ...toRefs(state),
      goHome,
      viewOrders
    };
  },
};
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100vh;
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
}

header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 0 3.6vw;
}

header .fa {
  font-size: 8vw;
  cursor: pointer;
}

header p {
  font-size: 4.8vw;
  font-weight: 600;
}

.top-ban {
  width: 100%;
  height: 15.7vw;
}

.bottom-ban {
  width: 100%;
  height: 20vw;
}

section {
  flex: 1;
  width: 86vw;
  margin: 0 auto;
  padding: 8vw 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.success-icon {
  margin-bottom: 6vw;
}

.success-icon .fa-check-circle {
  font-size: 20vw;
  color: #52c41a;
}

.success-message {
  text-align: center;
  margin-bottom: 8vw;
}

.success-message h2 {
  font-size: 6vw;
  color: #333;
  margin-bottom: 2vw;
}

.success-message p {
  font-size: 4vw;
  color: #666;
}

.order-info {
  width: 100%;
  background-color: #fff;
  border-radius: 2vw;
  padding: 6vw;
  margin-bottom: 6vw;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.order-info h3 {
  font-size: 4.5vw;
  color: #333;
  margin-bottom: 4vw;
  padding-bottom: 2vw;
  border-bottom: 1px solid #f0f0f0;
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 3vw 0;
  border-bottom: 1px solid #f5f5f5;
}

.info-item:last-child {
  border-bottom: none;
}

.info-item .label {
  font-size: 3.8vw;
  color: #666;
}

.info-item .value {
  font-size: 3.8vw;
  color: #333;
}

.info-item .value.success {
  color: #52c41a;
  font-weight: 600;
}

.tips {
  width: 100%;
  background-color: #fff;
  border-radius: 2vw;
  padding: 6vw;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.tips h3 {
  font-size: 4.5vw;
  color: #333;
  margin-bottom: 4vw;
  padding-bottom: 2vw;
  border-bottom: 1px solid #f0f0f0;
}

.tips ul {
  list-style: none;
  padding: 0;
}

.tips li {
  font-size: 3.6vw;
  color: #666;
  padding: 2vw 0;
  position: relative;
  padding-left: 4vw;
}

.tips li:before {
  content: "•";
  color: #127a90;
  position: absolute;
  left: 0;
}

.bottom-btn {
  width: 100%;
  background-color: #fff;
  position: fixed;
  left: 0;
  bottom: 14.2vw;
  padding: 4vw;
  box-sizing: border-box;
}

.btn-group {
  display: flex;
  gap: 4vw;
}

.btn-secondary,
.btn-primary {
  flex: 1;
  height: 12vw;
  border: none;
  border-radius: 6vw;
  font-size: 4.2vw;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-secondary {
  background-color: #f5f5f5;
  color: #666;
}

.btn-secondary:hover {
  background-color: #e8e8e8;
}

.btn-primary {
  background-color: #117c94;
  color: #fff;
}

.btn-primary:hover {
  background-color: #0f6b85;
}
</style>