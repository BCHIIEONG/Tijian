<template>
    <!-- 总容器 -->
    <div class="wrapper">
      <header>
        <i class="fa fa-angle-left" @click="goBack"></i>
        <p>个人中心</p>
        <div></div>
      </header>
      <div class="top-ban"></div>
  
      <section>
        <div class="info">
          <div class="content">
            <img src="@/assets/img/user.png" />
            <div>
              <p>{{ users.realName }}</p>
              <p>账号: {{ users.userId }}</p>
            </div>
          </div>
        </div>
        <ul>
          <li @click="goToAppointmentList">
            <div class="left">
              <i class="fa fa-user-plus"></i>
              <p>我的预约</p>
            </div>
            <div class="right">
              <i class="fa fa-angle-right"></i>
            </div>
          </li>
          <li>
            <div class="left">
              <i class="fa fa-volume-control-phone"></i>
              <p>我的服务</p>
            </div>
            <div class="right">
              <i class="fa fa-angle-right"></i>
            </div>
          </li>
          <li @click="goToReportList">
            <div class="left">
              <i class="fa fa-file-text-o"></i>
              <p>健康档案</p>
            </div>
            <div class="right">
              <i class="fa fa-angle-right"></i>
            </div>
          </li>
          <li @click="goToAppointment">
            <div class="left">
              <i class="fa fa-calendar"></i>
              <p>立即预约</p>
            </div>
            <div class="right">
              <i class="fa fa-angle-right"></i>
            </div>
          </li>
          <li>
            <div class="left">
              <i class="fa fa-cog"></i>
              <p>设置</p>
            </div>
            <div class="right">
              <i class="fa fa-angle-right"></i>
            </div>
          </li>
          <li @click="loginOut">
            <div class="left">
              <i class="fa fa-sign-out"></i>
              <p>退出登录</p>
            </div>
            <div class="right">
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
  import { getSessionStorage, removeSessionStorage } from "@/common";
  import { useRouter } from "vue-router";
  export default {
    setup() {
      const router = useRouter();
      const userData = JSON.parse(sessionStorage.getItem("jinandaxueuser") || "{}") || {
        realName: "用户名",
        userId: "请先登录"
      };
      
      const state = reactive({
        users: userData,
      });
  
      function goBack() {
        router.go(-1);
      }
  
      function loginOut() {
        sessionStorage.removeItem("jinandaxueuser");
        router.push('/');
      }

      function goToAppointmentList() {
        router.push('/appointmentlist');
      }

      function goToReportList() {
        router.push('/reportlist.html');
      }

      function goToAppointment() {
        router.push('/appointment');
      }
  
      return {
        ...toRefs(state),
        goBack,
        loginOut,
        goToAppointmentList,
        goToReportList,
        goToAppointment
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
  }
  
  /*********************** footer ***********************/
  footer {
    width: 100%;
    height: 14.2vw;
    box-sizing: border-box;
    border-top: solid 1px #e9e9e9;
    background-color: #fff;
  
    position: fixed;
    left: 0;
    bottom: 0;
  }
  footer ul {
    width: 100%;
    height: 14.2vw;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }
  footer ul li {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  
    font-size: 3vw;
    color: #999;
  
    user-select: none;
    cursor: pointer;
  }
  footer ul li .fa {
    font-size: 5vw;
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
  section .info {
    width: 100%;
    height: 30vw;
    background-image: linear-gradient(135deg, #7db35d, #02a6c9, #02a6c9);
    display: flex;
    align-items: center;
  }
  section .info .content {
    width: 90vw;
    margin: 0 auto;
    display: flex;
  }
  section .info .content img {
    width: 14vw;
    height: 14vw;
    border-radius: 7vw;
  }
  section .info .content div {
    height: 14vw;
    margin-left: 3vw;
    color: #fff;
  }
  section .info .content div p:first-child {
    font-size: 4.8vw;
  }
  section .info .content div p:last-child {
    font-size: 3.2vw;
    margin-top: 2.6vw;
  }
  
  section ul {
    width: 86vw;
    margin: 0 auto;
  }
  section ul li {
    width: 100%;
    height: 14vw;
    border-bottom: solid 1px #eee;
    color: #555;
    font-size: 4.2vw;
    font-weight: 600;
    display: flex;
    justify-content: space-between;
    align-items: center;
    cursor: pointer;
  }
  section ul li .left {
    display: flex;
    align-items: center;
  }
  section ul li .left p {
    margin-left: 3vw;
  }
  section ul li .right {
    user-select: none;
    cursor: pointer;
  }
  section ul li .right i {
    font-size: 6vw;
  }
  </style>