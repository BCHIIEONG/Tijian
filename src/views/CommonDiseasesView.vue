<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="$router.go(-1)"></i>
      <p>常见疾病</p>
      <div></div>
    </header>

    <!-- 疾病分类列表 -->
    <div class="category-container">
      <div class="category-item" v-for="(category,index) in categoryArr" :key="category.category_id">
        <div class="category-header" @click="toggleDiseaseList(index, category.category_id)">
          <span class="category-name">{{category.category_name}}</span>
          <i class="fa" :class="category.isShow ? 'fa-angle-up' : 'fa-angle-down'"></i>
        </div>
        <transition name="slide">
          <div class="disease-list" v-if="category.isShow">
            <div 
              class="disease-item" v-for="disease in category.diseases" :key="disease.disease_id" @click="goToDiseaseDetail(disease)">
              {{disease.disease_name}}
            </div>
            <div v-if="category.diseases && category.diseases.length === 0" class="no-data">
              暂无疾病数据
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import axios from 'axios';
import { reactive, toRefs } from "vue";
import { useRouter } from 'vue-router';

export default {
  setup(){
    const router = useRouter();
    const state = reactive({
      categoryArr: [], 
    });

    function initCategoryArr() {
      axios.post("/api/listDiseaseCategory")
        .then((response) => {
          state.categoryArr = response.data.data.map(category => ({
            ...category,
            isShow: false,
            diseases: []
          }));
        })
        .catch(error => {
          console.log(error);
        });
    }

    function toggleDiseaseList(index, categoryId) {
      const category = state.categoryArr[index];
      category.isShow = !category.isShow;
      
      if (category.isShow && (!category.diseases || category.diseases.length === 0)) {
        loadDiseases(index, categoryId);
      }
    }

    function loadDiseases(index, categoryId) {
      // 发送GET请求获取指定分类下的疾病
      axios.get(`/api/listDiseasesByCategory?categoryId=${categoryId}`)
        .then(response => {
          state.categoryArr[index].diseases = response.data.data;
        })
        .catch(error => {
          console.log(error);
        });
    }

    function goToDiseaseDetail(disease) {
      router.push({
        name: 'diseasesView',
        query: {
          disease_id: disease.disease_id,
          disease_name: disease.disease_name,
          categoryId: disease.categoryId,
          etiology:disease.etiology,//病因
          clinical_manifestations:disease.clinical_manifestations,//症状
          complications:disease.complications,//并发症
          treatment:disease.treatment,//治疗
          nursing_care:disease.nursing_care,//护理
        }
      });
    }

    initCategoryArr();


    return {
      ...toRefs(state),
      toggleDiseaseList,
      goToDiseaseDetail
    }
  }
}
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  min-height: 100vh;
  background-color: #f5f7fa;
  padding-bottom: 15vw;
}

/*********************** header ***********************/
header {
  width: 100%;
  height: 12vw;
  background-color: #fff;
  position: fixed;
  left: 0;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 0 4vw;
  box-shadow: 0 1px 6px rgba(0,0,0,0.05);
  z-index: 10;
}

header .fa {
  font-size: 6vw;
  color: #333;
}

header p {
  font-size: 4.5vw;
  font-weight: 600;
  color: #333;
}

/*********************** 疾病分类容器 ***********************/
.category-container {
  margin-top: 12vw;
  padding: 0 4vw;
}

.category-item {
  margin-bottom: 3vw;
  border-radius: 2vw;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0,0,0,0.08);
  background-color: #fff;
}

.category-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 14vw;
  padding: 0 4vw;
  cursor: pointer;
}

.category-name {
  font-size: 4.2vw;
  font-weight: 500;
  color: #333;
}

.category-header .fa {
  font-size: 5vw;
  color: #999;
  transition: transform 0.3s;
}

.disease-list {
  background-color: #f9fafc;
  padding: 0 4vw;
  max-height: 60vh;
  overflow-y: auto;
}

.disease-item {
  padding: 3.5vw 0;
  border-bottom: 1px solid #eee;
  font-size: 3.8vw;
  color: #555;
  transition: background-color 0.2s;
  cursor: pointer;
}

.disease-item:active {
  background-color: #f0f2f5;
}

.disease-item:last-child {
  border-bottom: none;
}

.no-data {
  padding: 4vw 0;
  color: #999;
  font-size: 3.8vw;
  text-align: center;
}

/*********************** 动画效果 ***********************/
.slide-enter-active, .slide-leave-active {
  transition: all 0.3s ease;
  max-height: 500px;
}

.slide-enter-from, .slide-leave-to {
  opacity: 0;
  max-height: 0;
  padding-top: 0;
  padding-bottom: 0;
}
</style>