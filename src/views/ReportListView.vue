<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>健康档案</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <img src="@/assets/img/report.png" />
      <ul>
        <li v-for="order in ordersArr" :key="order.orderId">
          <div class="left">
            <i class="fa fa-file-text-o"></i>
            <div>
              <p>{{convertStr(order.orderDate)}} 体检报告</p> 
              <p>{{order.name}}</p>
            </div>
          </div>
          <div class="right" @click="toReport(order.orderId)">
            <i class="fa fa-angle-right"></i>
          </div>
        </li>
      </ul>
    </section>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";
import { reactive, toRefs } from "vue";
import axios from "axios";
import {useRouter,useRoute} from 'vue-router';

export default {
  setup() {
    const router=useRouter();

    const state = reactive({
      ordersArr: [], //存放从后端查询回来的订单数据  我们看到界面上是体检报告，其实是订单
      users: null
    });

    // 在组件挂载时获取用户信息并加载数据
    const userInfo = JSON.parse(sessionStorage.getItem("jinandaxueuser") || "{}");
    if (userInfo.userId) {
      state.users = userInfo;
      loadOrdersArr();
    } else {
      // 如果没有用户信息，也加载模拟数据进行测试
      loadOrdersArr();
    }

    function loadOrdersArr() {
      // 模拟数据 - 实际应该调用后端API
      const mockData = [
        {
          orderId: 1,
          orderDate: "2024-01-15",
          name: state.users?.realName || "张三"
        },
        {
          orderId: 2,
          orderDate: "2023-12-20",
          name: state.users?.realName || "张三"
        }
      ];
      
      state.ordersArr = mockData;
      
      // 实际API调用代码
      /*
      axios
        .post("/api/listOrdersByUserIdAndState", {
            userId:state.users.userId,
            state:2
        })
        .then((response) => {
            state.ordersArr=response.data.data;
        })
        .catch((error) => {
          console.error(error);
        });
      */
    }

    function convertStr(str){
        //2024-12-23
        let arr=str.split('-');   //[2024,12,23]
        return arr[0]+"年"+arr[1]+"月"+arr[2].substring(0,2)+"日";
    }

    function toReport(orderId){
      router.push({path:"/report",query:{orderId:orderId}})
    }

    function goBack(){
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
  components: {
    Footer,
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
</style>