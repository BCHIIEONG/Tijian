<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>体检报告</p>
      <div></div>
    </header>

    <nav>
      <div :class="{ active: flag == 'value1' }" @click="show('value1')">
        总检结论
      </div>
      <div :class="{ active: flag == 'value2' }" @click="show('value2')">
        报告详情
      </div>
    </nav>

    <div class="top-ban"></div>

    <div class="nav-content-item" v-if="flag == 'value1'">
      <div class="item" v-if="errorReportArr.length > 0">
        <div class="title">异常项</div>
        <ul>
          <li v-for="erci in errorReportArr" :key="erci.cidrId">
            <div class="indications">
              <div class="left">
                <div>异</div>
                <p>{{ erci.name }}</p>
              </div>
              <div class="right">
                <p>{{ erci.value }}{{ erci.unit }}</p>
                <p>正常值范围：{{ erci.normalValueString }}</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
      
      <div class="item" v-if="overallResultArr.length > 0">
        <div class="title">一、尊敬的顾客，您本次体检结论如下：</div>
        <ul>
          <li class="conclusion-title" v-for="(or, index) in overallResultArr" :key="or.orId">
            {{ index + 1 + "、" + or.title }}
          </li>
        </ul>
      </div>
      
      <div class="item" v-if="overallResultArr.length > 0">
        <div class="title">二、尊敬的顾客，您本次体检建议信息如下：</div>
        <ul>
          <li class="conclusion-content" v-for="(ovre, index) in overallResultArr" :key="ovre.orId">
            <h3>{{ index + 1 + "、" + ovre.title }}</h3>
            <p>{{ ovre.content }}</p>
          </li>
        </ul>
      </div>

      <div v-if="overallResultArr.length === 0 && errorReportArr.length === 0" class="empty-state">
        <i class="fa fa-file-text-o"></i>
        <p>暂无体检报告数据</p>
        <p>请等待体检完成后查看</p>
      </div>
    </div>

    <div class="nav-content-item" v-if="flag == 'value2'">
      <div class="item" v-for="ci in reportArr" :key="ci.cirId">
        <div class="title">{{ ci.ciName }}</div>
        <ul>
          <li v-for="cidetail in ci.cidetailedreportList" :key="cidetail.cidrId">
            <div class="indications" v-if="cidetail.type != 4">
              <div class="left">
                <div v-if="cidetail.isError == 1">异</div>
                <p>{{ cidetail.name }}</p>
              </div>
              <div class="right">
                <p>{{ cidetail.value }}</p>
                <p>正常值范围：{{ cidetail.normalValueString }}</p>
              </div>
            </div>
            <div class="indications-type-4" v-if="cidetail.type == 4">
              <div>
                <p>{{ cidetail.name }}</p>
              </div>
              <div>
                <p>{{ cidetail.content }}</p>
              </div>
            </div>
          </li>
        </ul>
      </div>

      <div v-if="reportArr.length === 0" class="empty-state">
        <i class="fa fa-file-text-o"></i>
        <p>暂无详细报告数据</p>
        <p>请等待体检完成后查看</p>
      </div>
    </div>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";

