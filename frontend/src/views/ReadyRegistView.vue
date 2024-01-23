<script setup>
import steps from '@/components/ready/Steps.vue'
import ProductInput from '@/components/ready/ProductInput.vue'
import NextButton from '@/components/ready/NextButton.vue'
import Preview from '@/components/editor/Preview.vue'
import Editor from '@/components/editor/Editor.vue'

import { ref, watch } from 'vue'

const step = ref('register')
const nextStep = ref('script')
const preview = ref('')

const update = (message) => {
  preview.value = message
}

const sendProduct = () => {
  //상품 정보 보내기
}
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="next-btn">
      <next-button :data="nextStep" @click="sendProduct" />
    </div>
    <div class="body">
      <div class="input-type">
        <product-input />
        <editor :message="preview" @preview-content="update" />
      </div>
      <div class="preview">
        <label for="preview">미리보기</label>
        <preview :preview="preview" id="preview" />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);

  .next-btn {
    @include box(100%, null, 0, 0, 0, 0);
    @include flex-box($justify: flex-end);
  }
  .body {
    @include box(100%, 80%, none, 0, 0.3rem, 0.1rem);
    @include flex-box($align: flex-start, $justify: space-evenly);
    border: 2px solid $main;
    border-radius: 0.5rem;

    .input-type {
      display: flex;
      flex-direction: column;
      border-right: 1px solid $main;
    }
    .preview {
      @include box(70%, 40rem, none, 0.3rem, 0.8rem, 0.1rem);
      @include flex-box($align: flex-start, $direction: column);
      @include font-factory($fs-1, null);
      label {
        @include font-factory($fs-1, bold, $main);
        margin-left: 0.8rem;
      }
    }
  }
}
</style>
