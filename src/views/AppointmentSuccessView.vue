<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>预约成功</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <div class="success">
        <div class="icon-box">
          <div class="icon">
            <i class="fa fa-check"></i>
          </div>
        </div>
        <h1>恭喜预约成功！</h1>
        <p v-if="orderInfo.setmealName">{{ orderInfo.setmealName }}</p>
        <p v-else>请体检用户携带本人身份证到店认证</p>
      </div>
      
      <div class="order-btn" @click="viewOrders">查看订单</div>
      <div class="continue" @click="continueBooking">继续为家人预约</div>
      
      <div class="info">
        <p v-if="orderInfo.date">预约时间：{{ orderInfo.date }}</p>
        <p v-if="orderInfo.hospitalName">体检机构：{{ orderInfo.hospitalName }}</p>
        <p v-if="orderInfo.price">支付金额：￥{{ orderInfo.price }}</p>
        <p>您的信息已经发送至体检机构</p>
        <p>预约成功后会发送短信至您的手机</p>
        <p>客服电话<span>4008-XXX-XXX</span></p>
      </div>
    </section>

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
        orderId: null,
        setmealName: null,
        price: null,
        date: null,
        hospitalName: null,
        status: 'paid'
      }
    });

    onMounted(() => {
      // 从sessionStorage获取预约信息
      const appointmentInfo = sessionStorage.getItem('appointmentInfo');
      if (appointmentInfo) {
        const info = JSON.parse(appointmentInfo);
        state.orderInfo = {
          ...state.orderInfo,
          ...info
        };
        
        // 保存预约记录到预约列表
        saveAppointmentToList(state.orderInfo);
      }
    });

    function saveAppointmentToList(orderInfo) {
      // 获取当前预约列表
      const existingAppointments = JSON.parse(sessionStorage.getItem('appointmentList') || '[]');
      
      // 创建新的预约记录
      const newAppointment = {
        orderId: Date.now(), // 简单的ID生成
        orderDate: new Date().toISOString(),
        setmealName: orderInfo.setmealName || '基础体检套餐',
        price: orderInfo.price || 299,
        state: 1, // 1: 待体检, 2: 已完成, 3: 已取消
        hospitalName: orderInfo.hospitalName || '第一人民医院',
        userId: JSON.parse(sessionStorage.getItem("jinandaxueuser") || "{}").userId
      };
      
      // 添加新预约到列表
      existingAppointments.push(newAppointment);
      
      // 保存到sessionStorage
      sessionStorage.setItem('appointmentList', JSON.stringify(existingAppointments));
    }

    function goBack() {
      router.go(-1);
    }

    function goHome() {
      router.push('/index');
    }

    function viewOrders() {
      router.push('/appointmentlist');
    }

    function continueBooking() {
      router.push('/appointment');
    }

    return {
      ...toRefs(state),
      goBack,
      goHome,
      viewOrders,
      continueBooking
    };
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #F9F9F9;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #FFF;
  position: fixed;
  left: 0;
  top: 0;
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

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** section ***********************/
section {
  width: 100%;
}

section .success {
  width: 100%;
  height: 62vw;
  border-bottom: solid 1px #EEE;
  background-color: #FFF;
}

section .success .icon-box {
  width: 100%;
  height: 30vw;
  background-image: linear-gradient(135deg, #01C7A4, #02A6C9, #02A6C9);
  position: relative;
}

section .success .icon-box .icon {
  width: 16vw;
  height: 16vw;
  border-radius: 8vw;
  background-color: #FFF;
  position: absolute;
  left: 42vw;
  bottom: -8vw;
  font-size: 8vw;
  color: #02A6C9;
  text-align: center;
  line-height: 16vw;
}

section .success h1 {
  text-align: center;
  font-size: 5.2vw;
  font-weight: 500;
  color: #02A6C9;
  margin-top: 10vw;
}

section .success p {
  text-align: center;
  font-size: 3.4vw;
  color: #555;
  margin-top: 3vw;
}

section .order-btn {
  width: 100%;
  height: 14vw;
  text-align: center;
  line-height: 14vw;
  font-size: 4vw;
  color: #02A6C9;
  background-color: #FFF;
  user-select: none;
  cursor: pointer;
}

section .continue {
  width: 88vw;
  height: 13vw;
  margin: 0 auto;
  background-image: linear-gradient(135deg, #01C7A4, #02A6C9, #02A6C9);
  border-radius: 1vw;
  text-align: center;
  line-height: 13vw;
  margin-top: 5vw;
  font-size: 4vw;
  color: #FFF;
  user-select: none;
  cursor: pointer;
}

section .info {
  width: 100%;
  margin-top: 16vw;
}

section .info p {
  text-align: center;
  font-size: 3.2vw;
  color: #999;
  margin-top: 1vw;
}

section .info p span {
  font-size: 3.6vw;
  color: #555;
  margin-left: 1vw;
}
</style>