<script setup>
import router from '@/router'

const goLogin = () => {
  router.push('/login')
}

const goHome = () => {
  router.push('/')
}
</script>

<template>
  <div class="container">
    <div class="header">비밀번호 찾기</div>
    <div class="content">
      <div class="text-area">
        이메일로 비밀번호 재설정 URL이 전송되었습니다. <br />
        URL에서 비밀번호 변경 후 로그인해주세요.
      </div>
      <button @click="goLogin" class="login">로그인하기</button>
      <button @click="goHome" class="home">홈으로 돌아가기</button>
    </div>
  </div>
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
