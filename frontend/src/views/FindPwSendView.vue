<script setup>
import router from '@/router'
import { ref } from 'vue'
import FormInput from '@/components/login/FormInput.vue'
import { checkEmailAuthNumber } from '@/components/api/AuthAPI'
import { useMemberStore } from '@/stores/member'

const store = useMemberStore()
const inputData = ref({
  title: '인증번호를 확인해주세요',
  data: {
    title: '인증번호',
    type: 'text'
  },
  button1: '비밀번호 재설정하기',
  button2: '로그인으로 돌아가기'
})

const goPassword = async (e) => {
  const res = await checkEmailAuthNumber(store.checkPwEmail, e)
  if (res) {
    router.push('/setpw')
  } else {
    alert('인증번호가 틀렸습니다. 다시 입력해 주세요.')
  }
}

const goLogin = () => {
  router.push('/login')
}
</script>

<template>
  <form-input :info="inputData" @btn1="(e) => goPassword(e)" @btn2="goLogin()" />
</template>

<style lang="scss" scoped>
.container {
  @include box(500px, 90%, white, 0.625rem, 1rem, 0.1rem);
  @include font-factory(13px, bold);
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
    @include box(90%, 90%, none, 0, 0, 0);
    @include flex-box($direction: column, $justify: center);
    @include font-factory(13px, bold);
    margin-bottom: 2rem;

    .text-area {
      @include flex-box();
      width: 90%;
      text-align: center;
      margin-bottom: 2rem;
    }
  }
  .login {
    @include box(85%, 2.5rem, $main, 0, 0.5rem, 0);
    border: 1px solid $main;
    color: white;
    cursor: pointer;
  }
  .home {
    @include box(85%, 2.5rem, white, 0, 0.5rem, 0);
    cursor: pointer;
    border: 1px solid $dark;
  }
}
</style>
