package com.alt.readingisgood.statistics;

import com.alt.readingisgood.security.CurrentUser;
import com.alt.readingisgood.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("statistics")
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticService;

    @GetMapping("/getMonthlyStatistics")
    public ResponseEntity<List<StatisticDTO>> getMonthlyStatistics(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(statisticService.getMonthlyOrderStatistic(currentUser.getId()));
    }
}
