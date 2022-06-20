package com.ken.carrental.service;

import com.ken.carrental.DBRollbackBase;
import com.ken.carrental.common.BusinessException;
import com.ken.carrental.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OrderServiceTest extends DBRollbackBase {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreate() throws ParseException {
        Date start = FORMAT.parse("2022-06-21 12:00");
        Date end = FORMAT.parse("2022-06-21 14:00");

        Order order1 = orderService.create(1, start, end);
        Order order2 = orderService.create(1, start, end);

        assertThat(order1).isNotNull();
        assertThat(order2).isNotNull();

        assertThatThrownBy(() -> {
            orderService.create(1, start, end);
        }).isInstanceOf(BusinessException.class);
    }

}