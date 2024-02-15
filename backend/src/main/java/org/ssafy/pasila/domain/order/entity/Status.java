package org.ssafy.pasila.domain.order.entity;

import lombok.Getter;

/**
 * CANCEL : 주문 취소
 * ORDER : 주문 완료
 * DEPOSIT : 입금 완료
 * READY : 상품 준비
 * START : 배송 시작
 * COMP : 배송 완료
 * */
@Getter
public enum Status {

    CANCEL("주문 취소"),
    ORDER("주문 완료"),
    DEPOSIT("입금 완료"),
    READY("상품 준비"),
    START("배송 시작"),
    COMP("배송 완료");


    private final String description;

    Status(String description) {

        this.description = description;

    }

}