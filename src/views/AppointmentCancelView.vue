<template>
  <div class="wrapper">
    <header>
      <i class="fa fa-angle-left" @click="goBack"></i>
      <p>取消预约</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <section>
      <div class="success">
        <div class="icon-box">
          <div class="icon">
            <i class="fa fa-check"></i>
          </div>
        </div>
        <h1>取消预约成功！</h1>
        <p>退款将在24小时之内到账</p>
      </div>
      <div class="order-btn" @click="viewOrders">查看订单</div>
      <div class="continue" @click="continueBooking">继续为家人预约</div>
      <div class="info">
        <p>您的信息已经发送至体检机构</p>
        <p>取消预约后会发送短信至您的手机</p>
        <p>客服电话<span>4008-XXX-XXX</span></p>
      </div>
    </section>
    
    <div class="bottom-ban"></div>
    <footer>
      <ul>
        <li @click="$router.push('/')">
          <i class="fa fa-home"></i>
          <p>云医院</p>
        </li>
        <li>
          <i class="fa fa-opencart"></i>
          <p>商城</p>
        </li>
        <li>
          <i class="fa fa-compass"></i>
          <p>发现</p>
        </li>
        <li @click="$router.push('/personal')">
          <i class="fa fa-user"></i>
          <p>我</p>
        </li>
      </ul>
    </footer>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'AppointmentCancelView',
  setup() {
    const router = useRouter()

    const goBack = () => {
      router.go(-1)
    }

    const viewOrders = () => {
      router.push('/appointmentlist')
    }

    const continueBooking = () => {
      router.push('/appointment')
    }

    return {
      goBack,
      viewOrders,
      continueBooking
    }
  }
}
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #F9F9F9;
}

header {
  width: 100%;
  height: 15.7vw;
  background-color: #FFF;
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

footer {
  width: 100%;
  height: 14.2vw;
  box-sizing: border-box;
  border-top: solid 1px #E9E9E9;
  background-color: #FFF;
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

.top-ban {
  width: 100%;
  height: 15.7vw;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

section {
  width: 100%;
}

section .success {
  width: 100%;
  height: 62vw;
  border-bottom: solid 1px #EEE;
  background-color: #FFF;
}

section .success .icon-box {
  width: 100%;
  height: 30vw;
  background-image: linear-gradient(135deg, #01C7A4, #02A6C9, #02A6C9);
  position: relative;
}

section .success .icon-box .icon {
  width: 16vw;
  height: 16vw;
  border-radius: 8vw;
  background-color: #FFF;
  position: absolute;
  left: 42vw;
  bottom: -8vw;
  font-size: 8vw;
  color: #02A6C9;
  text-align: center;
  line-height: 16vw;
}

section .success h1 {
  text-align: center;
  font-size: 5.2vw;
  font-weight: 500;
  color: #02A6C9;
  margin-top: 10vw;
}

section .success p {
  text-align: center;
  font-size: 3.4vw;
  color: #555;
  margin-top: 3vw;
}

section .order-btn {
  width: 100%;
  height: 14vw;
  text-align: center;
  line-height: 14vw;
  font-size: 4vw;
  color: #02A6C9;
  background-color: #FFF;
  user-select: none;
  cursor: pointer;
}

section .continue {
  width: 88vw;
  height: 13vw;
  margin: 0 auto;
  background-image: linear-gradient(135deg, #01C7A4, #02A6C9, #02A6C9);
  border-radius: 1vw;
  text-align: center;
  line-height: 13vw;
  margin-top: 5vw;
  font-size: 4vw;
  color: #FFF;
  user-select: none;
  cursor: pointer;
}

section .info {
  width: 100%;
  margin-top: 16vw;
}

section .info p {
  text-align: center;
  font-size: 3.2vw;
  color: #999;
  margin-top: 1vw;
}

section .info p span {
  font-size: 3.6vw;
  color: #555;
  margin-left: 1vw;
}
</style>