export default {
  name: "ReportView",
  components: {
    Footer,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();

    const state = reactive({
      flag: "value1",
      overallResultArr: [],
      reportArr: [],
      orderId: route.query.orderId,
      users: null,
      errorReportArr: [],
      loading: false
    });

    onMounted(() => {
      const userInfo = JSON.parse(sessionStorage.getItem('jinandaxueuser') || '{}');
      if (!userInfo.userId) {
        router.push('/');
        return;
      }
      state.users = userInfo;
      loadData();
    });

    function loadData() {
      state.loading = true;
      
      // 使用真实API调用
      Promise.all([
        axios.post("/api/listOverallResult", {
          orderId: parseInt(state.orderId)
        }),
        axios.post("/api/listReport", {
          orderId: parseInt(state.orderId)
        })
      ]).then(([overallResponse, reportResponse]) => {
        if (overallResponse.data.status === 200 || overallResponse.data.code === 1) {
          state.overallResultArr = overallResponse.data.data || [];
        } else {
          console.error("获取总检结论失败:", overallResponse.data.desc);
          state.overallResultArr = [];
        }
        
        if (reportResponse.data.status === 200 || reportResponse.data.code === 1) {
          state.reportArr = reportResponse.data.data || [];
          // 筛选异常项
          state.errorReportArr = [];
          for (let i = 0; i < state.reportArr.length; i++) {
            let cidetailList = state.reportArr[i].cidetailedreportList || [];
            for (let j = 0; j < cidetailList.length; j++) {
              if (cidetailList[j].isError == 1) {
                state.errorReportArr.push(cidetailList[j]);
              }
            }
          }
        } else {
          console.error("获取体检报告失败:", reportResponse.data.desc);
          state.reportArr = [];
          state.errorReportArr = [];
        }
      }).catch(error => {
        console.error("获取体检报告失败:", error);
        state.overallResultArr = [];
        state.reportArr = [];
        state.errorReportArr = [];
      }).finally(() => {
        state.loading = false;
      });
    }

    function show(val) {
      state.flag = val;
    }

    function goBack() {
      router.go(-1);
    }

    return {
      ...toRefs(state),
      show,
      loadData,
      goBack
    };
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
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
  height: 31.7vw;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** nav ***********************/
nav {
  width: 100%;
  height: 16vw;
  display: flex;
  background-color: #f9f9f9;
  position: fixed;
  left: 0;
  top: 15.7vw;
  z-index: 100;
}

nav div {
  flex: 1;
  height: 14vw;
  box-sizing: border-box;
  text-align: center;
  line-height: 14vw;
  font-size: 4.2vw;
  font-weight: 600;
  color: #555;
  cursor: pointer;
}

.active {
  border-bottom: solid 2px #137e92;
  color: #137e92;
}

/*********************** nav-content-item ***********************/
.nav-content-item .item {
  width: 92vw;
  margin: 0 auto;
  margin-top: 3vw;
  margin-bottom: 3vw;
  overflow: hidden;
  border-radius: 3vw;
  background-color: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.nav-content-item .item .title {
  width: 100%;
  height: 10vw;
  background-color: #7bafd7;
  line-height: 10vw;
  box-sizing: border-box;
  padding-left: 4vw;
  font-size: 4.2vw;
  color: #fff;
  font-weight: 600;
}

.nav-content-item .item ul {
  width: 100%;
}

/****** 数值型检查项样式 ******/
.nav-content-item .item ul li {
  border-bottom: solid 1px #eee;
}

.nav-content-item .item ul li:last-child {
  border-bottom: none;
}

.nav-content-item .item ul li .indications {
  width: 100%;
  height: 14vw;
  padding: 0 3vw;
  display: flex;
  align-items: center;
  font-size: 3.2vw;
  color: #333;
}

.nav-content-item .item ul li .indications .left {
  flex: 1;
  display: flex;
  align-items: center;
}

.nav-content-item .item ul li .indications .left div {
  background-color: #ba634e;
  color: #fff;
  padding: 0.2vw 0.6vw;
  border-radius: 0.6vw;
  font-size: 2.8vw;
}

.nav-content-item .item ul li .indications .left p {
  font-weight: 600;
  margin-left: 2vw;
}

.nav-content-item .item ul li .indications .right {
  flex: 1;
  text-align: right;
}

.nav-content-item .item ul li .indications .right p:first-child {
  font-weight: 600;
  margin-bottom: 1vw;
}

.nav-content-item .item ul li .indications .right p:last-child {
  color: #999;
  font-size: 2.8vw;
}

/****** 描述型检查项样式 ******/
.nav-content-item .item ul li .indications-type-4 {
  width: 100%;
  box-sizing: border-box;
  padding: 0 3vw;
  font-size: 3.2vw;
  color: #333;
}

.nav-content-item .item ul li .indications-type-4 div {
  margin: 5vw 0;
}

.nav-content-item .item ul li .indications-type-4 div:first-child {
  font-weight: 600;
}

.nav-content-item .item ul li .indications-type-4 div:last-child {
  color: #666;
  line-height: 1.5;
}

.nav-content-item .item ul .conclusion-title {
  width: 100%;
  height: 12vw;
  box-sizing: border-box;
  border-bottom: solid 1px #eee;
  padding: 0 3vw;
  display: flex;
  align-items: center;
  font-size: 4vw;
  color: #555;
  font-weight: 600;
}

.nav-content-item .item ul .conclusion-title:last-child {
  border-bottom: none;
}

.nav-content-item .item ul .conclusion-content {
  width: 100%;
  box-sizing: border-box;
  border-bottom: solid 1px #eee;
  padding: 4vw 3vw;
  font-size: 3.6vw;
  color: #555;
}

.nav-content-item .item ul .conclusion-content:last-child {
  border-bottom: none;
}

.nav-content-item .item ul .conclusion-content h3 {
  font-size: 4vw;
  font-weight: 600;
  margin-bottom: 2vw;
  color: #333;
}

.nav-content-item .item ul .conclusion-content p {
  text-indent: 8vw;
  line-height: 1.6;
  color: #666;
}

/*********************** 空状态 ***********************/
.empty-state {
  width: 92vw;
  margin: 0 auto;
  text-align: center;
  padding: 20vw 0;
  background-color: #fff;
  border-radius: 3vw;
  margin-top: 3vw;
}

.empty-state i {
  font-size: 20vw;
  color: #ccc;
  margin-bottom: 4vw;
}

.empty-state p {
  font-size: 4vw;
  color: #999;
  margin-bottom: 2vw;
}

.empty-state p:first-of-type {
  font-size: 4.5vw;
  font-weight: 600;
  color: #666;
}
</style>