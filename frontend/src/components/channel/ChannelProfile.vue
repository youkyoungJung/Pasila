<script setup>
import { text } from '@fortawesome/fontawesome-svg-core'
import { ref } from 'vue'
const props = defineProps({
  member: Object
})
const textArea = ref(null)
const isFocus = ref(false)
let originValue = ''

const ControlSize = (e) => {
  textArea.value.style.height = 'fit-content'
  textArea.value.style.height = textArea.value.scrollHeight + 'px'
}

const FocusDesc = (e) => {
  isFocus.value = true
  originValue = e.target.value
}

const BlurDesc = (e) => {
  const next = e.relatedTarget
  console.log(next)
  if (next instanceof HTMLButtonElement) {
    next.click()
  } else {
    UndoDesc()
  }
}

const SaveDesc = () => {
  console.log('저장')
  isFocus.value = false
}

const UndoDesc = () => {
  props.member.description = originValue
  isFocus.value = false
}
</script>

<template>
  <div class="channel-profile">
    <div class="profile-img" :style="{ backgroundImage: `url(${member?.profile})` }"></div>
    <div>
      <div class="name-line">
        <span class="name">{{ member?.channel }}</span>
        <button class="profile-btn gray">내 정보 수정</button>
        <button class="profile-btn red">주문 관리</button>
      </div>
      <textarea
        class="desc"
        @input="ControlSize"
        @focus="FocusDesc"
        @blur="BlurDesc"
        ref="textArea"
        v-model="member.description"
      ></textarea>
      <template v-if="isFocus">
        <button @click="SaveDesc">확인</button>
        <button @click="UndoDesc">취소</button>
      </template>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.channel-profile {
  @include flex-box(flex-start, flex-start);
  .profile-img {
    @include box(10rem, 10rem, $gray, 50%, 0 2rem 0 0, 0);
    min-width: 10rem;
  }
  .name-line {
    @include flex-box(center, flex-start);
  }
  .name {
    @include font-factory($fs-4, bold);
    margin-right: 2rem;
  }
  .desc {
    @include font-factory($fs-3, 500);
    margin: 1rem 0;
    width: 50rem;
    min-width: 100%;
    height: fit-content;
    resize: none;
    border: none;
    // &::-webkit-scrollbar {
    //   background: none;
    // }

    // /* 스크롤바 막대 꾸미기 */
    // &::-webkit-scrollbar-thumb {
    //   display: none;
    // }
  }
  .name,
  .desc {
    display: block;
    white-space: pre-line;
  }

  .profile-btn {
    @include box(fit-content, fit-content, null, 10px, 0 0.5rem, 0.3rem 2rem);
    @include font-factory($fs-1, bold);
    cursor: pointer;
    &.gray {
      background-color: $gray;
      color: $dark;
      border: none;
      &:hover {
        background: black;
        color: white;
      }
    }

    &.red {
      background: none;
      color: $main;
      border: 2px solid $main;
      &:hover {
        background: $main;
        color: white;
      }
    }
  }
}
</style>
