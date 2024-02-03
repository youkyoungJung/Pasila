<script setup></script>

<template>
  <div class="script-box">
    <img src="@/assets/img/chatgpt-icon.png" alt="gpt" class="icon" width="120" />
    <div class="script">
      <span class="icon-color">
        <font-awesome-icon icon="fa-solid fa-circle-chevron-left" size="2x" />
      </span>
      <span class="text"
        >여러분 많이 기다리셨죠 제가 큰 건 잡아왔어요! 단골 고객님들은 알텐데 제가 가져온 제품
        좋은거 다 알죠?</span
      >
      <span class="icon-color">
        <font-awesome-icon icon="fa-solid fa-circle-chevron-right" size="2x" />
      </span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.script-box {
  flex: 1;
  @include box(calc(100% - 2rem), calc(28vh - 3rem), $gray, 20px, 0, 1rem);
  .script {
    @include flex-box();
    height: 75%;
    .text {
      @include font-factory($fs-3, 800);
      padding: 0 1rem;
    }
    .icon-color {
      color: $dark;
      width: 5%;
    }
  }
}
</style>
