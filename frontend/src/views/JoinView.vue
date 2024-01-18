<template>
    <div class="container">
        <div class="header">회원가입</div>
        <!-- 프로필 사진 추가 필요-->
        <div class="content">
            <section class="short-type">
                <div class="label">이메일</div>
                <div class="input-area">
                    <input type="email" placeholder="메일을 입력하세요" class="short-input">
                    <input type="submit" value="중복확인" class="input-btn">
                </div>
            </section>
            <section class="long-type">
                <div class="label">이름</div>
                <input type="text" placeholder="이름을 입력하세요" class="long-input">
            </section>
            <section class="short-type">
                <div class="label">채널명</div>
                <div class="input-area">
                    <input type="text" placeholder="채널명을 입력하세요" class="short-input">
                    <input type="submit" value="중복확인" class="input-btn">
                </div>
            </section>
            <section class="long-type">
                <div class="label">비밀번호</div>
                <input type="password" placeholder="비밀번호를 입력하세요" class="long-input">
            </section>
            <section class="long-type">
                <div class="label">비밀번호 확인</div>
                <input type="password" placeholder="비밀번호를 한 번 더 입력하세요" class="long-input">
                <div style="margin: 2px; color: #ff495c; font-size: 10px;">
                    비밀번호 맞는지 확인하는 텍스트
                </div>
            </section>
            <section class="short-type">
                <div class="label">휴대폰</div>
                <div class="input-area">
                    <input id="phone-number" type="number" placeholder="휴대폰 번호를 입력하세요" class="short-input">
                    <input type="submit" value="번호받기" class="input-btn">
                </div>
            </section>
            <section class="short-type">
                <div class="label">인증번호 확인</div>
                <div class="input-area">
                    <input id="phone-number-check" type="number" placeholder="인증번호를 입력하세요" class="short-input">
                    <input type="submit" value="인증확인" class="input-btn">
                </div>
                <div style="margin: 2px; color: #ff495c; font-size: 10px;" >
                    인증번호 확인하는 텍스트
                </div>
            </section>
            <section class="short-type">
                <div class="label">주소</div>
                <div class="input-area">
                    <input type="text" placeholder="주소를 입력하세요" class="short-input">
                    <input type="submit" value="주소검색" class="input-btn">
                </div>
            </section>
            <section class="long-type">
                <div class="label">상세주소</div>
                <input type="text" placeholder="상세주소를 입력하세요" class="long-input">
            </section>
            <section class="long-type">
                <form>
                    <div class="label">성별</div>
                    <div class="radio">
                        <label><input type="radio" name="성별" value="남성">남성</label>
                        <label><input type="radio" name="성별" value="여성">여성</label>
                        <label><input type="radio" name="성별" value="선택안함">선택안함</label>
                    </div>
                </form>
            </section>
            <section class="long-type">
                <div class="label">생년월일</div>
                <input type="date" class="long-input">
            </section>
            <button @click="join" class="join">회원가입</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const join = () => {
    console.log("회원가입 후 메인페이지 가쟈");
}

</script>

<style lang="scss" scoped>
.container {
    @include box(500px, 100%, white, 10px, 5px, 5px);
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
        @include flex-box($direction: column, $justify: none);
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

        .short-type {
            @include box(90%, 10%, null, 0, 2px, 5px);
            .label {
                text-align: flex-start;
            }

            .input-area {
                @include box(100%, 60%, null, 0, 2px, null);
                @include flex-box($justify: space-between);

                margin-top: 5px;

                .short-input {
                    @include box(80%, 90%, whitesmoke, 0, null, 10px);
                    border: none;
                }
    
                .input-btn {
                    @include font-factory(13px, null);
                    text-align: flex-end;
                    border: 1px solid $dark;
                    background-color: white;
                    margin-left: 10px;
                    padding: 5px;
                }

            }
        }

    .radio {
        @include flex-box($justify: flex-start);
        @include font-factory(11px, null);

        label {
            margin: 10px 10px 0px 10px;
        }
    }


    .join {
        width: 90%;
        height: 43px;
        margin: 10px;
        border: none;
        background-color: $main;
        color: white;
        cursor: pointer;
    }

    }

}
</style>