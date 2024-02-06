<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue'

const ChatList = reactive([
  {
    type: 'user',
    user: '김빨강',
    content: '어 이거 카리나 인스타에서 봤어요!',
    profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
  },
  {
    type: 'user',
    user: '김빨강',
    content: '하 사야되나 이거',
    profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
  },
  {
    type: 'user',
    user: '김빨강',
    content: '!길이감',
    profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
  },
  {
    type: 'user',
    user: '이제니',
    content: '기대됩니다ㅏ',
    profile: new URL('@/assets/img/test/jenny.jpg', import.meta.url).href
  },
  {
    type: 'user',
    user: '한보라',
    content: '저 이거 갖고싶었던거에요!!!',
    profile: new URL('@/assets/img/test/karina.jpg', import.meta.url).href
  },
  {
    type: 'chatbot',
    user: '챗봇',
    content:
      '안녕하세요 고객님. 해당 제품은 2가지 길이 조정이 가능한 제품으로 무드에 따라 길이를 조절하실 수 있습니다. 자세한 내용은 상품설명을 참고하세요.',
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
      <span>
        <font-awesome-icon icon="fa-solid fa-paper-plane" class="send-icon" />
      </span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-chat-box {
  @include box(null, calc(78vh - 5px), none, 20px, 0, 0);
  border: 3px solid $main;
  position: relative;

  .live-chat {
    padding: 1rem;
    height: 63vh;
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
          @include font-factory($fs-1, 600);
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
  @include box(calc(100% - 2rem), 10vh, white, 0 0 20px 20px, 0, 0 1rem);
  position: absolute;
  bottom: 0;

  input {
    @include box(75%, null, $gray, 30px, 0, 0.5rem 1rem);
    @include font-factory($fs-1, 500);
    outline: none;
    border: none;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25) inset;
  }
  .send-icon {
    height: 1.3rem;
    width: 2rem;
  }
}
</style>
