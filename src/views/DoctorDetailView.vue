<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>医生详细介绍</p>
      <div></div>
    </header>
    
    <div class="doctor-card" v-if="currentDoctor">
      <div class="doctor-basic-info">
        <img :src="currentDoctor.doctorPicture" />
        <div class="doctor-meta">
          <h1 class="doctor-name">{{ currentDoctor.doctorName }}</h1>
          <p class="doctor-title">{{ currentDoctor.doctorLevel }}</p>
          <div class="divider"></div>
          <div class="info-row">
            <span class="info-label">医院：</span>
            <span class="info-value">{{ currentDoctor.doctorHospital }}</span>
          </div>
          <div class="divider"></div>
          <div class="info-row">
            <span class="info-label">科室：</span>
            <span class="info-value">{{ currentDoctor.doctorDepartment }}</span>
          </div>
        </div>
      </div>
      
      <div class="doctor-details">
        <div class="detail-section">
          <h3 class="section-title">擅长</h3>
          <p class="bio-text">{{ currentDoctor.doctorExcel }}</p>
        </div>
        
        <div class="detail-section">
          <h3 class="section-title">个人简介</h3>
          <p class="bio-text">{{ currentDoctor.doctorSynopsis }}</p>
        </div>
      </div>
    </div>
    <div v-else>
      正在加载医生信息...
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import axios from 'axios';
import { reactive, toRefs, computed} from "vue"; // 添加 onMounted
import { useRouter, useRoute } from 'vue-router';

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    const state = reactive({
      doctors: [], // 所有医生列表
      doctorId: route.query.doctorId // 从路由参数获取当前医生ID
    });

    // 计算属性：获取当前显示的医生
    const currentDoctor = computed(() => {
      return state.doctors.find(doctor => doctor.doctorId == state.doctorId);
    });

    
    // 初始化医生数据
    function initDoctorDetailArr() {
      axios
        .post("/api/listDoctordetail")
        .then((response) => {
          state.doctors = response.data.data;
          console.log("医生数据加载成功:", state.doctors);
        })
        .catch(function (error) {
          console.error("加载医生数据失败:", error);
        });
    }

    // 添加 mounted 生命周期钩子
    initDoctorDetailArr();

    return {
      ...toRefs(state),
      currentDoctor,
      initDoctorDetailArr,
    };
  }
}
</script>

<!-- 样式部分保持不变 -->

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  min-height: 100vh;
  background-color: #f9f9f9;
  padding-bottom: 10vw;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 15.7vw;
  background-color: #fff;
  position: fixed;
  left: 0;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 0 3.6vw;
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
  z-index: 10;
}

header .fa {
  font-size: 8vw;
  color: #333;
}

header p {
  font-size: 4.5vw;
  font-weight: 600;
  color: #333;
}

/*********************** 医生卡片 ***********************/
.doctor-card {
  width: 100%;
  padding: 20vw 4vw 6vw;
  background-color: #fff;
  margin-bottom: 3vw;
  box-sizing: border-box;
}

.doctor-basic-info {
  display: flex;
  align-items: flex-start;
  padding: 4vw 0;
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 4vw;
}

.doctor-image {
  width: 25vw;
  height: 25vw;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #f0f0f0;
}

.doctor-meta {
  margin-left: 4vw;
  flex: 1;
}

.doctor-name {
  font-size: 3.5vw;
  font-weight: 600;
  color: #333;
  margin: 0 0 1vw 0;
}

.doctor-title {
  font-size: 3vw;
  color: #666;
  margin: 0 0 3vw 0;
}

.divider {
  height: 1px;
  background: #f0f0f0;
  margin: 3vw 0;
}

.info-row {
  display: flex;
  margin-bottom: 2vw;
}

.info-label {
  font-size: 3vw;
  color: #333;
  font-weight: 500;
  width: 18vw;
}

.info-value {
  font-size: 2.8vw;
  color: #666;
  flex: 1;
}

/*********************** 详细信息 ***********************/
.doctor-details {
  padding: 2vw 0;
}

.detail-section {
  margin-bottom: 6vw;
}

.section-title {
  font-size: 3vw;
  color: #333;
  font-weight: 600;
  margin: 0 0 3vw 0;
  position: relative;
  padding-left: 3vw;
}

.section-title::before {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 1vw;
  height: 4vw;
  background: #1e88e5;
  border-radius: 0.5vw;
}

.expertise-list {
  padding-left: 5vw;
  margin: 0;
}

.expertise-list li {
  font-size: 3.8vw;
  color: #555;
  line-height: 1.6;
  margin-bottom: 2vw;
  text-align: justify;
}

.bio-text {
  font-size: 2vw;
  color: #555;
  line-height: 1.6;
  text-align: justify;
  margin: 0;
}
</style>