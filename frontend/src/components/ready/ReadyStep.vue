<script setup>
const props = defineProps({
  data: String,
  numbers: Number,
  names: String
})
</script>

<template>
  <div class="steps">
    <div :class="[props.data == 'register' ? 'icon-wrapper' : 'no-icon-wrapper']">
      <font-awesome-icon
        icon="fa-solid fa-1"
        :class="[props.data == 'register' ? 'icon-number' : 'no-icon-number']"
      />
    </div>
    <span :class="[props.data == 'register' ? 'step-text' : 'no-step-text']">상품 등록</span>
    <font-awesome-icon icon="fa-solid fa-minus" class="icon-dash" size="2x" />
  </div>
  <div class="steps">
    <div :class="[props.data == 'script' ? 'icon-wrapper' : 'no-icon-wrapper']">
      <font-awesome-icon
        icon="fa-solid fa-2"
        :class="[props.data == 'script' ? 'icon-number' : 'no-icon-number']"
      />
    </div>
    <span :class="[props.data == 'script' ? 'step-text' : 'no-step-text']">대본 생성</span>
    <font-awesome-icon icon="fa-solid fa-minus" class="icon-dash" size="2x" />
  </div>
  <div class="steps">
    <div :class="[props.data == 'chatbot' ? 'icon-wrapper' : 'no-icon-wrapper']">
      <font-awesome-icon
        icon="fa-solid fa-3"
        :class="[props.data == 'chatbot' ? 'icon-number' : 'no-icon-number']"
      />
    </div>
    <span :class="[props.data == 'chatbot' ? 'step-text' : 'no-step-text']">챗봇 생성</span>
    <font-awesome-icon icon="fa-solid fa-minus" class="icon-dash" size="2x" />
  </div>
  <div class="steps">
    <div :class="[props.data == 'schedule' ? 'icon-wrapper' : 'no-icon-wrapper']">
      <font-awesome-icon
        icon="fa-solid fa-4"
        :class="[props.data == 'schedule' ? 'icon-number' : 'no-icon-number']"
      />
    </div>
    <span :class="[props.data == 'schedule' ? 'step-text' : 'no-step-text']">라이브 예약</span>
  </div>
</template>

<style lang="scss" scoped>
.step-container {
  @include flex-box($justify: flex-start);
  @include box(100%, null, null, 0, 2px, 5px);

  .steps {
    @include flex-box();
  }
  .icon-wrapper {
    @include box(40px, 40px, null, 50%, 5px, 2px);
    @include flex-box();
    border: 1px solid $main;
  }
  .icon-number {
    color: $main;
    size: 2x;
  }

  .no-icon-number {
    color: $gray;
    size: 2x;
  }

  .no-icon-wrapper {
    @include box(40px, 40px, null, 50%, 5px, 2px);
    @include flex-box();
    border: 1px solid $gray;
  }
  .icon-dash {
    width: 50px;
  }
  .step-text {
    @include font-factory($fs-1, bold);
    margin: 5px;
  }

  .no-step-text {
    @include font-factory($fs-1, null, $gray);
    margin: 5px;
  }
}
</style>
