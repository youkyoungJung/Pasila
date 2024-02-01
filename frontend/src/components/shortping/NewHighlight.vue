<script setup>
import { ref } from 'vue'
const emit = defineEmits(['getData'])

const newHigh = ref({
  isEnroll: false,
  highlightTitle: '',
  highlightStartTime: '',
  highlightEndTime: '',
  highlightSubtitle: ''
})
const newHighlight = () => {
  //하이라이트 추가하기
  newHigh.value.isEnroll = true
  emit('getData', newHigh.value)
}
const resetHigh = () => {
  newHigh.value.isEnroll = false
  newHigh.value.highlightTitle = ''
  newHigh.value.highlightStartTime = ''
  newHigh.value.highlightEndTime = ''
  newHigh.value.highlightSubtitle = ''
}
</script>

<template>
  <div class="highlight">
    <div class="highlight-top">
      <div class="highlight-time">
        <input type="number" v-model="newHigh.highlightStartTime" />~
        <input type="number" v-model="newHigh.highlightEndTime" />
      </div>
      <button @click="newHighlight" class="delete-btn">등록</button>
    </div>
    <div class="highlight-title">
      <label for="title">제목</label>
      <input
        id="title"
        type="text"
        placeholder="제목을 입력해주세요."
        v-model="newHigh.highlightTitle"
      />
    </div>
    <div class="highlight-subtitle">
      <label for="subtitle">자막</label>
      <textarea
        id="subtitle"
        type="text"
        placeholder="자막을 입력해주세요."
        v-model="newHigh.highlightSubtitle"
      ></textarea>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.highlight {
  @include box(95%, 10rem, white, 0.2rem, 0, 0.3rem);
  @include font-factory($fs-1, bold);
  @include drop-shadow;

  .highlight-top {
    @include box(98%, 25%, white, 0.2rem, 0, 0.1rem);
    display: flex;
    justify-content: space-between;
    align-items: center;
    .highlight-time {
      @include box(40%, 60%, $yellow, 0.3rem, 0.4rem, 0.2rem);
      border: 1px solid $yellow;
      display: flex;
      justify-content: space-evenly;

      input {
        width: 45%;
        border: none;
        background-color: transparent;
        outline: none;
        font-weight: bold;
        text-align: center;
      }

      ::-webkit-outer-spin-button,
      ::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }
    }
    .delete-btn {
      @include box(10%, 60%, $main, 0.2rem, 0, 0.2rem);
      font-size: 0.7rem;
      border: none;
      color: white;
      cursor: pointer;
    }
  }

  .highlight-title {
    @include box(95%, 25%, white, 0.2rem, 0, 0.3rem);
    display: flex;
    align-items: flex-start;

    input {
      width: 70%;
      height: 70%;
      border: none;
      outline: none;
      background-color: $light-gray;
    }
  }

  .highlight-subtitle {
    @include box(95%, 35%, white, 0.2rem, 0, 0.3rem);
    display: flex;
    align-items: flex-start;

    input {
      width: 70%;
      border: none;
      outline: none;
      background-color: $light-gray;
    }
    textarea {
      resize: none;
      outline: none;
      width: 70%;
      border: none;
      background-color: $light-gray;
    }
  }

  label {
    margin-right: 0.5rem;
  }
}
</style>
