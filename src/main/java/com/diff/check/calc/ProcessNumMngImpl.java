package com.diff.check.calc;

import com.diff.check.gathernum.Consts;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Сервис обработки чисел
 *
 * @author Lev
 * @version 1.01
 */
@Service
public class ProcessNumMngImpl implements ProcessNumMng, Consts {

    // хранилище чисел
    private final Set<Integer> map = new HashSet<>(10);

    // интерфейс ламба-функции
    interface Check {
        Optional<Integer> test(Set<Integer> lst);
    }

    /**
     * Получить число
     *
     * @param fn - лябмда-функция определяющая, что искать
     * @return - искомое число
     */
    private Optional<Integer> getSome(Check fn) {
        synchronized (map) {
            if (map.size() > 0) {
                return fn.test(map);
            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Получить максимальное число
     *
     * @return - результат поиска числа
     */
    @Override
    public Optional<Integer> getMax() {
        return getSome((t) -> Optional.of(Collections.max(t)));
    }

    /**
     * Получить минимальное число
     *
     * @return - результат поиска числа
     */
    @Override
    public Optional<Integer> getMin() {
        return getSome((t) -> Optional.of(Collections.min(t)));
    }

    /**
     * Получить среднее арифметическое число
     *
     * @return - результат поиска числа
     */
    @Override
    public Optional<Double> getAverage() {
        synchronized (map) {
            if (map.size() > 0) {
                Integer sum = 0;
                for (Integer i : map) {
                    sum += i;
                }
                return Optional.of(sum.doubleValue() / map.size());
            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Добавить число в хранилище
     *
     * @param str - строковое представление числа
     * @return - результат добавления
     */
    @Override
    public Result add(String str) {
        if (str != null) {
            try {
                int num = Integer.parseInt(str);
                synchronized (map) {
                    if (map.contains(num)) {
                        return Result.ALREADY_EXISTS;
                    } else {
                        map.add(num);
                    }
                }
            } catch (NumberFormatException e) {
                return Result.INCORRECT_FORMAT;
            }
            return Result.OK;
        }
        return Result.INCORRECT_FORMAT;
    }
}
