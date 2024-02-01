<script setup>
import { ref } from 'vue'

const props = defineProps(['data'])
const emit = defineEmits(['deleteData'])

const deleteHighlight = () => {
  //하이라이트 삭제하기
  emit('deleteData', props.data)
}
</script>

<template>
  <div class="highlight">
    <div class="highlight-top">
      <div class="highlight-time">
        <div>{{ props.data.highlightStartTime }}</div>
        ~
        <div>{{ props.data.highlightEndTime }}</div>
      </div>
      <button @click="deleteHighlight" class="delete-btn">삭제</button>
    </div>
    <div class="highlight-title">
      <label for="title">제목</label>
      <div>{{ props.data.highlightTitle }}</div>
    </div>
    <div class="highlight-subtitle">
      <label for="subtitle">자막</label>
      <div class="subtitle-text">{{ props.data.highlightSubtitle }}</div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.highlight {
  @include box(95%, 10rem, white, 0.2rem, 0, 0.3rem);
  @include font-factory($fs-1, null);
  @include drop-shadow;

  .highlight-top {
    @include box(98%, 25%, white, 0.2rem, 0, 0.1rem);
    display: flex;
    justify-content: space-between;
    align-items: center;
    .highlight-time {
      @include box(40%, 60%, $soft-pink, 0.3rem, 0.4rem, 0.2rem);
      border: 1px solid $soft-pink;
      display: flex;
      justify-content: space-evenly;

      input {
        width: 45%;
        border: none;
        border-bottom: 1px solid $dark;
        background-color: $yellow;
        outline: none;
        font-weight: bold;
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
      width: 60%;
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
      width: 60%;
      border: none;
      outline: none;
      background-color: $light-gray;
    }
    textarea {
      resize: none;
      outline: none;
      width: 60%;
      border: none;
      background-color: $light-gray;
    }
    .subtitle-text {
      @include box(88%, 100%, null, 0, 0, 0);
      word-wrap: break-word;
    }
  }

  label {
    width: 8%;
    margin-right: 0.5rem;
    font-weight: bold;
  }
}
</style>
