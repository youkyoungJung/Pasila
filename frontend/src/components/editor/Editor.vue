<script setup>
import { reactive } from 'vue'

const state = reactive({
  content: '',
  _content: '',
  editorOption: {
    placeholder: '',
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
        ['clean'],
        ['link', 'image', 'video']
      ]
    }
  },
  disabled: false
})

const onEditorBlur = (quill) => {
  console.log('editor blur!', quill)
}
const onEditorFocus = (quill) => {
  console.log('editor focus!', quill)
}
const onEditorReady = (quill) => {
  console.log('editor ready!', quill)
}
const onEditorChange = ({ quill, html, text }) => {
  console.log('editor change!', quill, html, text)
  state._content = html
}

setTimeout(() => {
  state.disabled = true
}, 2000)
</script>

<template>
  <quill-editor
    v-model:value="state.content"
    :options="state.editorOption"
    @blur="onEditorBlur($event)"
    @focus="onEditorFocus($event)"
    @ready="onEditorReady($event)"
    @change="onEditorChange($event)"
  />
</template>
