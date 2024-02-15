<script setup>
import { ref, onMounted } from 'vue'
import { getLiveProductApi } from '@/components/api/OpenviduAPI'
import LiveDescription from '@/components/live/customer/LiveDescription.vue'

const props = defineProps(['liveId'])
const product = ref(null)

onMounted(async () => {
  product.value = await getLiveProductApi(props.liveId)
})
</script>

<template>
  <div class="session" v-if="product">
    <section class="col-1">
      <div class="camera-header">
        <div class="live-title">{{ product.title }}</div>
      </div>
      <div class="camera-box">
        <video :src="product.fullVideoUrl" autoplay controls />
      </div>
    </section>

    <section class="col-2">
      <live-description :product="product" :live-id="liveId" />
    </section>
  </div>
</template>

<style lang="scss" scoped>
.session {
  @include flex-box(flex-start, center);
  height: 78vh;
  overflow-y: auto;
  padding: 0rem 2rem;
  .col-1 {
    flex: 2;
    padding: 0 1rem 0 0;

    display: flex;
    gap: 1rem;
    flex-direction: column;
    justify-content: flex-start;

    .camera-header {
      @include flex-box(flex-end, space-between);
      width: 100%;

      .live-title {
        @include font-factory($fs-3, bold);
      }
    }
    .camera-box {
      flex: 3;

      @include box(100%, 50vh, $dark, 20px, 0, 0);
      @include flex-box(center, center, column);

      video {
        height: 100%;
        border-radius: 20px;
      }
    }
  }
  .col-2 {
    flex: 1;
    padding: 0 1rem 0 0;

    display: flex;
    flex-direction: column;
  }
}
</style>
