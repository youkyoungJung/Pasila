<script setup>
const emit = defineEmits(['getHour', 'getMinute', 'getAM'])
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
        </select>
      </div>
      <div class="vc-base-select">
        <select
          modelmodifiers="[object Object]"
          class="vc-time-select-minutes vc-focus vc-align-left"
          @change="$emit('getMinute', $event.target.value)"
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
          <option value="59">59</option>
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
