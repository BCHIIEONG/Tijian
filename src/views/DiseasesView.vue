<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="$router.go(-1)"></i>
      <p>疾病详情</p>
      <div></div>
    </header>
    
    <div class="disease-card" v-if="currentDisease">
      <div class="disease-basic-info">
        <h1 class="disease-name">{{ currentDisease.disease_name }}</h1>
      </div>
      
      <div class="disease-details">
        <div class="detail-section">
          <h3 class="section-title">病因</h3>
          <p class="desc-text">{{currentDisease.etiology}}</p>
        </div>
        <div class="detail-section">
          <h3 class="section-title">症状</h3>
          <p class="desc-text">{{currentDisease.clinical_manifestations}}</p>
        </div>
        <div class="detail-section">
          <h3 class="section-title">并发症</h3>
          <p class="desc-text">{{currentDisease.complications}}</p>
        </div>
        <div class="detail-section">
          <h3 class="section-title">治疗</h3>
          <p class="desc-text">{{currentDisease.treatment}}</p>
        </div>
        <div class="detail-section">
          <h3 class="section-title">护理</h3>
          <p class="desc-text">{{currentDisease.nursing_care}}</p>
        </div>
      </div>
    </div>
    <div v-else>
      正在加载疾病信息...
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { reactive, toRefs, computed } from "vue";
import { useRouter, useRoute } from 'vue-router';

export default {
  setup() {
    const route = useRoute();

    const state = reactive({
      currentDisease: {
        disease_id: route.query.disease_id,
        disease_name: route.query.disease_name,
        categoryId: route.query.categoryId,
        etiology: route.query.etiology,
        clinical_manifestations:route.query.clinical_manifestations,
        complications:route.query.complications,
        treatment:route.query.treatment,
        nursing_care:route.query.nursing_care,
      }
    });


    return {
      ...toRefs(state),
      currentDisease: computed(() => state.currentDisease),
      
    };
  }
}
</script>

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

/*********************** 疾病卡片 ***********************/
.disease-card {
  width: 100%;
  padding: 20vw 4vw 6vw;
  background-color: #fff;
  margin-bottom: 3vw;
  box-sizing: border-box;
}

.disease-basic-info {
  padding: 4vw 0;
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 4vw;
}

.disease-name {
  font-size: 5vw;
  font-weight: 600;
  color: #333;
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
  font-size: 3.5vw;
  color: #333;
  font-weight: 500;
  width: 20vw;
}

.info-value {
  font-size: 3.5vw;
  color: #666;
  flex: 1;
}

/*********************** 详细信息 ***********************/
.disease-details {
  padding: 2vw 0;
}

.detail-section {
  margin-bottom: 6vw;
}

.section-title {
  font-size: 4vw;
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

.desc-text {
  font-size: 3.8vw;
  color: #555;
  line-height: 1.6;
  text-align: justify;
  margin: 0;
}
</style>