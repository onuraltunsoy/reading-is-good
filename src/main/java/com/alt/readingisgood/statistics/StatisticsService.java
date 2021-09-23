package com.alt.readingisgood.statistics;

import com.alt.readingisgood.book.Book;
import com.alt.readingisgood.book.BookService;
import com.alt.readingisgood.exception.AppException;
import com.alt.readingisgood.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final OrderService orderService;
    private final BookService bookService;

    public List<StatisticDTO> getMonthlyOrderStatistic(String currentUserId) {
        List<Order> customerOrders = orderService.findAllByStatusAndCustomerId(OrderStatus.PURCHASED.name(), currentUserId);

        Map<Integer, List<Order>> monthlyOrders = customerOrders.stream()
                .collect(groupingBy(d -> d.getCreatedDate().get(ChronoField.MONTH_OF_YEAR)));

        List<StatisticDTO> statisticData = new ArrayList<>();
        DecimalFormat amountFormat = new DecimalFormat("#.##");

        for (var monthKey : monthlyOrders.entrySet()) {
            String monthName = Month.of(monthKey.getKey()).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
            long totalBookCount = 0L;
            AtomicReference<Double> totalPurchasedAmount = new AtomicReference<>((double) 0);

            for (Order order : monthKey.getValue()) {
                for (OrderItem orderItem : order.getProducts()) {
                    totalBookCount += orderItem.getQuantity();
                    totalPurchasedAmount.updateAndGet(v -> v + orderItem.getBook().getPrice() * orderItem.getQuantity());

                }
            }
            statisticData.add(
                    StatisticDTO.builder()
                            .month(monthName)
                            .totalOrderCount((long) monthKey.getValue().size())
                            .totalBookCount(totalBookCount)
                            .totalAmount(Double.valueOf(amountFormat.format(totalPurchasedAmount.get())))
                            .build()
            );
        }
        return statisticData;

    }

//TODO • Will serve customer’s monthly order statistics
// Total Order count
//  Total count of purchased books
//  Total amount of all purchased orders
}
