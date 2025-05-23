package com.smplatform.product_service.domain.coupon.entity;

import com.smplatform.product_service.domain.coupon.exception.InvalidEnumValueException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum IssueType {
    AUTO,
    CODE;
}
