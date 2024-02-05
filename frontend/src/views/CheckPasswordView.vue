<script setup>
import { ref } from 'vue'
import FormInput from '@/components/login/FormInput.vue'
import router from '@/router'

const inputData = ref({
  title: '비밀번호 확인',
  data: {
    title: '비밀번호',
    type: 'password'
  },
  button1: '정보 수정하기',
  button2: '홈으로 돌아가기'
})

const goMypage = (e) => {
  //비밀번호 확인 후 정보수정페이지 가기
  router.push('/my')
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
