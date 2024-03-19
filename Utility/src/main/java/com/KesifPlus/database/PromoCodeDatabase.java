package com.KesifPlus.database;

import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PromoCodeDatabase extends DatabaseUtilities {
    public void createPromocode(String promocodeName, String startDate, String endDate, String numberOfUsers, String discount, String discountType) {
        executeUpdateStatement(
                "INSERT INTO `promo_code` (`id`, `code`, `starts_at`, `ends_at`, `number_of_users`, `discount`, `discount_type`) " +
                        "VALUES (" +
                        "NULL, '" +
                        promocodeName + "', '" +
                        startDate + "', '" +
                        endDate + "', '" +
                        numberOfUsers + "', '" +
                        discount + "', '" +
                        discountType + "');");
    }

    @SneakyThrows
    public List<AllPromoCode> readPromoCode() {
        resultSet = executeQueryStatement("select * from promo_code");
        List<AllPromoCode> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new AllPromoCode(
                    resultSet.getInt("id"),
                    resultSet.getString("code"),
                    resultSet.getString("discount")
            ));
        }
        return list;
    }

    public void deletePromocode(String promoCode){
        updatePreparedStatement("delete from `promo_code` where `code`= ? ",promoCode);
    }


    public String getTimeForPromocode(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, amount);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    public record AllPromoCode(int id, String code, String discount) {
    }
}
