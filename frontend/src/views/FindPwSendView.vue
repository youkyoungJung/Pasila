<template>
    <div class="container">
        <div class="header">비밀번호 찾기</div>
        <div class="content">
            <div class="text-area">
                이메일로 비밀번호 재설정 URL이 전송되었습니다. <br>
                URL에서 비밀번호 변경 후 로그인해주세요.
            </div>
            <button @click="goLogin" class="login">로그인하기</button>
            <button @click="goHome" class="home">홈으로 돌아가기</button>

        </div>
    </div>
</template>

<script setup>
import router from '@/router'

const goLogin = () => {
    router.push('/login');
}

const goHome = () => {
    router.push("/");

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
        
        .text-area{
            @include box(90%, 30%, null, 0, 20px, 5px);
            @include flex-box();
            text-align: center;
        }

        }
        .login {
            @include box(90%, 43px, white, 0, 2px, 5px);
            margin-top: 10px;
            border: 1px solid $dark;
            cursor: pointer;
        }
        .home {
            @include box(90%, 43px, $main, 0, 2px, 5px);
            border: none;
            color: white;
            cursor: pointer;
            }

        }

    


</style>