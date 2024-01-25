<script setup>
import { reactive, defineProps, computed } from 'vue'

const props = defineProps(['preview'])

const editorText = computed({
  get: () => props.preview,
  set: (val) => {
    editorText = val
  }
})

const state = reactive({
  disabled: true
})
</script>

<template>
  <div class="preview-body">
    <div v-html="editorText" id="preview" class="content ql-editor"></div>
  </div>
</template>

<style lang="scss" scoped>
.preview-body {
  @include box(95%, 100%, none, 0, 0, 0.3rem);
  @include font-factory($fs-1, null);
  border: 1px solid lightgray;
  .content {
    @include box(100%, 100%, none, 0, 0, 0);
    overflow: hidden;
    .ql-editor {
      @include box(100%, 100%, none, 0, 0, 0);
    }
  }
}
</style>
