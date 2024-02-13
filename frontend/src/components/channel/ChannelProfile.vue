<script setup>
import { ref } from 'vue'
import router from '@/router'
import { updateChannelDescApi } from '@/components/api/MemberAPI'

const props = defineProps({
  member: Object,
  channelId: String
})
const textArea = ref(null)
const isFocus = ref(false)
const channelDesc = ref(props.member.description)
let originValue = ''

const controlSize = (e) => {
  textArea.value.style.height = 'fit-content'
  textArea.value.style.height = textArea.value.scrollHeight + 'px'
}

const focusDesc = (e) => {
  isFocus.value = true
  originValue = e.target.value
}

const blurDesc = (e) => {
  const next = e.relatedTarget
  if (next instanceof HTMLButtonElement) {
    next.click()
  } else {
    undoDesc()
  }
}

const saveDesc = async () => {
  if (await updateChannelDescApi(props.channelId, { description: channelDesc.value })) {
    isFocus.value = false
  } else {
    alert('서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해 주세요.')
  }
}

const undoDesc = () => {
  channelDesc.value = originValue
  isFocus.value = false
}
</script>

<template>
  <div class="channel-profile">
    <div class="profile-img" :style="{ backgroundImage: `url(${member?.profile})` }"></div>
    <div>
      <div class="name-line">
        <span class="name">{{ member?.channel }}</span>
        <!-- TODO: 마이페이지 경로 연결 필요 -->
        <button class="profile-btn gray">내 정보 수정</button>
        <button class="profile-btn red" @click="() => router.push(`${channelId}/orders`)">
          주문 관리
        </button>
      </div>
      <textarea
        class="desc"
        @input="controlSize"
        @focus="focusDesc"
        @blur="blurDesc"
        ref="textArea"
        v-model="channelDesc"
      ></textarea>
      <template v-if="isFocus">
        <button @click="saveDesc" class="save">수정</button>
        <button @click="undoDesc" class="undo">취소</button>
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
    background-size: cover;
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
  button {
    @include box(fit-content, 100%, none, 5px, 0 0.3rem, 0.5rem);
    border: none;
    outline: none;
    cursor: pointer;
  }

  .save {
    background-color: $main;
    color: white;
  }
}
</style>
