<script setup>
import OvVideo from '@/components/live/openvidu/OvVideo.vue'
import { computed } from 'vue'

const props = defineProps({
  streamManager: Object
})

const clientData = computed(() => {
  const { clientData } = getConnectionData()
  return clientData
})

const getConnectionData = () => {
  const { connection } = props.streamManager.stream
  return JSON.parse(connection.data)
}
</script>

<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <div>
      <p>{{ clientData }}</p>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
