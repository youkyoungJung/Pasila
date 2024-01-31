<script setup>
const emit = defineEmits(['getHour', 'getMinute', 'getAM'])
const props = defineProps(['APM', 'hour', 'minute'])
</script>

<template>
  <div class="select-time">
    <div class="vc-time-select-group">
      <div class="vc-base-select">
        <select @change="$emit('getAM', $event.target.value)" class="select-am">
          <option value="오전">오전</option>
          <option value="오후">오후</option>
        </select>
        <select
          modelmodifiers="[object Object]"
          class="vc-time-select-hours vc-focus vc-align-right"
          @change="$emit('getHour', $event.target.value)"
        >
          <option v-for="i in 12" :key="i" :value="i">{{ i }}</option>
        </select>
      </div>
      <div class="vc-base-select">
        <select
          modelmodifiers="[object Object]"
          class="vc-time-select-minutes vc-focus vc-align-left"
          @change="$emit('getMinute', $event.target.value)"
        >
          <option v-for="i in 60" :key="i" :value="i - 1">{{ i - 1 }}</option>
        </select>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.select-time {
  @include box(100%, 7rem, white, 0.625rem, 0, 0);
  .vc-time-select-group {
    @include box(100%, 100%, white, 0, 0, 0);
    @include flex-box();
    .select-am {
      width: 6rem;
      height: 4rem;
      flex-shrink: 0;
      border: none;
      border-radius: 0.625rem;
      background-color: #d9d9d9;
      outline: none;
      margin-right: 1rem;

      text-align: center;
      font-family: 'Noto Sans KR';
      font-size: 1.5rem;
      font-style: normal;
      font-weight: 500;
      line-height: normal;
    }
    select {
      width: 9rem;
      height: 4rem;
      flex-shrink: 0;
      border: none;
      border-radius: 0.625rem;
      background-color: #d9d9d9;
      margin-left: 0.5rem;

      text-align: center;
      font-family: 'Noto Sans KR';
      font-size: 1.5rem;
      font-style: normal;
      font-weight: 500;
      line-height: normal;
    }
  }
}
</style>
