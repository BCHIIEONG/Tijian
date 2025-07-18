<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>选择体检日期</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <div class="date-box">
        <i class="fa fa-caret-left" @click="subtractMonth"></i>
        <p>{{currYear}}年{{currMonth}}月</p>
        <i class="fa fa-caret-right" @click="addMonth"></i>
      </div>
      <table>
        <tr>
          <th>日</th>
          <th>一</th>
          <th>二</th>
          <th>三</th>
          <th>四</th>
          <th>五</th>
          <th>六</th>
        </tr>
      </table>
      <ul>
        
        <li v-for="(ca,index) in calendarArr" :key="ca.ymd" @click="selected(index)">
          <p :class="{fontcolor:ca.remainder!=null&&ca.remainder!=0,pselect:ca.remainder!=null&&ca.remainder!=0&&ca.isSelected==1}">{{ ca.day }}</p>
          <p>{{ca.remainder!=null&&ca.remainder!=0?"余"+ca.remainder:""}}</p>
        </li>
     <!--
        
      <li>
          <p class="fontcolor pselect">18</p>
          <p>余56</p>
        </li>
        <li>
          <p class="fontcolor">19</p>
          <p>余66</p>
        </li>
     
     -->  
       
      </ul>
    </section>

    <div class="bottom-btn">
      <div></div>
      <div @click="toConfirmOrder">下一步</div>
    </div>

    <div class="bottom-ban"></div>
   <Footer></Footer>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import axios from "axios";
import { useRouter, useRoute } from "vue-router";
import Footer from "@/components/Footer.vue";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();

    //获取当前日期
    const currDate=new Date();  //js代码

    const state = reactive({
        hpId:route.query.hpId,
        smId:route.query.smId,
      currYear:currDate.getFullYear(),
      currMonth:currDate.getMonth()+1,    //从0开始，所以加1
      calendarArr:[],
      selectedDay:""
    });

    //初始化日历界面数据的函数
    function loadCalendar(){
        axios
        .post("/api/listCalendar",{hpId:state.hpId,year:state.currYear,month:state.currMonth}) //  localhost:8080/login/userId=?&password=?
        .then((response) => {
           state.calendarArr=response.data.data;

           for(let i=0;i<state.calendarArr.length;i++){
               //2023-12-12
               if(state.calendarArr[i].ymd!=null&&state.calendarArr[i].ymd!=""){
                    state.calendarArr[i].day=state.calendarArr[i].ymd.substring(8);
               }
              
           }

           //为了方便控制样式，再给每个日历加多一个属性selected  选中为1  未选中为0
          let firstAvailableIndex = -1;
          for (let y = 0; y < state.calendarArr.length; y++) {
            if (state.calendarArr[y].ymd != null) {
              state.calendarArr[y].isSelected = 0;  //默认没有选中
              
              // 找到第一个可选日期的索引
              if (firstAvailableIndex === -1 && 
                  state.calendarArr[y].remainder != null && 
                  state.calendarArr[y].remainder != 0) {
                firstAvailableIndex = y;
              }
            }
          }
          
          // 自动选中第一个可选日期
          if (firstAvailableIndex !== -1) {
            state.calendarArr[firstAvailableIndex].isSelected = 1;
            state.selectedDay = state.calendarArr[firstAvailableIndex].ymd;
          }
        })
        .catch(function (error) {
          // 请求失败处理
          console.log(error);
        });
    }

    loadCalendar();

    function subtractMonth(){
        if(state.currMonth>1){
            state.currMonth--;
        }else{
           state.currYear--;
            state.currMonth=12;
        }
        //界面要不要变化？
        loadCalendar();
    }

    function addMonth(){
        if(state.currMonth<12){
            state.currMonth++;
        }else{
            state.currYear++;
            state.currMonth=1;
        }
        loadCalendar();
    }

    function selected(index){
        //1.把选中的日期存起来
        state.selectedDay=state.calendarArr[index].ymd;
       //清理掉原来的选中样式
        for(let i=0;i<state.calendarArr.length;i++){
            state.calendarArr[i].isSelected=0;
        }

         //2.给选中日期加样式

        state.calendarArr[index].isSelected=1;
    }
    
    function goBack() {
        router.go(-1);
    }
    
    function toConfirmOrder(){
        if(state.selectedDay==""){
            alert("请先选中体检日期");
            return;
        }
        router.push({path:"/confirmOrderView",query:{hpId:state.hpId,smId:state.smId,selectedDay:state.selectedDay}});

    }

    return{
        ...toRefs(state),
        loadCalendar,
        subtractMonth,
        addMonth,
        selected,
        goBack,
        toConfirmOrder

    }
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

/*********************** section ***********************/
section {
  width: 82vw;
  margin: 0 auto;
  margin-top: 12vw;
}
section .date-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 4.5vw;
  font-weight: 600;
}
section .date-box p {
  color: #000;
}
section .date-box i {
  color: #999;
  user-select: none;
  cursor: pointer;
}
section table {
  width: 100%;
  font-size: 3.8vw;
}
section table tr th {
  text-align: center;
  font-weight: 600;
  line-height: 12vw;
}

section ul {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
section ul li {
  width: 14.28%;
  height: 12vw;
  font-weight: 600;
  color: #999;
}
section ul li p:first-child {
  width: 6vw;
  height: 6vw;
  margin: 0 auto;
  border-radius: 3vw;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 4.2vw;

  user-select: none;
  cursor: pointer;
}
section ul li p:last-child {
  font-size: 3vw;
  font-weight: 300;
  text-align: center;
  user-select: none;
}

.fontcolor {
  color: #333;
}

.pselect {
  background-color: #fb902b;
  color: #fff;
}

/*********************** bottom-btn ***********************/
.bottom-btn {
  width: 100%;
  height: 12vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  bottom: 14.2vw;

  display: flex;
}
.bottom-btn div:first-child {
  flex: 2;
}
.bottom-btn div:last-child {
  flex: 1;
  background-color: #117c94;
  text-align: center;
  line-height: 12vw;
  font-size: 5vw;
  color: #fff;

  user-select: none;
  cursor: pointer;
}
</style>