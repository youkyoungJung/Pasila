<script setup>
import router from '@/router'
import { ref } from 'vue'
import VLongInput from '@/components/common/VLongInput.vue'
import { emailLoginApi } from '@/components/api/AuthAPI'

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

const login = async () => {
  const res = await emailLoginApi(user.value.userEmail, user.value.userPassword)
  if (res) {
    router.go()
  } else {
    alert('비밀번호가 틀렸습니다. 다시 입력해주세요!')
  }
}

const join = () => {
  router.push('/join')
}
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
  padding: 1rem 0;

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
      cursor: pointer;
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
    margin: 20px 0px;
  }
}
</style>
