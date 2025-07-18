<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>注册</p>
      <div></div>
    </header>
    <div class="top-ban"></div>
    <h1>欢迎注册</h1>
    <table>
      <tr>
        <td>手机号码</td>
        <td>
          <input
            type="text"
            placeholder="请输入手机号码"
            v-model="users.userId"
            @blur="isExist"
            @keyup.enter="register"
          />
        </td>
      </tr>
      <tr>
        <td>真实姓名</td>
        <td>
          <input
            type="text"
            placeholder="真实姓名便于查看体检报告"
            v-model="users.realName"
            @keyup.enter="register"
          />
        </td>
      </tr>
      <tr>
        <td>生日</td>
        <td><input type="date" v-model="users.birthday" /></td>
      </tr>
      <tr>
        <td>性别</td>
        <td>
          <input type="radio" v-model="users.sex" value="1" />男
          <input type="radio" v-model="users.sex" value="0" />女
        </td>
      </tr>
      <tr>
        <td>身份证号</td>
        <td>
          <input
            type="text"
            placeholder="请输入身份证号"
            v-model="users.identityCard"
            @keyup.enter="register"
          />
        </td>
      </tr>
      <tr>
        <td>密码</td>
        <td>
          <input
            type="password"
            placeholder="请输入密码"
            v-model="users.password"
            @keyup.enter="register"
          />
        </td>
      </tr>
      <tr>
        <td>确认密码</td>
        <td>
          <input
            type="password"
            placeholder="请再次输入密码"
            v-model="confirmPassword"
            @keyup.enter="register"
          />
        </td>
      </tr>
    </table>
    <div class="btn" @click="register">完成</div>
  </div>
</template>

<script>
import { reactive, toRefs } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

export default {
  name: "RegisterView",
  setup() {
    const router = useRouter();

    const state = reactive({
      users: {
        userId: "",
        realName: "",
        birthday: "",
        sex: 1,
        password: "",
        identityCard: "",
      },
      confirmPassword: "",
    });

    function goBack() {
      router.go(-1);
    }

    function isExist() {
      // 检查手机号是否存在
      if (state.users.userId && state.users.userId.length === 11) {
        axios.post("/api/checkUserExist", {
          userId: state.users.userId
        }).then(response => {
          if (response.data.code === 1 && response.data.data) {
            alert("该手机号已注册，请直接登录");
          }
        }).catch(error => {
          console.error("检查用户是否存在失败:", error);
        });
      }
    }

    function validateForm() {
      if (!state.users.userId) {
        alert("请输入手机号码");
        return false;
      }
      if (!/^1[3-9]\d{9}$/.test(state.users.userId)) {
        alert("请输入正确的手机号码");
        return false;
      }
      if (!state.users.realName) {
        alert("请输入真实姓名");
        return false;
      }
      if (!state.users.birthday) {
        alert("请选择生日");
        return false;
      }
      if (!state.users.identityCard) {
        alert("请输入身份证号");
        return false;
      }
      if (!/^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(state.users.identityCard)) {
        alert("请输入正确的身份证号");
        return false;
      }
      if (!state.users.password) {
        alert("请输入密码");
        return false;
      }
      if (state.users.password.length < 6) {
        alert("密码长度不能少于6位");
        return false;
      }
      if (state.confirmPassword !== state.users.password) {
        alert("两次输入的密码不一致");
        return false;
      }
      return true;
    }

    function register() {
      if (!validateForm()) {
        return;
      }

      axios.post("/api/register", state.users)
        .then((response) => {
          let result = response.data;
          if (result.status === 200 || result.code === 1) {
            alert("注册成功！");
            router.push("/");
          } else {
            alert(result.desc || "注册失败！");
          }
        })
        .catch((error) => {
          console.error("注册失败:", error);
          alert("注册失败，请稍后重试");
        });
    }

    return {
      ...toRefs(state),
      goBack,
      isExist,
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
  background-color: #F9F9F9;
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

/*********************** 标题部分 ***********************/
h1 {
  font-size: 7.4vw;
  font-weight: 500;
  box-sizing: border-box;
  padding: 0 3.6vw;
  margin-top: 6vw;
  color: #333;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}

/*********************** table ***********************/
table {
  width: 92.8vw;
  margin: 0 auto;
  margin-top: 5vw;
  border-collapse: collapse;
  font-size: 4.2vw;
  background-color: #fff;
  border-radius: 2vw;
  overflow: hidden;
}

table tr td {
  height: 14vw;
  border-bottom: solid 1px #f0f0f0;
  padding: 0 4vw;
}

table tr:last-child td {
  border-bottom: none;
}

table tr td:first-child {
  width: 25%;
  color: #333;
  font-weight: 500;
}

table tr td input {
  border: none;
  outline: none;
  font-size: 4.2vw;
  width: 100%;
  color: #333;
}

table tr td input::placeholder {
  color: #ccc;
}

table tr td input[type="radio"] {
  width: auto;
  margin-right: 2vw;
}

/*********************** btn ***********************/
.btn {
  width: 92.8vw;
  margin: 0 auto;
  height: 12vw;
  margin-top: 8vw;
  background-color: #137e92;
  border-radius: 2vw;
  color: #fff;
  font-size: 5vw;
  text-align: center;
  line-height: 12vw;
  user-select: none;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(19, 126, 146, 0.3);
}

.btn:hover {
  background-color: #0f6b7d;
}

.btn:active {
  transform: translateY(1px);
}
</style>