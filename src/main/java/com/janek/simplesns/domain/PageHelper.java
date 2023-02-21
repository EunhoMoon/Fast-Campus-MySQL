package com.janek.simplesns.domain;

import org.springframework.data.domain.Sort;

import java.util.List;

import static org.springframework.data.domain.Sort.*;

public class PageHelper {

    public static String orderBy(Sort sort) {
        if (sort.isEmpty()) {
            return "id DESC";
        }

        List<Order> orders = sort.toList();
        List<String> orderBys = orders.stream()
                .map(o -> new StringBuilder(o.getProperty())
                        .append(" ")
                        .append(o.getDirection())
                        .toString())
                .toList();

        return String.join(", ", orderBys);
    }

}
