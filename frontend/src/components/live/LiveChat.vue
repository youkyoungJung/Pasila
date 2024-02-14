<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue'

defineProps({
  isCustomer: Boolean,
  chatmsg: String,
  chatList: Array
})

let isChatbot = ref(false)
const liveChat = ref(null)

// const ChatList = reactive([
//   {
//     type: 'user',
//     user: '김빨강',
//     content: '어 이거 카리나 인스타에서 봤어요!',
//     profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
//   },
//   {
//     type: 'user',
//     user: '김빨강',
//     content: '하 사야되나 이거',
//     profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
//   },
//   {
//     type: 'user',
//     user: '김빨강',
//     content: '!길이감',
//     profile: new URL('@/assets/img/test/rose.jpg', import.meta.url).href
//   },
//   {
//     type: 'user',
//     user: '이제니',
//     content: '기대됩니다ㅏ',
//     profile: new URL('@/assets/img/test/jenny.jpg', import.meta.url).href
//   },
//   {
//     type: 'user',
//     user: '한보라',
//     content: '저 이거 갖고싶었던거에요!!!',
//     profile: new URL('@/assets/img/test/karina.jpg', import.meta.url).href
//   },
//   {
//     type: 'chatbot',
//     user: '챗봇',
//     content:
//       '안녕하세요 고객님. 해당 제품은 2가지 길이 조정이 가능한 제품으로 무드에 따라 길이를 조절하실 수 있습니다. 자세한 내용은 상품설명을 참고하세요.',
//     profile: new URL('@/assets/img/test/robot-solid 2.png', import.meta.url).href
//   }
// ])

onMounted(() => {
  nextTick(() => {
    liveChat.value.scrollTop = 30 + liveChat.value.scrollHeight
  })
})

const clickChatbot = () => {
  isChatbot.value = !isChatbot.value
}
</script>

<template>
  <div class="live-chat-box">
    <div class="live-chat" ref="liveChat">
      <div class="chat-line" v-for="(item, index) in chatList" :key="index">
        <!-- <img :src="item.profile" :alt="item.memberId" class="profile" /> -->
        <div class="chat-box">
          <span class="name">{{ item.memberId }}</span>
          <div class="content">{{ item.massage }}</div>
        </div>
      </div>
    </div>
    <div class="chat-input">
      <div class="top" v-if="isChatbot">
        <span class="chatbot-info">
          챗봇에게 질문 중
          <font-awesome-icon icon="ellipsis" />
        </span>
      </div>
      <div class="bottom">
        <span class="chatbot" @click="clickChatbot">
          <font-awesome-icon icon="robot" class="icon" />
        </span>
        <input type="text" @input="$emit('changeMsg', $event.target.value)" :value="chatmsg" />
        <span class="plane" @click="$emit('sendMsg', isChatbot)">
          <font-awesome-icon icon="fa-solid fa-paper-plane" class="icon" />
        </span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-chat-box {
  @include box(null, calc(78vh - 5px), none, 20px, 0, 0);
  @include flex-box(center, space-between, column);
  border: 3px solid $main;

  .live-chat {
    padding: 1rem;
    overflow-y: scroll;
    box-shadow: 0px -4px 10px 0px rgba(0, 0, 0, 0.25) inset;
    border-radius: 20px 20px 0 0;

    -ms-overflow-style: none;
    .chat-line {
      @include flex-box(flex-start, flex-start);
      margin-bottom: 1rem;
      .profile {
        @include box(3rem, 3rem, none, 50px, 0, 0);
        object-fit: cover;
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
  padding: 0 0.5rem;

  .top {
    @include box(100%, calc(3vh - 0.4rem), white, 0 0 20px 20px, 0, 0.2rem 0);

    .chatbot-info {
      margin-right: 0.5rem;
      @include font-factory($fs-1, normal, $orange);
    }
  }
  .bottom {
    @include flex-box(center, space-between, row);
    @include box(100%, 10vh, white, 0 0 20px 20px, 0, 0);
  }

  .chatbot {
    @include box(2.5rem, 2.5rem, $orange, 50%, 0.5rem, 0);
    @include flex-box();
    color: white;
    cursor: pointer;
  }

  input {
    flex: 3;
    @include box(100%, null, $gray, 30px, 0, 0.5rem 1rem);
    @include font-factory($fs-1, 500);
    outline: none;
    border: none;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25) inset;
  }
  .plane {
    color: $dark;
    margin: 0 0.5rem;
  }
  .icon {
    flex: 1;
    height: 1.3rem;
    width: 2rem;
  }
}
</style>
