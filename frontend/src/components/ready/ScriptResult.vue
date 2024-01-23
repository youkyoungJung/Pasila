<script setup>
const saveScript = () => {
  //스크립트 저장하기
}
</script>

<template>
  <div class="result-body">
    <div id="script" class="script">
      <!--대본생성 후 텍스트-->
    </div>
    <div class="button-box">
      <button @click="saveScript" class="save-btn">저장하기</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.result-body {
  @include box(90%, 100%, white, 0, 0.3rem, 0.1rem);
  @include font-factory($fs-1, null);

  .script {
    @include box(30rem, 10rem, $light-gray, 0.3rem, 0.3rem, 0.5rem);
    overflow-y: scroll;
    overflow-x: hidden;
    word-wrap: break-word;
  }
  .script::-webkit-scrollbar {
    width: 0.5rem;
  }
  .script::-webkit-scrollbar-track {
    background: $soft-pink; /*스크롤바 뒷 배경 색상*/
  }
  .script::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: $main; /* 스크롤바의 색상 */

    border-radius: 10px;
  }
  .button-box {
    display: flex;
    justify-content: center;
    .save-btn {
      @include box(8rem, 2rem, $main, 0.3rem, 0.1rem, 0.1rem);
      border: none;
      color: white;
    }
  }
}
</style>
