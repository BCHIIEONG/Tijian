<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <!-- 固定头部 -->
        <header>
            <i class="fa fa-angle-left" @click="$router.go(-1)"></i>
            <p class="title">健康发现</p>
            <div class="placeholder"></div>
        </header>

        <!-- 主要内容区域，添加顶部内边距避免被头部遮挡 -->
        <main class="content">
            <!-- 健康咨询 -->
            <div class="info">
                <ul>
                    <li v-for="health in healthArr" :key="health.healthId" >
                        <img :src="health.image" />
                        <div @click="toFoundContentView(health.healthId)">
                            <h3>{{health.title}}</h3>
                        </div>
                    </li>
                    <div class="middle-ban"></div>
                </ul>
            </div>

            <div class="bottom-ban"></div>
        </main>
    </div>
    <Footer></Footer>
</template>

<script>
import { reactive,toRefs } from 'vue';
import axios from 'axios';
import { useRouter,useRoute } from 'vue-router';

export default {
    setup(){

      const router = useRouter();

      const state = reactive({
        healthArr:[]
      })

      function initHealthArr(){
        axios
        .post("/api/listHealth")
        .then((response) =>{
          state.healthArr = response.data.data;
          console.log(state.healthArr);
        })
        .catch(function(error){
          console.log(error);
        })
      }

      function toFoundContentView(healthId){
        router.push({ path: '/foundContentView', query: { healthId: healthId } });
      }

      initHealthArr();

      return{
        ...toRefs(state),
        initHealthArr,
        toFoundContentView,
      }
    }
}
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100vh;
  background-color: #f9f9f9;
  position: relative;
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


/*********************** 内容区域 ***********************/
.content {
  padding-top: 12vw; /* 与header高度一致 */
  padding-bottom: 10vw;
}

/*********************** 健康咨询 ***********************/
.info {
  width: 100%;
  background-color: #fff;
  margin-top: 3vw;
}

.item-title {
  padding: 3vw 4vw;
  font-size: 4.2vw;
  font-weight: 500;
  color: #333;
  border-bottom: 1px solid #f0f0f0;
}

.info ul {
  width: 100%;
  padding: 0;
  margin: 0;
  list-style: none;
}

/* 修改样式部分 */
.info ul li {
  width: 100%;
  min-height: 27vw;
  box-sizing: border-box;
  padding: 3.6vw;
  display: flex;
  align-items: flex-start; /* 改为顶部对齐 */
  border-bottom: 1px solid #f0f0f0; /* 添加分割线 */
}

.info ul li img {
  width: 25vw; /* 调整宽度 */
  height: 25vw; /* 调整为正方形 */
  border-radius: 1.5vw;
  object-fit: cover;
}

.info ul li div {
  width: calc(100% - 28vw); /* 动态计算宽度 */
  padding-left: 3vw;
}

.info ul li div h3 {
  font-size: 4vw;
  font-weight: 500;
  color: #333;
  margin: 0 0 1.5vw 0;
  /* 移除单行限制，改为多行显示 */
  white-space: normal;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}


.info ul li div span {
  font-size: 3vw;
  color: #999;
  display: block;
  margin-bottom: 1.5vw;
}

.info ul li div p {
  text-align: right;
  font-size: 3vw;
  color: #999;
  margin: 0;
}

.info ul li div p i {
  margin-right: 1.5vw;
}

.middle-ban {
  height: 1px;
  background-color: #f0f0f0;
  margin: 0 4vw;
}

.bottom-ban {
  height: 2vw;
  background-color: #f9f9f9;
}
</style>