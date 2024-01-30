<script setup>
import steps from '@/components/ready/Steps.vue'
import 'v-calendar/style.css'

import { ref, computed } from 'vue'

const step = ref('schedule')
const date = ref('')
const realDate = ref('')
const trimDate = () => {
  if (date.value != '') realDate.value = date.value.toLocaleDateString()
}
const hour = ref('00')
const minute = ref('00')
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="body">
      <div class="calendar">
        <v-date-picker v-model="date" @click="trimDate" />
      </div>
      <div class="schedule-time">
        <div class="vc-time-picker vc-attached">
          <div class="vc-time-select-group">
            <div class="vc-base-select">
              <select
                modelmodifiers="[object Object]"
                class="vc-time-select-hours vc-focus vc-align-right"
                v-model="hour"
              >
                <option value="0">00</option>
                <option value="1">01</option>
                <option value="2">02</option>
                <option value="3">03</option>
                <option value="4">04</option>
                <option value="5">05</option>
                <option value="6">06</option>
                <option value="7">07</option>
                <option value="8">08</option>
                <option value="9">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">1</option>
                <option value="14">2</option>
                <option value="15">3</option>
                <option value="16">4</option>
                <option value="17">5</option>
                <option value="18">6</option>
                <option value="19">7</option>
                <option value="20">8</option>
                <option value="21">9</option>
                <option value="22">10</option>
                <option value="23">11</option></select
              ><!----><!---->
            </div>
            <span class="vc-time-colon">:</span>
            <div class="vc-base-select">
              <select
                modelmodifiers="[object Object]"
                class="vc-time-select-minutes vc-focus vc-align-left"
                v-model="minute"
              >
                <option value="0">00</option>
                <option value="1">01</option>
                <option value="2">02</option>
                <option value="3">03</option>
                <option value="4">04</option>
                <option value="5">05</option>
                <option value="6">06</option>
                <option value="7">07</option>
                <option value="8">08</option>
                <option value="9">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
                <option value="32">32</option>
                <option value="33">33</option>
                <option value="34">34</option>
                <option value="35">35</option>
                <option value="36">36</option>
                <option value="37">37</option>
                <option value="38">38</option>
                <option value="39">39</option>
                <option value="40">40</option>
                <option value="41">41</option>
                <option value="42">42</option>
                <option value="43">43</option>
                <option value="44">44</option>
                <option value="45">45</option>
                <option value="46">46</option>
                <option value="47">47</option>
                <option value="48">48</option>
                <option value="49">49</option>
                <option value="50">50</option>
                <option value="51">51</option>
                <option value="52">52</option>
                <option value="53">53</option>
                <option value="54">54</option>
                <option value="55">55</option>
                <option value="56">56</option>
                <option value="57">57</option>
                <option value="58">58</option>
                <option value="59">59</option></select
              ><!----><!---->
            </div>
          </div>
        </div>
        <div>{{ realDate }}</div>
        <div>{{ hour }}:{{ minute }}</div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);

  .body {
    @include box(100%, 100%, red, 0, 0.3rem, 0.1rem);
    @include flex-box($align: flex-start, $justify: space-evenly);
    border-radius: 0.5rem;

    .calendar {
      @include box(100%, 100%, blue, 0, 0.1rem, 0.2rem);
      display: flex;
      justify-content: center;
    }
    .schedule-time {
      @include box(100%, 100%, pink, 0, 0.1rem, 0.2rem);
    }
  }
}
</style>
