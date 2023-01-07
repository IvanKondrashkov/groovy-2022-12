package ru.otus.homework

import com.google.common.collect.Range
import com.google.common.collect.ContiguousSet
import com.google.common.collect.DiscreteDomain

class HelloOtus {
    int start
    int end

    void setData(int s, int e) {
        ContiguousSet<Integer> set = ContiguousSet.create(
                Range.closed(s, e),
                DiscreteDomain.integers())

        start = set.first()
        end = set.last()
    }
}
