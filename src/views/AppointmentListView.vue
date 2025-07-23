<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>我的预约</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul v-if="appointmentList.length > 0">
      <li v-for="appointment in appointmentList" :key="appointment.orderId">
        <div class="left" @click="viewDetail(appointment)">
          <p>{{ formatDate(appointment.orderDate) }}</p>
          <p>{{ appointment.setmeal?.name || '体检套餐' }}</p>
          <p class="price">￥{{ appointment.setmeal?.price || '0' }}</p>
        </div>
        <div class="right" v-if="appointment.state === 1 && canCancelAppointment(appointment)" @click="cancelAppointment(appointment)">
          取消预约
        </div>
        <div class="right disabled" v-else-if="appointment.state === 1 && !canCancelAppointment(appointment)">
          不可取消
        </div>
        <div class="right completed" v-else-if="appointment.state === 2">
          已完成
        </div>
        <div class="right cancelled" v-else-if="appointment.state === 3">
          已取消
        </div>
      </li>
    </ul>

    <div v-else class="empty-state">
      <i class="fa fa-calendar-o"></i>
      <p>暂无预约记录</p>
      <button @click="goToAppointment">立即预约</button>
    </div>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import { reactive, toRefs, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import Footer from "@/components/Footer.vue";
import { formatDateToString, createLocalDate } from "@/utils/dateUtils.js";

export default {
  name: "AppointmentListView",
  components: {
    Footer
  },
  setup() {
    const router = useRouter();
    
    const state = reactive({
      appointmentList: [],
      loading: false
    });

    onMounted(() => {
      loadAppointmentList();
    });

    function loadAppointmentList() {
      const userInfo = JSON.parse(sessionStorage.getItem("jinandaxueuser") || "{}");
      if (!userInfo.userId) {
        router.push('/');
        return;
      }

      state.loading = true;
      
      // 使用真实API调用
      axios.post('/api/getAppointmentList', {
        userId: userInfo.userId
      }).then(response => {
        if (response.data.status === 200 || response.data.code === 1) {
          state.appointmentList = response.data.data || [];
        } else {
          console.error('获取预约列表失败:', response.data.desc);
          state.appointmentList = [];
        }
      }).catch(error => {
        console.error('获取预约列表失败:', error);
        state.appointmentList = [];
      }).finally(() => {
        state.loading = false;
      });
    }

    function formatDate(date) {
      return formatDateToString(date);
    }

    function canCancelAppointment(appointment) {
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      
      // 使用工具函数创建本地日期，避免时区问题
      const appointmentDate = createLocalDate(appointment.orderDate);
      appointmentDate.setHours(0, 0, 0, 0);
      
      const tomorrow = new Date(today);
      tomorrow.setDate(tomorrow.getDate() + 1);
      
      // 预约日期必须是明天之后才能取消（即今天和明天都不能取消）
      return appointmentDate > tomorrow;
    }

    function goBack() {
      router.go(-1);
    }

    function viewDetail(appointment) {
      // 跳转到预约详情页面
      console.log('查看预约详情:', appointment);
    }

    function cancelAppointment(appointment) {
      if (confirm('确定要取消这个预约吗？')) {
        // 更新预约状态为已取消
        const savedAppointments = JSON.parse(sessionStorage.getItem('appointmentList') || '[]');
        const updatedAppointments = savedAppointments.map(item => {
          if (item.orderId === appointment.orderId) {
            return { ...item, state: 3 }; // 3: 已取消
          }
          return item;
        });
        
        // 保存更新后的列表
        sessionStorage.setItem('appointmentList', JSON.stringify(updatedAppointments));
        
        // 跳转到取消预约成功页面
        router.push('/appointment-cancel');
      }
    }

    function goToAppointment() {
      router.push('/appointment');
    }

    return {
      ...toRefs(state),
      goBack,
      viewDetail,
      cancelAppointment,
      goToAppointment,
      formatDate,
      canCancelAppointment
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

/*********************** ul ***********************/
ul {
  width: 86vw;
  margin: 0 auto;
  padding: 4vw 0;
}

ul li {
  width: 100%;
  min-height: 18vw;
  border-bottom: solid 1px #EEE;
  background-color: #FFF;
  margin-bottom: 2vw;
  border-radius: 2vw;
  padding: 4vw;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

ul li .left {
  flex: 1;
  user-select: none;
  cursor: pointer;
}

ul li .left p:first-child {
  color: #555;
  font-size: 3.3vw;
  margin-bottom: 1vw;
}

ul li .left p:nth-child(2) {
  color: #333;
  font-size: 4.2vw;
  font-weight: 600;
  margin-bottom: 1vw;
}

ul li .left p.price {
  color: #E6A23C;
  font-size: 4vw;
  font-weight: 600;
}

ul li .right {
  font-size: 3.6vw;
  color: #E6A23C;
  cursor: pointer;
  padding: 2vw 3vw;
  border: 1px solid #E6A23C;
  border-radius: 4vw;
  user-select: none;
}

ul li .right.completed {
  color: #67C23A;
  border-color: #67C23A;
}

ul li .right.cancelled {
  color: #F56C6C;
  border-color: #F56C6C;
}

ul li .right.disabled {
  color: #C0C4CC;
  border-color: #C0C4CC;
  cursor: not-allowed;
}

/*********************** 空状态 ***********************/
.empty-state {
  width: 86vw;
  margin: 0 auto;
  text-align: center;
  padding: 20vw 0;
}

.empty-state i {
  font-size: 20vw;
  color: #ccc;
  margin-bottom: 4vw;
}

.empty-state p {
  font-size: 4vw;
  color: #999;
  margin-bottom: 6vw;
}

.empty-state button {
  background-color: #02A6C9;
  color: #fff;
  border: none;
  padding: 3vw 8vw;
  border-radius: 6vw;
  font-size: 4vw;
  cursor: pointer;
}

.empty-state button:hover {
  background-color: #0195b6;
}
</style>