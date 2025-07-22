<template>
  <div class="page-container">
    <div class="news-container" v-if="currentFounds">
      <div class="news-header">
        <h1>{{currentFounds.title}}</h1>
        <div class="news-meta">
          <span class="date">{{currentFounds.time}}</span>
          <span class="source">来源：{{currentFounds.source}}</span>
        </div>
      </div>

      <div class="news-content">
        <!-- 使用v-html并添加white-space: pre-line样式保留换行 -->
        <div class="content-text" v-html="formatContent(currentFounds.content)"></div>
      </div>
      <div class="news-footer">
        <p>编辑:{{ currentFounds.editor }}</p>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { useRouter, useRoute } from 'vue-router';
import { reactive, toRefs, computed } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const route = useRoute();

    const state = reactive({
      founds: [],
      healthId: route.query.healthId,
    })

    const currentFounds = computed(() => {
      return state.founds.find(found => found.healthId == state.healthId);
    })

    function initFoundsArr(){
      axios
      .post("/api/listHealth")
      .then((response) => {
        state.founds = response.data.data;
        console.log(state.founds);
      })
      .catch(function(error){
        console.log(error);
      });
    }

    // 格式化内容，将换行符转换为<br>标签
    const formatContent = (content) => {
      if (!content) return '';
      return content.replace(/\n/g, '<br>');
    }

    initFoundsArr();

    return {
      ...toRefs(state),
      currentFounds,
      initFoundsArr,
      formatContent,
    }
  },
}
</script>

<style scoped>
.page-container {
  padding-bottom: 60px; /* 为Footer留出空间 */
  position: relative;
  min-height: 100vh;
}

.news-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: "Microsoft YaHei", Arial, sans-serif;
  line-height: 1.6;
  color: #333;
  padding-bottom: 40px; /* 额外的内边距确保内容不被挡住 */
}

.news-header {
  margin-bottom: 20px;
  border-bottom: 1px solid #eee;
  padding-bottom: 15px;
}

.news-header h1 {
  font-size: 24px;
  margin-bottom: 10px;
  color: #222;
}

.news-meta {
  font-size: 14px;
  color: #666;
}

.news-meta .date {
  margin-right: 15px;
}

.news-content {
  margin-bottom: 30px;
}

/* 新增内容样式，保留换行 */
.content-text {
  white-space: pre-line;
  text-indent: 2em;
  margin-bottom: 15px;
}

.warning-box {
  background-color: #fff8e6;
  border-left: 4px solid #ffcc00;
  padding: 15px;
  margin: 20px 0;
}

.warning-box h3 {
  color: #d35400;
  margin-top: 0;
  margin-bottom: 10px;
}

.news-footer {
  text-align: right;
  font-size: 14px;
  color: #666;
  border-top: 1px solid #eee;
  padding-top: 15px;
  margin-bottom: 20px; /* 增加底部外边距 */
}
</style>