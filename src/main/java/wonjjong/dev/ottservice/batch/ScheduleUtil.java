package wonjjong.dev.ottservice.batch;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//@Service
//@NoArgsConstructor
@Slf4j
public class ScheduleUtil {
    @Scheduled(fixedRate = 1000)
    public void fixedRateSchedule() throws InterruptedException {
        log.info("fixedRate long" + LocalDate.now());
        Thread.sleep(1050);
    }

    @Scheduled(fixedDelay = 1000)
    public void fixedDelaySchedule() throws InterruptedException {
        log.info("fixedDelay long " + LocalDate.now());
        Thread.sleep(1050);
    }

    //application.yml에서 값 가져옴
    @Scheduled(fixedRateString = "${wonjjong.batch.fixedRate}")
    public void fixedRateStringSchedule() throws InterruptedException {
        log.info("fixedRateStringSchedule " + LocalDate.now());
        Thread.sleep(1050);
    }

    @Scheduled(fixedDelayString = "${wonjjong.batch.fixedDelay}")
    public void fixedDelayStringSchedule() throws InterruptedException {
        log.info("fixedDelayStringSchedule " + LocalDate.now());
        Thread.sleep(1050);
    }

    //    @Scheduled(cron = " ")
    /* Note that in this example,
       we're scheduling a task to be executed at 10:15 AM on the 15th day of every month.
       seecond, minute, hor, day of month, month, and day of week
     */

    @Scheduled(cron = "0 15 10 15 * ?")
    public void cronSchedule() throws InterruptedException {
        log.info("cronSchedule " + LocalDate.now());
        Thread.sleep(1000);
    }
}
