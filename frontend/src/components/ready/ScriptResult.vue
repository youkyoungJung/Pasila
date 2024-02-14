<script setup>
import { useReadyLiveStore } from '@/stores/readyLive'
const props = defineProps(['script'])
defineEmits(['getData'])
const store = useReadyLiveStore()
const saveScript = () => {
  store.liveScript = props.script
  alert('저장 되었습니다.')
}
</script>

<template>
  <div class="result-body">
    <div id="script" class="script">
      <textarea
        class="script-text"
        @change="$emit('getData', $event.target.value)"
        :value="props.script"
      >
      </textarea>
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
    @include box(95%, 23em, $light-gray, 0.3rem, 0.5rem, 0.5rem);
    padding-top: 0.2rem;

    .script-text {
      @include box(100%, 100%, $light-gray, 0, 0, 0.3rem);
      @include font-factory($fs-1, null);
      border: none;
      resize: none;
      overflow-y: scroll;
      word-wrap: break-word;
      outline: none;
      padding-top: 0;
    }
  }
  .script-text::-webkit-scrollbar {
    width: 0.3rem;
  }

  .script-text::-webkit-scrollbar-thumb {
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
