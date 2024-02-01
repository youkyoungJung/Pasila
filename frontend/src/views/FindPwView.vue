<script setup>
import router from '@/router'
import { ref } from 'vue'
import VLongInput from '@/components/common/VLongInput.vue'

const inputData = ref({
  email: {
    title: '이메일',
    type: 'text'
  }
})
const props = defineProps(['data'])
const emit = defineEmits(['getData'])

const login = () => {
  router.push('/login')
}

const findPw = () => {
  //이메일 디비에 존재하면 진행하기
  router.push('/findpw/send')
}
</script>

<template>
  <div class="find-container">
    <div class="header">비밀번호 찾기</div>
    <div class="content">
      <section class="long-type">
        <v-long-input :data="inputData.email" />
      </section>
      <button @click="findPw" class="find-pw">이메일로 비밀번호 찾기</button>
      <button @click="login" class="login">로그인으로 돌아가기</button>
    </div>
  </div>
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
    .find-pw {
      @include box(85%, 2.5rem, $main, 0, 0.5rem, 0);
      border: 1px solid $main;
      color: white;
      cursor: pointer;
    }
    .login {
      @include box(85%, 2.5rem, white, 0, 0.5rem, 0);
      cursor: pointer;
      border: 1px solid $dark;
    }
  }
}
</style>
