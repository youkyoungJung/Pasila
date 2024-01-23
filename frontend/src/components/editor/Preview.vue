<script setup>
import { ref, defineProps, computed, watch } from 'vue'

const props = defineProps(['preview'])

const editorText = computed({
  get: () => props.preview,
  set: (val) => {
    editorText = val
  }
})
</script>

<template>
  <div class="preview-body">
    <div v-html="editorText" class="preview-html"></div>
  </div>
</template>

<style lang="scss" scoped>
.preview-body {
  @include box(90%, 90%, none, 0, 0, 0);
  border: 1px solid $light-dark;
  overflow: scroll;
  font-size: 10px;
  color: black;

  .preview-html {
    @include box(100%, 100%, none, 0, 0, 0);
  }
}
</style>
