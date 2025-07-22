<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <!-- 头部 -->
    <header>
      <div class="header-content">
        <div class="header-content-left">
          <h1>沈阳云医院</h1>
          <i class="fa fa-angle-down"></i>
        </div>
        <i class="fa fa-envelope-o"></i>
      </div>
      <div class="header-bottom"></div>
    </header>

    <div class="top-ban"></div>
    <!-- 导航部分 -->
    <nav>
      <div class="logo-img">
        <img src="@/assets/img/logo.png" />
      </div>
      <ul>
        <li>
          <img src="@/assets/img/menu01.png" />
          <div class="nav-item-text">
            <h3>免费咨询</h3>
            <p>新型冠状病毒肺炎</p>
          </div>
        </li>
        <li>
          <img src="@/assets/img/menu02.png" />
          <div class="nav-item-text">
            <h3>网络问诊</h3>
            <p>图文视频网络咨询</p>
          </div>
        </li>
        <li @click="tojiankang">
          <img src="@/assets/img/menu03.png" />
          <div class="nav-item-text">
            <h3>健康热点</h3>
            <p>了解更多健康讯息</p>
          </div>
        </li>
        <li @click="toCommonDiseases">
          <img src="@/assets/img/menu04.png" />
          <div class="nav-item-text">
            <h3>医典自查</h3>
            <p>常见疾病基本信息</p>
          </div>
        </li>
        <li @click="toPhysicalExaminationEncyclopediaView">
          <img src="@/assets/img/menu05.png" />
          <div class="nav-item-text">
            <h3>体检百科</h3>
            <p>体检项目基本知识</p>
          </div>
        </li>
        <li @click="toAppointment">
          <img src="@/assets/img/menu06.png" />
          <div class="nav-item-text">
            <h3>团检预约</h3>
            <p>团体体检套餐定制</p>
          </div>
        </li>
      </ul>
    </nav>

    <!-- 我的健康报告 -->
    <div class="report">
      <div class="item-title">
        <p>我的健康报告</p>
      </div>
      <div class="report-content">
        <p>随时随地查看体检报告</p>
        <div @click="toReportList">立即查看</div>
      </div>
    </div>

    <!-- 推荐医生 -->
    <div class="doctor">
      <div class="item-title">
        <p>推荐医生</p>
      </div>
      <ul>
        <li v-for="doctordetail in doctordetailArr" :key="doctordetail.doctorId"
          @click="toDoctorDetail(doctordetail.doctorId)">
          <img :src="doctordetail.doctorPicture" />
          <h3>{{ doctordetail.doctorName }}</h3>
          <p>{{ doctordetail.doctorLevel }}</p>
        </li>
      </ul>
    </div>

    <!-- 健康评估 -->
    <div class="assess">
      <div class="item-title">
        <p>健康评估</p>
      </div>
      <div class="assess-content">
        <div class="assess-grid">
          <div class="assess-row">
            <div class="assess-item"><img src="@/assets/img/assess1.png" @click="toziwo"/></div>
            <div class="assess-item"><img src="@/assets/img/assess2.png" @click="tofengxian"/></div>
          </div>
          <div class="assess-row">
            <div class="assess-item"><img src="@/assets/img/assess3.png" @click="tojshouming"/></div>
            <div class="assess-item"><img src="@/assets/img/assess4.png" @click="toxinlinianling"/></div>
          </div>
          <div class="assess-row">
            <div class="assess-item"><img src="@/assets/img/assess5.png" @click="tozhongyi"/></div>
            <div class="assess-item"><img src="@/assets/img/assess6.png" @click="totangniao"/></div>
          </div>
        </div>
      </div>
    </div>

    

    <div class="bottom-ban"></div>
  </div>
  <Footer></Footer>
</template>

<script>
import { reactive, toRefs} from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";

