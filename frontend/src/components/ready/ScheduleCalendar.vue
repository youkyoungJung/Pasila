<script setup>
import { ref } from 'vue'

const emit = defineEmits(['getDate', 'getHour', 'getMinute'])

const date = ref(new Date())
</script>

<template>
  <div class="calendar">
    <v-date-picker
      expanded
      transparent
      borderless
      :min-date="new Date()"
      min-hight="3rem"
      color="red"
      v-model="date"
      @click="$emit('getDate', date)"
    >
    </v-date-picker>
  </div>
</template>

<style lang="scss" scoped>
.calendar {
  @include box(100%, 100%, white, 0, 0, 0);
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 1rem;
}
</style>
