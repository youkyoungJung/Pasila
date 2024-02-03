<script setup>
defineProps({
  isActive: Boolean,
  iconName: String
})
</script>

<template>
  <button @click="$emit('click-btn')" :class="{ active: isActive }">
    <font-awesome-icon :icon="iconName" />
  </button>
</template>

<style lang="scss" scoped>
button {
  @include box(3rem, 3rem, $dark, 50%, 1rem, 0);
  @include flex-box();
  outline: none;
  border: none;
  cursor: pointer;

  svg {
    width: 1.3rem;
    height: 1.3rem;
    color: white;
  }

  &.active {
    background-color: $main;
  }
}
</style>
