<script setup>
import { ref } from 'vue'
import FormInput from '@/components/login/FormInput.vue'
import router from '@/router'
import { checkMyEmailApi } from '@/components/api/MemberAPI'
import { getEmailAuthNumberApi } from '@/components/api/AuthAPI'
import { useMemberStore } from '@/stores/member'

const inputData = ref({
  title: '비밀번호 찾기',
  data: {
    title: '이메일',
    type: 'email'
  },
  button1: '이메일로 비밀번호 찾기',
  button2: '로그인으로 돌아가기'
})

const strongEmail = (str) => {
  return /^[A-Za-z0-9_.-]+@[A-Za-z0-9-]+\.[A-Za-z0-9-]/.test(str)
}
const store = useMemberStore()
const goEmail = async (e) => {
  if (!strongEmail(e)) {
    alert('이메일 형식을 확인해주세요!')
    return
  }
  const res = await checkMyEmailApi(e)
  if (!res) {
    store.checkPwEmail = e
    await getEmailAuthNumberApi(e)
    router.push('/findpw/send')
  } else {
    alert('존재하지 않는 이메일입니다. 다시 작성해주세요.')
  }
}

const goLogin = () => {
  router.push('/login')
}
</script>

<template>
  <form-input :info="inputData" @btn1="(e) => goEmail(e)" @btn2="goLogin()" />
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
