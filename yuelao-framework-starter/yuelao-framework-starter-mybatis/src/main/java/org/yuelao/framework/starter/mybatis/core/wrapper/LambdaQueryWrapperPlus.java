package org.yuelao.framework.starter.mybatis.core.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.ObjectUtils;

import java.util.Collection;


public class LambdaQueryWrapperPlus<T> extends LambdaQueryWrapper<T> {


    @Override
    public LambdaQueryWrapperPlus<T> eq(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.eq(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.eq(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> ne(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.ne(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> ne(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.ne(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> le(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.le(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> le(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.le(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> ge(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.ge(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> ge(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.ge(condition, column, val);
    }


    @Override
    public LambdaQueryWrapperPlus<T> lt(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.lt(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> lt(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.lt(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> gt(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.gt(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> gt(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.gt(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> like(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.like(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> like(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.like(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> likeLeft(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.likeLeft(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> likeLeft(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.likeLeft(condition, column, val);
    }


    @Override
    public LambdaQueryWrapperPlus<T> likeRight(SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.likeRight(column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> likeRight(boolean condition, SFunction<T, ?> column, Object val) {
        return ObjectUtils.isEmpty(val) ? this : (LambdaQueryWrapperPlus<T>) super.likeRight(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperPlus<T> between(SFunction<T, ?> column, Object val1, Object val2) {
        if (!ObjectUtils.isEmpty(val1) && !ObjectUtils.isEmpty(val2)) {
            return (LambdaQueryWrapperPlus<T>) super.between(column, val1, val2);
        }
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> between(boolean condition, SFunction<T, ?> column, Object val1, Object val2) {
        if (!ObjectUtils.isEmpty(val1) && !ObjectUtils.isEmpty(val2)) {
            return (LambdaQueryWrapperPlus<T>) super.between(condition, column, val1, val2);
        }
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> notBetween(SFunction<T, ?> column, Object val1, Object val2) {
        if (!ObjectUtils.isEmpty(val1) && !ObjectUtils.isEmpty(val2)) {
            return (LambdaQueryWrapperPlus<T>) super.notBetween(column, val1, val2);
        }
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> notBetween(boolean condition, SFunction<T, ?> column, Object val1, Object val2) {
        if (!ObjectUtils.isEmpty(val1) && !ObjectUtils.isEmpty(val2)) {
            return (LambdaQueryWrapperPlus<T>) super.notBetween(condition, column, val1, val2);
        }
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(SFunction<T, ?> column, Object... values) {
        return ObjectUtils.isEmpty(values) ? this : (LambdaQueryWrapperPlus<T>) super.in(column, values);
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(SFunction<T, ?> column, Collection<?> coll) {
        return ObjectUtils.isEmpty(coll) ? this : (LambdaQueryWrapperPlus<T>) super.in(column, coll);
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(boolean condition, SFunction<T, ?> column, Object... values) {
        return ObjectUtils.isEmpty(values) ? this : (LambdaQueryWrapperPlus<T>) super.in(condition, column, values);
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(boolean condition, SFunction<T, ?> column, Collection<?> coll) {
        return ObjectUtils.isEmpty(coll) ? this : (LambdaQueryWrapperPlus<T>) super.in(condition, column, coll);
    }

    @Override
    public LambdaQueryWrapperPlus<T> notIn(SFunction<T, ?> column, Object... value) {
        return ObjectUtils.isEmpty(value) ? this : (LambdaQueryWrapperPlus<T>) super.in(column, value);
    }

    @Override
    public LambdaQueryWrapperPlus<T> notIn(SFunction<T, ?> column, Collection<?> coll) {
        return ObjectUtils.isEmpty(coll) ? this : (LambdaQueryWrapperPlus<T>) super.in(column, coll);
    }

    @Override
    public LambdaQueryWrapperPlus<T> notIn(boolean condition, SFunction<T, ?> column, Object... values) {
        return ObjectUtils.isEmpty(values) ? this : (LambdaQueryWrapperPlus<T>) super.in(condition, column, values);
    }

    @Override
    public LambdaQueryWrapperPlus<T> notIn(boolean condition, SFunction<T, ?> column, Collection<?> coll) {
        return ObjectUtils.isEmpty(coll) ? this : (LambdaQueryWrapperPlus<T>) super.in(condition, column, coll);
    }

}
