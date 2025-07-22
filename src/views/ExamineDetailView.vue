<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>{{ examineDetail.examineName }}</p>
      <div></div>
    </header>
    
    <!-- 体检项目详情内容 -->
    <div class="content">
      <div class="examine-detail">
        <div class="detail-item">
          <label>科室：</label>
          <span>{{ examineDetail.examineDepartment }}</span>
        </div>
        <div class="detail-item">
          <label>简称：</label>
          <span>{{ examineDetail.abbreviation }}</span>
        </div>
        <div class="detail-item">
          <label>常见名称：</label>
          <span>{{ examineDetail.Common_names }}</span>
        </div>
        <div class="objective">
          <h4>检查目的</h4>
          <p>{{ examineDetail.examineObjective }}</p>
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import { reactive, toRefs } from "vue";
import { useRoute } from "vue-router";

export default {
  setup() {
    const route = useRoute();
    const state = reactive({
      examineDetail: {
        examineId: route.query.examineId,
        examineName: route.query.examineName,
        examineDepartment: route.query.examineDepartment,
        abbreviation: route.query.abbreviation,
        Common_names: route.query.Common_names,
        examineObjective: route.query.examineObjective
      }
    });

    function goBack() {
      window.history.back();
    }

    return {
      ...toRefs(state),
      goBack
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
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
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

/*********************** 内容样式 ***********************/
.content {
  margin-top: 15.7vw;
  padding: 4vw;
}

.examine-detail {
  background-color: #fff;
  border-radius: 12px;
  padding: 5vw;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.detail-item {
  display: flex;
  margin-bottom: 4vw;
  font-size: 4vw;
}

.detail-item label {
  color: #666;
  min-width: 20vw;
}

.detail-item span {
  color: #333;
  font-weight: 500;
}

.objective {
  margin-top: 6vw;
  padding-top: 4vw;
  border-top: 1px solid #eee;
}

.objective h4 {
  font-size: 4.5vw;
  color: #333;
  margin-bottom: 3vw;
}

.objective p {
  font-size: 3.8vw;
  color: #666;
  line-height: 1.6;
}
</style>