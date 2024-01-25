<script setup>
import { defineProps, ref } from 'vue'

const props = defineProps(['script'])

const saveScript = () => {
  //스크립트 저장하기
}
</script>

<template>
  <div class="result-body">
    <div id="script" class="script">
      <div class="script-text">
        {{ props.script }}
      </div>
    </div>
    <div class="button-box">
      <button @click="saveScript" class="save-btn">저장하기</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.result-body {
  @include box(95%, 100%, white, 0, 0.3rem, 0.1rem);
  @include font-factory($fs-1, null);
  display: flex;
  flex-direction: column;
  justify-content: center;

  .script {
    @include box(95%, 18em, $light-gray, 0.3rem, 0.5rem, 0.5rem);
    overflow-y: scroll;
    overflow-x: hidden;
    word-wrap: break-word;

    .script-text {
      @include font-factory($fs-1, null);
    }
  }
  .script::-webkit-scrollbar {
    width: 0.3rem;
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
      cursor: pointer;
    }
  }
}
</style>
