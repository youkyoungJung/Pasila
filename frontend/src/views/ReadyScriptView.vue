<script setup>
import steps from '@/components/ready/Steps.vue'
import NextButton from '@/components/ready/NextButton.vue'
import ScriptSelect from '@/components/ready/ScriptSelect.vue'
import ScriptResult from '@/components/ready/ScriptResult.vue'

import { ref } from 'vue'

const step = ref('script')
const nextStep = ref('chatbot')
const script = ref('')

const update = (message) => {
  script.value = message
}
</script>

<template>
  <div class="container">
    <steps :data="step" />

    <div class="body">
      <div class="select">
        <script-select @script="update" />
      </div>
      <div class="result">
        <script-result :script="script" />
      </div>
    </div>
    <div class="next-btn">
      <next-button :data="nextStep" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);
  @include font-factory($fs-1, bold);
  .next-btn {
    @include box(100%, null, 0, 0, 0, 0);
    @include flex-box($justify: flex-end);
  }

  .body {
    @include box(100%, 80%, none, 0, 0.3rem, 0.1rem);
    @include flex-box($align: flex-start, $justify: space-evenly);
    border: 2px solid $main;
    border-radius: 0.5rem;

    .select {
      @include box(48%, 100%, white, 0, 0.3rem, 0.1rem);
      @include flex-box($align: flex-start);
    }

    .result {
      @include box(48%, 100%, white, 0, 0.3rem, 0.1rem);
      @include flex-box($align: flex-start);
      border-left: 1px solid $main;
    }
  }
}
</style>
