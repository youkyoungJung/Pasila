<script setup>
import { ref } from 'vue'
import ScriptSelectButton from '@/components/ready/ScriptSelectButton.vue'
import { getScript } from '@/components/api/ScriptAPI.js'

const emits = defineEmits(['script'])
const data = ref({
  style: '',
  userName: '재즈박',
  productName: '빅파이',
  description: ''
})

const tones = ref({ 구어체: String, 왕: String, enfp: String, 아재: String })
const accents = ref({ 친근한: String, 공손한: String, 신뢰있는: String, 발랄한: String })

const selectedTone = ref('')
const selectTone = (i) => {
  data.value.style = i
  selectedTone.value = i
}

const selectedAccent = ref('')
const selectAccent = (i) => {
  data.value.accent = i
  selectedAccent.value = i
}

const script = ref('')
const makeScript = () => {
  data.value.description = script.value.replaceAll('\n', '')
  console.log(data.value)
  getScript(data.value, sendData, failLog)
}

const sendData = (e) => {
  emits('script', e.data)
}

const failLog = () => {
  console.error()
}
</script>

<template>
  <div class="select-body">
    <div class="tones">
      <label for="tone">어조</label>
      <div class="buttons">
        <div v-for="(index, tone) in tones" :key="index">
          <script-select-button
            v-bind:word="tone"
            @click="selectTone(tone)"
            :class="[selectedTone == tone ? 'selected-btn' : 'btn']"
          />
        </div>
      </div>
    </div>
    <div class="accents">
      <label for="accent">분위기</label>
      <div class="buttons">
        <div v-for="(index, accent) in accents" :key="index">
          <script-select-button
            v-bind:word="accent"
            @click="selectAccent(accent)"
            :class="[selectedAccent == accent ? 'selected-btn' : 'btn']"
          />
        </div>
      </div>
    </div>
    <div class="self-input">
      <label for="input-area">상품 설명을 직접 입력해 보세요!</label>
      <span style="font-size: 9px">
        Tip! 상품 소개, 구성, 라이브 방송 혜택, 특징, 차별점(가격비교) 등 자세한 내용이 들어가면 더
        효과적인 스크립트가 만들어 집니다.</span
      >
      <textarea type="text" id="input-area" class="input-area" v-model="script"></textarea>
    </div>
    <div class="button-box">
      <button @click="makeScript" class="make-btn">생성하기</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.select-body {
  @include box(90%, 100%, white, 0, 0.3rem, 0.1rem);
  @include font-factory($fs-1, null);

  .tones,
  .accents {
    margin: 0.3rem;
    padding: 0.3rem;
  }

  .self-input {
    display: flex;
    flex-direction: column;
    margin: 0.3rem;
    padding: 0.5rem;

    .input-area {
      @include box(95%, 10rem, white, 0.3rem, 0.3rem, 0.3rem);
      border: 1px $main solid;
      border-radius: 0.3rem;
      overflow-y: scroll;
      overflow-x: hidden;
      resize: none;
      word-wrap: break-word;
      outline: none;
    }
    .input-area::-webkit-scrollbar {
      width: 0.3rem;
    }
    .input-area::-webkit-scrollbar-thumb {
      height: 30%;
      background: $main;
      border-radius: 10px;
    }
  }

  .buttons {
    @include flex-box($justify: space-between);

    .btn {
      @include box(7rem, 2rem, $light-gray, 0.3rem, 0.3rem, 0.3rem);
      border: none;
      cursor: pointer;
    }
    .selected-btn {
      @include box(7rem, 2rem, $main, 0.3rem, 0.3rem, 0.3rem);
      border: none;
      cursor: pointer;
      color: white;
    }
  }

  .button-box {
    display: flex;
    justify-content: center;

    .make-btn {
      @include box(8rem, 2rem, $main, 0.3rem, 0.1rem, 0.1rem);
      border: none;
      color: white;
      cursor: pointer;
    }
  }
}
</style>
