<template>
    <div class="container">
        <div class="header">비밀번호 찾기</div>
        <div class="content">
            <section class="long-type">
                    <div class="label">이메일</div>
                    <input type="text" placeholder="이메일을 입력하세요" class="long-input">
            </section>
            <button @click="find-pw" class="find-pw">이메일로 비밀번호 찾기</button>
            <button @click="login" class="login">로그인으로 돌아가기</button>

        </div>
    </div>
</template>

<script setup>
const login = () => {
    // router.push("/login");
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
        .find-pw {
            @include box(90%, 43px, $main, 0, 2px, 5px);
            margin-top: 30px;
            border: none;
            color: white;
            }

        }
        .login {
            @include box(90%, 43px, white, 0, 2px, 5px);
            margin-top: 10px;
            border: 1px solid $dark;
            cursor: pointer;
        }

    }


</style>