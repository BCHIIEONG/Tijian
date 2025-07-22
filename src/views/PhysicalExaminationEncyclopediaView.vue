<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
      <p>体检百科</p>
      <div></div>
    </header>

    <!-- 体检百科内容 -->
    <div class="content">
      <div class="grid-container">
        <div class="grid-item" v-for="project in projectList" :key="project.projectId"
          @click="toProjectDetail(project)">
          {{ project.projectName }}
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import { reactive, toRefs } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      projectList: []
    });

    function initProjectList() {
      axios
        .post("/api/listPhysicalExamination")
        .then((response) => {
          state.projectList = response.data.data;
        })
        .catch(function (error) {
          console.log(error)
        });
    }

    function toProjectDetail(project) {
      router.push({
        name: 'examineView',
        query: {
          projectId: project.projectId
        }
      });
    }

    initProjectList();

    return {
      ...toRefs(state),
      toProjectDetail,
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

.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 3vw;
}

.grid-item {
  background-color: #fff;
  border-radius: 2vw;
  padding: 4vw 2vw;
  text-align: center;
  font-size: 3.8vw;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}
</style>