export default {
  setup() {
    const router = useRouter();

    const state = reactive({
      doctordetailArr: [], //医院数组
    });

    function tojiankang(){
      window.open('https://health.huanqiu.com/health_news/', '_blank');
    }
    function toziwo(){
      window.open('https://https://aqs.health.ikang.com/promo/feiyan/home.html?saveType=1.huanqiu.com/health_news/', '_blank');
    }
    function tofengxian(){
      window.open('https://www.hsap.com.cn/2017/jkfx_0807/3.html', '_blank');
    }
    function tojshouming(){
      window.open('https://ruseo.cn/Lifespan-calculator/', '_blank');
    }
    function toxinlinianling(){
      window.open('https://iqeq.com.cn/xinlinianlingceshi.html', '_blank');
    }
    function tozhongyi(){
      window.open('https://t.huofar.com/', '_blank');
    }
    function totangniao(){
      window.open('https://www.isclab.org.cn/rsd/RSDAssess.php', '_blank');
    }

    function toPhysicalExaminationEncyclopediaView(){
      router.push('/physicalExaminationEncyclopediaView')
    }

    function toCommonDiseases() {
      router.push('/commonDiseasesView')
    }

    function toReportList() {
      router.push("/reportlist.html");
    }

    function toAppointment() {
      router.push("/appointment");
    }

    function toDoctorDetail(doctorId) {
      router.push({ path: '/doctorDetailView', query: { doctorId: doctorId } });
    }

    function initDoctordetailArr() {
      axios
        .post("/api/listDoctordetail")
        .then((response) => {
          state.doctordetailArr = response.data.data;
          console.log(state.doctordetailArr);
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    initDoctordetailArr();

    return {
      ...toRefs(state),
      toAppointment,
      toDoctorDetail,
      toCommonDiseases,
      toPhysicalExaminationEncyclopediaView,
      toReportList,
      tojiankang,
      toziwo,
      tofengxian,
      tojshouming,
      toxinlinianling,
      tozhongyi,
      totangniao,

    };
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
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
}

header .header-content {
  width: 100%;
  height: 14.2vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

header .header-content .header-content-left {
  display: flex;
  align-items: center;
}

header .header-content .header-content-left h1 {
  font-size: 5vw;
  font-weight: 500;
  margin-left: 3.6vw;
  letter-spacing: 0.4vw;
}

header .header-content .header-content-left .fa-angle-down {
  font-size: 6vw;
  margin-left: 2vw;
}

header .header-content .fa-envelope-o {
  font-size: 5.6vw;
  margin-right: 3.6vw;
}

header .header-bottom {
  width: 100%;
  height: 1.5vw;
  background-color: #f9f9f9;
}

/*********************** nav ***********************/
nav {
  width: 100%;
  height: 110vw;
  background-color: #fff;
  margin-bottom: 3vw;
  overflow: hidden;
}

nav .logo-img {
  width: 92.8vw;
  height: 28vw;
  margin: 0 auto;
  margin-top: 3.6vw;
}

nav .logo-img img {
  width: 92.8vw;
  height: 28vw;
}

nav ul {
  width: 92.8vw;
  margin: 0 auto;
  margin-top: 11vw;

  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

nav ul li {
  width: 45.5vw;
  height: 19.8vw;

  box-sizing: border-box;
  padding-top: 3vw;
  border: solid 1px #efefef;
  box-shadow: 0 2px 0 rgba(0, 0, 0, 0.04);

  border-radius: 1vw;
  margin-bottom: 1vw;

  display: flex;
}

nav ul li img {
  width: 10vw;
  height: 10vw;
  margin: 0 1.4vw;
}

nav ul li .nav-item-text h3 {
  font-size: 4.8vw;
  font-weight: 500;
  margin-bottom: 1vw;
}

nav ul li .nav-item-text p {
  font-size: 3.4vw;
  color: #888;
}

nav ul li .nav-item-text .menu03-h3-span {
  font-size: 6vw;
}

/*********************** 我的健康报告 ***********************/
.report {
  width: 100%;
  height: 32vw;
  background-color: #fff;
  margin-bottom: 3vw;
}

.report .report-content {
  width: 100%;
  height: 19vw;
  box-sizing: border-box;
  padding: 0 3.6vw;
  font-size: 4.3vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.report .report-content>div {
  font-size: 3.6vw;
  color: #fff;
  padding: 1.6vw 3vw;
  background-color: #127a90;
  border-radius: 2vw;

  user-select: none;
  cursor: pointer;
}

/*********************** 推荐医生 ***********************/
.doctor {
  width: 100%;
  height: 79vw;
  background-color: #fff;
  margin-bottom: 3vw;
}

.doctor ul {
  width: 100%;
  height: 66vw;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}

.doctor ul li {
  width: 22vw;
  height: 28vw;
  display: flex;
  flex-direction: column;
  align-items: center;

  user-select: none;
  cursor: pointer;
}

.doctor ul li img {
  width: 14vw;
  height: 14vw;
  border-radius: 7vw;
  margin-bottom: 1.8vw;
}

.doctor ul li h3 {
  font-size: 4vw;
  font-weight: 500;
  margin-bottom: 1.8vw;
}

.doctor ul li p {
  font-size: 3vw;
  color: #888;
}

/*********************** 健康评估 ***********************/
.assess {
  width: 100%;
  background-color: #fff;
  margin-bottom: 3vw;
}

.assess .assess-content {
  width: 100%;
  box-sizing: border-box;
  padding: 3.6vw;
}

.assess .assess-content .assess-grid {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.assess .assess-content .assess-row {
  display: flex;
  justify-content: center;
  margin-bottom: 2vw;
  width: 100%;
}

.assess .assess-content .assess-item {
  width: 45%;
  margin: 0 2.5%;
}

.assess .assess-content .assess-item img {
  width: 100%;
  height: auto;
  display: block;
}



/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}

.middle-ban {
  width: 100%;
  height: 1px;
  background-color: #e9e9e9;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

.item-title {
  width: 100%;
  height: 13vw;
  box-sizing: border-box;
  padding: 0 3.6vw;
  border-bottom: solid 2px #f9f9f9;

  display: flex;
  align-items: center;
  justify-content: space-between;
}

.item-title p {
  height: 4.3vw;
  line-height: 4.3vw;

  box-sizing: border-box;
  padding-left: 3vw;

  font-size: 4.3vw;
  border-left: solid 2px #127a90;
  letter-spacing: 0.2vw;
}

.item-title span {
  font-size: 3vw;
  color: #666;
  user-select: none;
  cursor: pointer;
}
</style>