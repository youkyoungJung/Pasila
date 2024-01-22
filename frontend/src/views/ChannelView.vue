<script setup>
import ChannelProfile from '@/components/channel/ChannelProfile.vue'

const member = {
  name: '김가을',
  desc: '안녕하세요! 패션 인플루언서 김가을입니다.\n파시라 한정 세일 진행중이니, 라이브에 많은 관심 부탁드립니다!',
  profile: '/src/assets/img/test/gaeul.png'
}
</script>

<template>
  <div class="channel">
    <channel-profile :member="member"></channel-profile>
  </div>
</template>

<style lang="scss" scoped>
.channel {
  padding: 2rem 3rem;
}
</style>
