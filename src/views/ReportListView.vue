<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>健康档案</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <img src="@/assets/img/report.png" />
      <ul v-if="ordersArr.length > 0">
        <li v-for="order in ordersArr" :key="order.orderId">
          <div class="left">
            <i class="fa fa-file-text-o"></i>
            <div>
              <p>{{ convertStr(order.orderDate) }} 体检报告</p>
              <p>{{ order.setmealName || '体检套餐' }}</p>
            </div>
          </div>
          <div class="right" @click="toReport(order.orderId)">
            <i class="fa fa-angle-right"></i>
          </div>
        </li>
      </ul>
      
      <div v-else class="empty-state">
        <i class="fa fa-file-text-o"></i>
        <p>暂无体检报告</p>
        <p>完成体检后报告将在这里显示</p>
      </div>
    </section>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs, onMounted } from "vue";
import axios from "axios";
import { useRouter } from 'vue-router';

export default {
  name: "ReportListView",
  components: {
    Footer,
  },
  setup() {
    const router = useRouter();

    const state = reactive({
      ordersArr: [],
      users: null,
      loading: false
    });

    onMounted(() => {
      const userInfo = JSON.parse(sessionStorage.getItem('jinandaxueuser') || '{}');
      if (!userInfo.userId) {
        router.push('/');
        return;
      }
      state.users = userInfo;
      loadOrdersArr();
    });

    function loadOrdersArr() {
      state.loading = true;
      
      // 模拟数据，实际应该调用后端API
      const mockData = [
        {
          orderId: 1,
          orderDate: "2024-01-15",
          setmealName: "基础体检套餐",
          state: 2
        },
        {
          orderId: 2,
          orderDate: "2023-12-20",
          setmealName: "高级体检套餐",
          state: 2
        }
      ];

      // 实际API调用代码
      /*
      axios.post("/api/listOrdersByUserIdAndState", {
        userId: state.users.userId,
        state: 2 // 2表示已完成状态
      }).then((response) => {
        if (response.data.code === 1) {
          state.ordersArr = response.data.data;
        }
      }).catch((error) => {
        console.error("获取体检报告失败:", error);
      }).finally(() => {
        state.loading = false;
      });
      */

      // 使用模拟数据
      setTimeout(() => {
        state.ordersArr = mockData;
        state.loading = false;
      }, 500);
    }

    function convertStr(str) {
      if (!str) return '';
      const arr = str.split('-');
      return arr[0] + "年" + arr[1] + "月" + arr[2] + "日";
    }

    function toReport(orderId) {
      router.push({
        path: "/report",
        query: { orderId: orderId }
      });
    }

    function goBack() {
      router.go(-1);
    }

    return {
      ...toRefs(state),
      loadOrdersArr,
      convertStr,
      toReport,
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
  height: 15.7vw;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** section ***********************/
section {
  width: 100%;
}

section img {
  width: 100%;
  display: block;
}

section ul {
  width: 86vw;
  margin: 0 auto;
}

section ul li {
  width: 100%;
  height: 18vw;
  border-bottom: solid 1px #eee;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 0 4vw;
  margin-bottom: 1vw;
  border-radius: 2vw;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

section ul li .left {
  display: flex;
  align-items: center;
}

section ul li .left i {
  font-size: 8vw;
  color: #6bb9b6;
  margin-right: 3vw;
}

section ul li .left p:first-child {
  color: #555;
  font-size: 4.2vw;
  font-weight: 600;
}

section ul li .left p:last-child {
  color: #999;
  font-size: 3.2vw;
  font-weight: 600;
  margin-top: 1vw;
}

section ul li .right {
  user-select: none;
  cursor: pointer;
}

section ul li .right i {
  color: #999;
  font-size: 6vw;
}

/*********************** 空状态 ***********************/
.empty-state {
  width: 86vw;
  margin: 0 auto;
  text-align: center;
  padding: 20vw 0;
  background-color: #fff;
  border-radius: 2vw;
  margin-top: 4vw;
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