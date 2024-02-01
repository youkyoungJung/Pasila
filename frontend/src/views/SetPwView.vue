<script setup>
import router from '@/router'
import { ref } from 'vue'
import VLongInput from '@/components/common/VLongInput.vue'

const inputData = ref({
  password: {
    title: '새 비밀번호',
    type: 'password',
    value: ''
  },
  passwordCheck: {
    title: '비밀번호 확인',
    type: 'password',
    value: ''
  }
})
const props = defineProps(['data'])
const emit = defineEmits(['getData'])

const login = () => {
  router.push('/login')
}

const findPw = () => {
  if (inputData.value.password.value != inputData.value.passwordCheck.value) {
    alert('비밀번호가 다릅니다. 다시 확인해 주세요.')
  } else {
    //비밀번호 변경해주기
    alert('비밀번호가 변경되었습니다. 로그인을 해주세요.')
    router.push('/login')
  }
}
</script>

<template>
  <div class="find-container">
    <div class="header">비밀번호 재설정</div>
    <div class="content">
      <section class="long-type">
        <v-long-input :data="inputData.password" @getData="(e) => (inputData.password.value = e)" />
      </section>
      <section class="long-type">
        <v-long-input
          :data="inputData.passwordCheck"
          @getData="(e) => (inputData.passwordCheck.value = e)"
        />
        <div
          v-if="
            inputData.password.value != '' &&
            inputData.password.value === inputData.passwordCheck.value
          "
          class="check-text"
        >
          비밀번호가 일치합니다.
        </div>
        <div v-else class="wrong-text">비밀번호가 일치하지 않습니다. 다시 입력해주세요.</div>
      </section>
      <button @click="findPw" class="find-pw">확인</button>
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
      margin-bottom: 1rem;
    }

    .check-text {
      margin: 2px;
      color: #0085ff;
      font-size: 0.7rem;
      width: 90%;
      display: flex;
      justify-content: flex-start;
      text-align: flex-start;
      margin-bottom: 0.3rem;
    }
    .wrong-text {
      margin: 2px;
      color: $main;
      font-size: 0.7rem;
      width: 90%;
      display: flex;
      justify-content: flex-start;
      text-align: flex-start;
      margin-bottom: 0.3rem;
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
