<script setup>
import router from '@/router'
import { ref } from 'vue'
import VLongInput from '@/components/common/VLongInput.vue'

const user = ref({
  userEmail: '',
  userPassword: ''
})

const findPassword = () => {
  router.push('/findpw')
}

const inputData = ref({
  email: {
    title: '이메일',
    type: 'text'
  },
  password: {
    title: '비밀번호',
    type: 'password'
  }
})

const login = () => {
  //이메일로 로그인하기
}

const join = () => {
  router.push('/join')
}

//소셜 로그인 당시 필수나 선택으로 가져온 정보를 회원가입 페이지에 넣어주기
//그리고 필수적으로 이메일을 받아서 이메일로 로그인 가능!
const googleLogin = () => {}

const kakaoLogin = () => {}

const naverLogin = () => {}
</script>

<template>
  <div class="container">
    <div class="header">로그인</div>
    <div class="content">
      <section class="long-type">
        <v-long-input :data="inputData.email" @getData="(e) => (user.userEmail = e)" />
      </section>
      <section class="long-type">
        <v-long-input :data="inputData.password" @getData="(e) => (user.userPassword = e)" />
      </section>
      <section class="forget-pw">
        <button @click="findPassword" class="find-pw">비밀번호를 잊으셨나요?</button>
      </section>
      <button @click="login" class="login">로그인</button>
      <button @click="join" class="join">회원가입</button>
      <hr />
      <div class="social-login" @click="googleLogin">
        <img src="@/assets/img/google-logo.png" />
        <button class="social-btn">구글로 시작하기</button>
      </div>
      <div class="social-login" @click="kakaoLogin">
        <img src="@/assets/img/kakao-logo.png" />
        <button class="social-btn">카카오로 시작하기</button>
      </div>
      <div class="social-login" @click="naverLogin">
        <img src="@/assets/img/naver-logo.png" />
        <button class="social-btn">네이버로 시작하기</button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(500px, 90%, white, 0.625rem, 1rem, 0.1rem);
  @include flex-box($direction: column);
  @include drop-shadow;
  border: 2px solid #d9d9d9;
  margin-left: auto;
  margin-right: auto;

  .header {
    margin: 2rem 0;
    @include font-factory($fs-3, bold);
  }
  .content {
    @include box(90%, 90%, none, 0, 10px, 10px);
    @include font-factory(13px, bold);
    display: flex;
    flex-direction: column;
    align-items: center;

    .long-type {
      @include box(95%, 10%, null, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .login {
      @include box(80%, 43px, $main, 0, 2px, 5px);
      margin-top: 30px;
      border: none;
      color: white;
    }

    .forget-pw {
      @include box(90%, 10%, null, 0, 2px, 5px);
      @include flex-box();
      justify-content: flex-end;
      .find-pw {
        justify-content: flex-end;
        border: none;
        background-color: white;
        cursor: pointer;
        color: $main;
      }
    }
    .join {
      @include box(80%, 43px, white, 0, 2px, 5px);
      margin-top: 10px;
      border: 1px solid $dark;
      cursor: pointer;
    }
  }

  hr {
    width: 90%;
    margin: 35px 0px;
  }

  .social-login {
    @include box(85%, 2.5rem, whitesmoke, 0, 0.5rem, 0);
    @include flex-box();
    cursor: pointer;

    img {
      width: 1rem;
      height: 1rem;
      vertical-align: middle;
    }
    .social-btn {
      cursor: pointer;
      background-color: whitesmoke;
      border: none;
    }
  }
}
</style>
