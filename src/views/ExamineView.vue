<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>体检项目</p>
      <div></div>
    </header>
    
    <!-- 体检项目详情内容 -->
    <div class="content">
      <div v-if="examineList.length > 0">
        <div class="examine-card" v-for="examine in examineList" :key="examine.examineId">
          <div class="card-content" @click="toExamineDetail(examine)">
            <h3>{{ examine.examineName }}</h3>
          </div>
        </div>
      </div>
      <div v-else class="no-data">
        <p>暂无检查项目数据</p>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import { reactive, toRefs, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from 'axios';

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    
    const state = reactive({
      projectId: route.query.projectId,
      examineList: []
    });

    function fetchExamineList() {
      if (!state.projectId) return;
      
      axios.get(`/api/listExamineByProject?projectId=${state.projectId}`)
        .then(response => {
          state.examineList = response.data.data;
        })
        .catch(error => {
          console.log(error);
        });
    }

    // 跳转到详情页
    function toExamineDetail(examine) {
      router.push({
        name: 'examineDetailView',
        query: {
          examineId: examine.examineId,
          examineName: examine.examineName,
          examineDepartment: examine.examineDepartment,
          abbreviation: examine.abbreviation,
          Common_names: examine.common_names,
          examineObjective: examine.examineObjective
        }
      });
    }

    onMounted(() => {
      fetchExamineList();
    });

    return {
      ...toRefs(state),
      toExamineDetail
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

.examine-card {
  background-color: #fff;
  border-radius: 12px;
  margin-bottom: 3vw;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.examine-card:active {
  transform: scale(0.98);
}

.card-content {
  padding: 5vw;
}

.examine-card h3 {
  font-size: 4.5vw;
  color: #333;
  margin: 0;
  font-weight: 500;
}

.no-data {
  text-align: center;
  padding: 8vw;
  color: #999;
  font-size: 4vw;
}
</style>