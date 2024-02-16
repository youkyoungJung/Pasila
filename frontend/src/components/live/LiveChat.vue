<script setup>
import { ref, onMounted, onUpdated, computed } from 'vue'

defineProps({
  isCustomer: Boolean,
  isChatbot: Boolean,
  chatmsg: String,
  chatList: Array
})

const liveChat = ref(null)

onMounted(() => {
  scrollBottom()
})

onUpdated(() => {
  scrollBottom()
})

const scrollBottom = () => {
  liveChat.value.scrollTop = liveChat.value.scrollHeight
}
</script>

<template>
  <div class="live-chat-box">
    <div class="live-chat" ref="liveChat">
      <div class="chat-line" v-for="(item, index) in chatList" :key="index">
        <!-- <span class="profile" :style="{ backgroundImage: item.profile }"></span> -->
        <div class="chat-box">
          <span class="name">{{ item.name }}</span>
          <div class="content">{{ item.message }}</div>
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
        <span class="chatbot" @click="$emit('clickChatbot')" v-if="isCustomer">
          <font-awesome-icon icon="robot" class="icon" />
        </span>
        <input
          type="text"
          @input="$emit('changeMsg', $event.target.value)"
          :value="chatmsg"
          @keyup.enter="$emit('send')"
        />
        <span class="plane" @click="$emit('sendMsg')">
          <font-awesome-icon icon="fa-solid fa-paper-plane" class="icon" />
        </span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-chat-box {
  @include box(null, calc(78vh - 6px), none, 20px, 0, 0);
  @include flex-box(center, space-between, column);
  border: 3px solid $main;

  .live-chat {
    width: calc(100% - 2rem);
    height: 100%;
    padding: 1rem;
    overflow-y: scroll;
    box-shadow: 0px -4px 10px 0px rgba(0, 0, 0, 0.25) inset;
    border-radius: 20px 20px 0 0;

    -ms-overflow-style: none;
    .chat-line {
      @include flex-box(flex-start, flex-start);
      margin-bottom: 1rem;
      .profile {
        @include box(3rem, 3rem, $yellow, 50%, 0, 0);
        background-size: cover;
        display: inline-block;
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
          word-break: break-all;
        }
      }
    }
  }
}
.live-chat::-webkit-scrollbar {
  display: none;
}

.chat-input {
  width: 90%;
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
