<script setup>
import { reactive } from 'vue'

const emits = defineEmits(['preview-content'])

const state = reactive({
  content: '',
  _content: '',
  editorOption: {
    placeholder: '여기에 상품 사진 등록과 상품 정보 입력을 할 수 있어요!',
    modules: {
      toolbar: [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        [{ header: 1 }, { header: 2 }],
        [{ list: 'ordered' }, { list: 'bullet' }],
        [{ script: 'sub' }, { script: 'super' }],
        [{ indent: '-1' }, { indent: '+1' }],
        [{ direction: 'rtl' }],
        [{ size: ['small', false, 'large', 'huge'] }],
        [{ header: [1, 2, 3, 4, 5, 6, false] }],
        [{ color: [] }, { background: [] }],
        [{ font: [] }],
        [{ align: [] }],
        ['link', 'image', 'video']
      ]
    }
  },
  disabled: false
})

const onEditorChange = ({ quill, html, text }) => {
  state._content = html
  emits('preview-content', state._content)
}

setTimeout(() => {
  state.disabled = true
}, 2000)
</script>

<template>
  <div class="editor-box">
    <quill-editor
      v-model:value="state.content"
      :options="state.editorOption"
      @change="onEditorChange($event)"
    />
  </div>
</template>

<style lang="scss">
.ql-toolbar.ql-snow {
  @include box(98%, 100%, $light-gray, 0, 0, 0);
}
section {
  @include box(98%, 100%, white, 0, 0, 0.3rem);
}
.ql-editor {
  @include box(100%, 25rem, white, 0, 0, 0);
}

.editor-box {
  @include box(98%, 100%, none, 0, 0.3rem, 0.1rem);
  @include font-factory($fs-1, null);
  @include flex-box($direction: column);
  margin-top: 0;
  padding-top: 0;
}
</style>
