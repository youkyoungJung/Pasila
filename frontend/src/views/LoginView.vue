<template>
    <div class="container">
        <div class="header">로그인</div>
        <div class="content">
            <section class="long-type">
                <div class="label">이메일</div>
                <input type="text" placeholder="이메일을 입력하세요" class="long-input">
            </section>
            <section class="long-type">
                <div class="label">비밀번호</div>
                <input type="password" placeholder="비밀번호를 입력하세요" class="long-input">
            </section>
            <section class="forget-pw">
                <!-- <span style="font-size: 8px;">비밀번호를 잊으셨나요?</span> -->
                <button @click="findPassword" class="find-pw">비밀번호를 잊으셨나요?</button>
            </section>
            <button @click="login" class="login">로그인</button>
            <button @click="join" class="join">회원가입</button>

            <!-- 소셜로그인 버튼 필요 -->
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { ref } from 'vue';
import JoinView from './JoinView.vue';

const user = ref({
    userEmail: '',
    userPassword: '',
});

const findPassword = function() {
    router.push("/findpw");
}

const login = () => {
    console.log("로그인 고고");
}

const join = () => {
    router.push("/join");
}

</script>

<style lang="scss" scoped>
.container {
    @include box(500px, 90%, white, 10px, 5px, 5px);
    border: 2px solid whitesmoke;
    @include flex-box($direction: column);
    @include drop-shadow;
    vertical-align: middle;
    margin-left: auto;
    margin-right: auto;
    
    .header {
        margin-top: 20px;
        @include font-factory($fs-3, bold);
    }
    .content {
        @include box(90%, 90%, none, 0, 10px, 10px);
        @include flex-box($direction: column, $justify: center);
        @include font-factory(13px, bold);
        
        .long-type {
            @include box(90%, 10%, null, 0, 2px, 5px);

            .label {
                text-align: flex-start;
            }

            .long-input {
                @include box(95%, 50%, whitesmoke, 0, null, 10px);
                border: none;
                margin-top: 5px;
            }
            
        }
        
        .login {
            @include box(80%, 43px, $main, 0, 2px, 5px);
            margin-top: 30px;
            border: none;
            color: white;
        }
        
        .forget-pw {
            @include box(90%, 10%, null, 0, 2px, 5px);
            @include flex-box();
            justify-content: flex-end;
            .find-pw {
                justify-content: flex-end;
                border: none;
                background-color: white;
                cursor: pointer;
                color: $main;
            }

        }
        .join {
            @include box(80%, 43px, white, 0, 2px, 5px);
            margin-top: 10px;
            border: 1px solid $dark;
            cursor: pointer;
        }
    }

}
</style>