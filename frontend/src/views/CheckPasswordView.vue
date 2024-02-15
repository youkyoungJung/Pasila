<script setup>
import { ref } from 'vue'
import FormInput from '@/components/login/FormInput.vue'
import router from '@/router'
import { checkPasswordApi } from '@/components/api/MemberAPI.js'

const inputData = ref({
  title: '비밀번호 확인',
  data: {
    title: '비밀번호',
    type: 'password'
  },
  button1: '정보 수정하기',
  button2: '홈으로 돌아가기'
})

const goMypage = async (e) => {
  const user = {
    id: 1,
    password: e
  }

  const res = await checkPasswordApi(user)

  if (res) {
    router.push(`/my`)
  } else {
    alert('비밀번호가 틀렸습니다. 다시 입력해주세요.')
  }
}

const goHome = () => {
  router.push('/')
}
</script>

<template>
  <form-input :info="inputData" @btn1="(e) => goMypage(e)" @btn2="goHome()" />
</template>

<style lang="scss" scoped>
.find-container {
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

    .long-type {
      @include box(95%, 10%, null, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-bottom: 2rem;
    }
    .check-pw {
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
}
</style>
