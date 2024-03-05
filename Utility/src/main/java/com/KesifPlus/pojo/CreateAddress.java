package com.KesifPlus.pojo;

public record CreateAddress(
        boolean isDefault,
        boolean isSellerAddress,
        String title,
        String address,
        String city,
        String postal,
        String state,
        boolean emptyBasket) {
}
