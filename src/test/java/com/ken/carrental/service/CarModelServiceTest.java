package com.ken.carrental.service;

import com.ken.carrental.DBRollbackBase;
import com.ken.carrental.entity.CarModel;
import com.ken.carrental.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CarModelServiceTest extends DBRollbackBase {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private OrderService orderService;

    @Test
    public void testFindAvailableCarModel() throws ParseException {
        Date start = FORMAT.parse("2022-06-21 12:00");
        Date end = FORMAT.parse("2022-06-21 14:00");
        List<CarModel> list = carModelService.findAvailableCarModel(start, end);
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testFindAvailableCarModel2() throws ParseException {
        Date start = FORMAT.parse("2022-06-22 12:00");
        Date end = FORMAT.parse("2022-06-22 14:00");
        orderService.create(1, start, end);
        orderService.create(1, start, end);
        List<CarModel> list = carModelService.findAvailableCarModel(start, end);
        Assertions.assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void testFindAvailableCarModel3() throws ParseException {
        Date start = FORMAT.parse("2022-06-23 12:00");
        Date end = FORMAT.parse("2022-06-23 14:00");
        orderService.create(1, start, end);
        orderService.create(1, start, end);
        orderService.create(2, start, end);
        Order order = orderService.create(2, start, end);
        List<CarModel> list = carModelService.findAvailableCarModel(start, end);
        Assertions.assertThat(list.size()).isEqualTo(0);
        Assertions.assertThat(order).isNotNull();
        orderService.cancel(order.getId());
        list = carModelService.findAvailableCarModel(start, end);
        Assertions.assertThat(list.size()).isEqualTo(1);
    }
}