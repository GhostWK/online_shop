package ru.ncedu.onlineshop.email;

import ru.ncedu.onlineshop.entities.EntityCart;
import ru.ncedu.onlineshop.entities.EntityChosenGoods;
import ru.ncedu.onlineshop.entities.EntityGoods;

import java.util.Set;

public class EmailPatterns {


    public static String getTableFromCart(EntityCart cart){

        Set<EntityChosenGoods> set = cart.getChosenGoods();
        double sum = 0;
        StringBuilder builder = new StringBuilder();

        builder.append("<table border=\"1\">")
                .append("<caption>Order number:"+ cart.getId() +"</caption>");
        appendRaw(builder, "Goods id", "Goods name", "Goods price", "Goods quantity", "Total price");

        for(EntityChosenGoods x : set){

            EntityGoods current = x.getGoods();

            double totalForGoods = x.getAmount() * current.getPrice();
            sum += totalForGoods;

            appendRaw(builder,
                    String.valueOf(current.getId()),
                    String.valueOf(current.getName()),
                    String.valueOf(current.getPrice()),
                    String.valueOf(x.getAmount()),
                    String.valueOf(totalForGoods));
        }
        appendRaw(builder,"---", "---","---","Total:", String.valueOf(sum));

        return builder.toString();
    }

    private static void appendRaw(StringBuilder builder, String ... values){

        builder.append("<tr>");
        for(String x : values){
            builder.append("<td>")
                    .append(x)
                    .append("</td>");
        }
        builder.append("</tr>");

    }

}
