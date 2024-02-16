<script setup>
import VLongInput from '@/components/common/VLongInput.vue'

import { ref } from 'vue'

const props = defineProps(['info'])
const emit = defineEmits(['btn1', 'btn2'])
const inputData = ref('')

const button1 = () => {
  emit('btn1', inputData.value)
}

const button2 = () => {
  emit('btn2')
}
</script>

<template>
  <div class="find-container">
    <div class="header">{{ props.info.title }}</div>
    <div class="content">
      <section id="section" class="long-type">
        <v-long-input :data="props.info.data" @getData="(e) => (inputData = e)" />
      </section>
      <button @click="button1" class="btn1">{{ props.info.button1 }}</button>
      <button @click="button2" class="btn2">{{ props.info.button2 }}</button>
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
    #section {
      margin: 0.3rem 0;
    }
    .long-type {
      @include box(95%, 10%, null, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-bottom: 2rem;
    }
    .btn1 {
      @include box(85%, 2.5rem, $main, 0, 0.5rem, 0);
      border: 1px solid $main;
      color: white;
      cursor: pointer;
    }
    .btn2 {
      @include box(85%, 2.5rem, white, 0, 0.5rem, 0);
      cursor: pointer;
      border: 1px solid $dark;
    }
  }
}
</style>
