package ru.ncedu.onlineshop.wrappers;

public class WrapperChosenGoods {

    private Long cartId;
    private Long goodsId;
    private Integer amount;

    public WrapperChosenGoods() {
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
