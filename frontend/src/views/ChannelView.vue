<script setup>
import { ref, onMounted } from 'vue'
import { getChannelApi } from '@/components/api/MemberAPI'
import ChannelProfile from '@/components/channel/ChannelProfile.vue'
import ToggleButton from '@/components/common/ToggleButton.vue'

const props = defineProps(['channelId'])

let member = ref(null)

onMounted(async () => {
  const data = await getChannelApi(props.channelId)
  member.value = data
})
</script>

<template>
  <div class="channel">
    <channel-profile v-if="member" :member="member" />
    <toggle-button />
  </div>
</template>

<style lang="scss" scoped>
.channel {
  padding: 2rem 3rem;
}
</style>
