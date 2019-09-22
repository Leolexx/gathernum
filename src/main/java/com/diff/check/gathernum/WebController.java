package com.diff.check.gathernum;

import com.diff.check.calc.ProcessNumMng;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Контроллер REST запросов
 *
 * @author Lev
 * @version 1.02
 */
@RestController
@Slf4j
public class WebController implements Consts {

    // сервис обработки чисел
    private final ProcessNumMng processNumMng;

    public WebController(ProcessNumMng processNumMng) {
        this.processNumMng = processNumMng;
    }

    // константа подсказки
    private final String retUrl = "<br><br><a href=\"/\">вернуться</a>";

    /**
     * Добавить число в хранилище
     *
     * @param str - строковое представление числа
     * @return - результат добавления
     */
    @RequestMapping("/add/{str}")
    public String check(@PathVariable String str) {
        log.info("GOT /add/{}", str);
        final Result result = processNumMng.add(str);
        if (result.equals(Result.OK)) {
            return "Число " + str + " добавлено!" + retUrl;
        } else if (result.equals(Result.ALREADY_EXISTS)) {
            return "Число " + str + " уже было добавлено!" + retUrl;
        } else {
            return "Некорректное число - " + str + "!" + retUrl;
        }
    }

    /**
     * Получить минимальное число
     *
     * @return - результат поиска числа
     */
    @RequestMapping("/showMin")
    public String check() {
        log.info("GOT /min");
        Optional<Integer> opt = processNumMng.getMin();
        return opt.map(t -> "Наименьшее число:" + t + retUrl)
                .orElse("Числа не были внесены!" + retUrl);
    }

    /**
     * Получить максимальное число
     *
     * @return - результат поиска числа
     */
    @RequestMapping("/showMax")
    public String showMax() {
        log.info("GOT /max");
        Optional<Integer> opt = processNumMng.getMax();
        return opt.map(t -> "Наибольшее число:" + t + retUrl)
                .orElse("Числа не были внесены!" + retUrl);
    }

    /**
     * Получить среднее арифметическое число
     *
     * @return - результат поиска числа
     */
    @RequestMapping("/showAvg")
    public String showAvg() {
        log.info("GOT /avg");
        Optional<Double> opt = processNumMng.getAverage();
        return opt.map(t -> "Среднее арифметическое всех добавленных чисел:" + t + retUrl)
                .orElse("Числа не были внесены!" + retUrl);
    }

}
