<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>选择体检套餐</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul class="setmeal">
      <li v-for="(setmeal,index) in setmealArr" :key="setmeal.smId">
        <div class="item">
          <div class="item-left" @click="toSelectDate(setmeal.smId)">
            <h3>{{setmeal.type==1?"男士套餐":"女士套餐"}}</h3>
            <p>{{setmeal.name}}</p>
          </div>
          <div class="item-right" @click="show(index)">
            <p>详情</p>
            <i class="fa fa-angle-down"></i>
          </div>
        </div>
        <div class="item-content" v-if="setmeal.isShow">
          <table>
            <tr>
              <th>检查项目</th>
              <th>检查内容</th>
              <th>检查意义</th>
            </tr>
            <tr v-for="checkitem in setmeal.checkitemsList" :key="checkitem.ciId">
              <td>{{ checkitem.ciName }}</td>
              <td>{{checkitem.ciContent}}</td>
              <td>{{ checkitem.meaning }}</td>
            </tr>
            
          </table>
        </div>
      </li>
      
    </ul>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import axios from "axios";
import {useRouter,useRoute} from 'vue-router';
import Footer from "@/components/Footer.vue";

export default {
  setup() {

    const router=useRouter();
    const route=useRoute();

    const state = reactive({
      setmealArr: [],
      hpId:route.query.hpId    //接收传过来的参数医院编号
    });

    function initSetmealArr() {
      axios
        .post("/api/listSetmeal",{sex:JSON.parse(sessionStorage.getItem("jinandaxueuser")).sex}) //  localhost:8080/login/userId=?&password=?
        .then((response) => {
          //首先后去后端返回数据
          state.setmealArr = response.data.data;
         // console.log(state.setmealArr);
         for(let i=0;i<state.setmealArr.length;i++){
          //给套餐对象扩展一个属性，是否显示检查项，默认值 false
            state.setmealArr[i].isShow=false;
         }
        })
        .catch(function (error) {
          // 请求失败处理
          console.log(error);
        });
    }

    initSetmealArr();

    function show(index){
      //state.setmealArr[index].isShow=true;
      state.setmealArr[index].isShow=!state.setmealArr[index].isShow;
    }

    function goBack() {
        router.go(-1);
    }

    function toSelectDate(smId){
        //console.log("hpId:"+state.hpId+",smId:"+smId);
        //跳转到选择体检日期界面
        //需要把医院编号和套餐编号都给到下一个界面
        router.push({path:"/selectDateView",query:{hpId:state.hpId,smId:smId}});

    }

    return {
      ...toRefs(state),
      initSetmealArr,
      show,
      goBack,
      toSelectDate
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

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}
.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** setmeal ***********************/
.setmeal {
  width: 100%;
  margin-top: 10vw;
}
.setmeal li {
  width: 92.8vw;
  margin: 0 auto;
  border: solid 1px #eee;
  border-radius: 1vw;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.08);
  background-color: #fff;
  margin-bottom: 3.6vw;
}
.setmeal li .item {
  width: 100%;
  height: 20vw;
  color: #555;

  display: flex;
  align-items: center;
}
.setmeal li .item .item-left {
  flex: 0 0 72vw;
  height: 20vw;
  border-right: solid 1px #eee;
  box-sizing: border-box;
  padding-left: 12vw;

  display: flex;
  flex-direction: column;
  justify-content: center;

  user-select: none;
  cursor: pointer;
}
.setmeal li .item .item-left h3 {
  font-size: 4.3vw;
  font-weight: 600;
}
.setmeal li .item .item-left p {
  font-size: 4vw;
  margin-top: 1vw;
}
.setmeal li .item .item-right {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;

  user-select: none;
  cursor: pointer;
}
.setmeal li .item .item-right p {
  font-size: 4vw;
  margin-right: 2vw;
}

.setmeal li .item-content {
  /*display: none;*/
  width: 100%;
  background-color: #fff;
}
.setmeal li .item-content table {
  width: 100%;
  border-collapse: collapse;
  font-size: 4vw;
  color: #555;
}
.setmeal li .item-content table tr {
  display: flex;
}
.setmeal li .item-content table tr td,
.setmeal li .item-content table tr th {
  flex: 1;
}
.setmeal li .item-content table tr th {
  text-align: center;
  background-color: #eee;
  height: 10vw;
  line-height: 10vw;
}
.setmeal li .item-content table tr td {
  border: solid 1px #efefef;
  box-sizing: border-box;
  padding: 2vw;
}
</style>