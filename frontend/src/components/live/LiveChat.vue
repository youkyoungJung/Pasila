<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue'

const ChatList = reactive([
  {
    type: 'user',
    user: '김빨강',
    content: '그래서 기능이 먼데;',
    profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
  },
  { type: 'user', user: '김빨강', content: '!기능', profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href },
  {
    type: 'user',
    user: '이제니',
    content: '기대됩니다ㅏ',
    profile: new URL('@/assets/img/test/jenny.jpg', import.meta.url).href
  },
  { type: 'user', user: '한보라', content: '!구성', profile: new URL('@/assets/img/test/karina.jpg', import.meta.url).href },
  {
    type: 'chatbot',
    user: '챗봇',
    content:
      '안녕하세요 고객님. 해당 제품의 기능은 수분 충전, 피부 열감 완화, 노화 방지 등이 있습니다. 더 다양한 설명은 상품 상세 페이지를 참고해주시기 바랍니다.',
    profile: new URL('@/assets/img/test/robot-solid 2.png', import.meta.url).href
  }
])

const liveChat = ref(null)

onMounted(() => {
  nextTick(() => {
    liveChat.value.scrollTop = 30 + liveChat.value.scrollHeight
  })
})
</script>

<template>
  <div class="live-chat-box">
    <div class="live-chat" ref="liveChat">
      <div class="chat-line" v-for="(item, index) in ChatList" :key="index">
        <img :src="item.profile" :alt="item.user" class="profile" />
        <div class="chat-box">
          <span class="name">{{ item.user }}</span>
          <div class="content">{{ item.content }}</div>
        </div>
      </div>
    </div>
    <div class="chat-input">
      <input type="text" />
      <font-awesome-icon icon="fa-solid fa-paper-plane" size="2x" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-chat-box {
  @include box(null, 100%, none, 20px, 1rem 0 1rem 0, 0);
  border: 3px solid $main;

  .live-chat {
    padding: 1rem;
    max-height: 26rem;
    overflow-y: scroll;

    -ms-overflow-style: none;
    .chat-line {
      @include flex-box(flex-start, flex-start);
      margin-bottom: 1rem;
      .profile {
        @include box(3rem, 3rem, none, 50px, 0, 0);
      }
      .chat-box {
        margin-left: 0.5rem;
        .name {
          @include font-factory($fs-1, 400);
          display: inline-block;
          margin-bottom: 0.3rem;
        }
        .content {
          @include box(fit-content, fit-content, $gray, 30px, 0, 0.5rem 1rem);
          @include font-factory($fs-2, 600);
        }
      }
    }
  }
}
.live-chat::-webkit-scrollbar {
  display: none;
}

.chat-input {
  @include flex-box(center, space-between, row);
  padding: 1rem;
  input {
    @include box(70%, null, $gray, 30px, 0, 1rem);
    @include font-factory($fs-1, 500);
    outline: none;
    border: none;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25) inset;
  }
}
</style>
