<script setup>
import { ref } from 'vue'
import NewHighlight from '@/components/shortping/NewHighlight.vue'
import SavedHighlight from '@/components/shortping/SavedHighlight.vue'

const props = defineProps(['data'])
const emit = defineEmits(['getData', 'deleteData', 'addEmptyData'])

let emptyData = ref({
  isEnroll: false,
  highlightTitle: '',
  highlightStartTime: '',
  highlightEndTime: '',
  highlightSubtitle: ''
})

const addHighlight = () => {
  emit('addEmptyData', emptyData.value)
  emptyData = ref({
    isEnroll: false,
    highlightTitle: '',
    highlightStartTime: '',
    highlightEndTime: '',
    highlightSubtitle: ''
  })
}
</script>

<template>
  <div class="shortping-highlight">
    <div class="highlight-top">
      <span>하이라이트</span>
      <button @click="addHighlight">추가</button>
    </div>
    <div class="highlight-body">
      <div v-for="(highlight, index) in props.data" :key="index" class="highlights">
        <div v-if="highlight.isEnroll">
          <saved-highlight
            :data="highlight"
            :index="index"
            @deleteData="
              (e) => {
                $emit('deleteData', e.index)
              }
            "
          />
        </div>
        <div v-else>
          <new-highlight
            :data="highlight"
            :index="index"
            @getTitle="(e) => (highlight.highlightTitle = e)"
            @getSubtitle="(e) => (highlight.highlightSubtitle = e)"
            @getStartTime="(e) => (highlight.highlightStartTime = e)"
            @getEndTime="(e) => (highlight.highlightEndTime = e)"
            @getData="
              (e) => {
                $emit('getData', e.index)
              }
            "
          />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.shortping-highlight {
  @include box(100%, 10%, white, 0, 0, 0.2rem);
  display: flex;
  flex-direction: column;
  align-items: center;

  .highlight-top {
    @include box(95%, 100%, white, 0, 0, 0.2rem);
    display: flex;
    justify-content: space-between;

    span {
      font-weight: bold;
    }

    button {
      width: 10%;
      border: none;
      background-color: $dark;
      color: white;
      border-radius: 0.2rem;
      font-size: 0.7rem;
      cursor: pointer;
    }
  }

  .highlight-body {
    @include box(100%, 32rem, null, 0, 0, 0);
    overflow: auto;
  }
  .highlight-body::-webkit-scrollbar {
    width: 0.3rem;
  }

  .highlight-body::-webkit-scrollbar-thumb {
    height: 20%;
    background: $main;
    border-radius: 10px;
  }

  .highlights {
    margin: 0.1rem;
    padding: 0.1rem;
  }
}
</style>
