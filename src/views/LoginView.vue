<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <h1>体检预约-登录</h1>
    <section>
      <div class="input-box">
        <i class="fa fa-user-o"></i>
        <input type="text" placeholder="输入手机号" v-model="users.userId" />
      </div>
      <div class="input-box">
        <i class="fa fa-lock"></i>
        <input
          type="password"
          placeholder="输入登录密码"
          v-model="users.password"
        />
      </div>
      <div class="reg-box">
        <p @click="register">注册</p>
        <p>忘记密码？</p>
      </div>
      <div class="button-box" @click="login">登录</div>
    </section>

    <footer>
      <div>
        <div class="line"></div>
        <p>有疑问请联系客服</p>
        <div class="line"></div>
      </div>
      <p>4008-XXX-XXX</p>
    </footer>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import axios from "axios";
import {useRouter,useRoute} from 'vue-router'

export default {
  setup() {

    const router=useRouter();

    //定义和页面数据关联的数据对象
    const state = reactive({
      users: {
        userId: "",
        password: "",
      },
    });

    //事件函数：登录事件
    function login() {
      console.log("you click login............" + state.users.userId);
      //1.非空校验
      if (state.users.userId == "" || state.users.password == "") {
        alert("用户名或者密码不能为空");
        return;
      }

      //2.异步请求后端工程，判断用户是否合法
      //需要导入axios包  npm install axios --save

      axios
        .post("/api/login",state.users)  //  localhost:8080/login/userId=?&password=?
        .then((response) => {
            //首先后去后端返回数据
            let result=response.data;
            if(result.status==200){
                //登录成功
                //跳转到首页面
                //获取用户信息，把用户信息存入SessionStorage，会话域，方便各个组件共享数据
                // result.data  这个是用户信息
                sessionStorage.setItem("jinandaxueuser",JSON.stringify(result.data));// result.data json对象 存的必须是字符串怎么？
              // const user= JSON.parse(sessionStorage.getItem("jinandaxueuser"));
                alert(result.desc);
                router.push('/index');
            }else{
                alert(result.desc);
                state.users.userId="";
                state.users.password="";
            }
        })
        .catch(function (error) {
          // 请求失败处理
          console.log(error);
        });

      //3.如果合法，那么保存用户数据，方便在各个页面展示，  跳转到首页面

      //4.如果不合法，提示用户用户名或者密码错误，请重新输入
    }

    //注册事件
    function register() {
      console.log("you click register..............." + state.users.password);
    }

    return {
      ...toRefs(state),
      login,
      register,
    };
  },
};
</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-image: linear-gradient(45deg, #266c9f, #266c9f, #7eb059);
  overflow: hidden;
}

/*********************** 标题部分 ***********************/
h1 {
  text-align: center;
  color: #fff;
  font-size: 9.5vw;
  font-weight: 500;
  margin-top: 13vh;
  margin-bottom: 3vh;
}

/*********************** 内容部分 ***********************/
section {
  width: 86vw;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 2.4vw;

  box-sizing: border-box;
  padding: 6vw;
}

section .input-box {
  width: 100%;
  height: 12vw;
  border: solid 1px #ccc;
  border-radius: 2vw;
  margin-top: 5vw;

  display: flex;
  align-items: center;
}
section .input-box i {
  margin: 0 3.6vw;
  font-size: 5.4vw;
  color: #ccc;
}
section .input-box input {
  border: none;
  outline: none;
}

section .reg-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 5vw;
  margin-bottom: 2vw;
}
section .reg-box p {
  font-size: 3.3vw;
  color: #2d727e;
  user-select: none;
  cursor: pointer;
}

section .button-box {
  width: 100%;
  height: 13vw;
  border-radius: 2vw;
  background-color: #70b0bc;

  text-align: center;
  line-height: 13vw;
  font-size: 4.6vw;
  color: #fff;
  letter-spacing: 1vw;

  user-select: none;
  cursor: pointer;
}

/*********************** footer部分 ***********************/
footer {
  width: 86vw;
  margin: 0 auto;
  margin-top: 12vw;
  font-size: 3.8vw;
  color: #fff;
}
footer div {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
footer div .line {
  width: 22vw;
  height: 1px;
  background-color: #fff;
}
footer > p {
  text-align: center;
  margin-top: 2vw;
}
</style>