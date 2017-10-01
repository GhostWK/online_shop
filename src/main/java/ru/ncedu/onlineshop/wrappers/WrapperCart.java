package ru.ncedu.onlineshop.wrappers;

public class WrapperCart {
    private Long userId;

    public WrapperCart() {
    }

    public WrapperCart(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
