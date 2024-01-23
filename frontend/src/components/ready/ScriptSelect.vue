<script setup>
import { ref } from 'vue'
import ScriptSelectButton from '@/components/ready/ScriptSelectButton.vue'

const data = ref({
  tone: '',
  accent: '',
  input: ''
})

const tones = ref({ 사장님어: String, 왕어: String, 언니어: String, BJ: String })

const selectTone = (i) => {
  data.value.tone = i
}
</script>

<template>
  <div class="select-body">
    <div class="tones">
      <label for="tone">어조</label>
      <div class="tone-btn">
        <div v-for="(index, tone) in tones" :key="index">
          <script-select-button v-bind:word="tone" @click="selectTone(tone)" />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.select-body {
  @include box(90%, 100%, blue, 0, 0.3rem, 0.1rem);
  @include flex-box($align: flex-start, $direction: column);
  @include font-factory($fs-1, null);

  .tones {
    margin: 0.3rem;
    padding: 0.3rem;
    .tone-btn {
      @include flex-box();
    }
  }
}
</style>
