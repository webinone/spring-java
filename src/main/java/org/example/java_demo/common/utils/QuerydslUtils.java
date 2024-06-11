package org.example.java_demo.common.utils;

import static org.springframework.util.StringUtils.hasText;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.StringTemplate;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

public class QuerydslUtils {
  public static int getEndOffset(List fetch, Pageable pageable) {
    int start = (int) pageable.getOffset();

    return (start + pageable.getPageSize()) > fetch.size() ? fetch.size() : (start + pageable.getPageSize());
  }

  public static BooleanExpression eq(StringPath field, String value) {
    return hasText(value) ? field.eq(value) : null;
  }

  public static BooleanExpression eq(NumberPath<Long> field, Long value) {
    return value == null ? null : field.eq(value);
  }

  public static BooleanExpression eq(BooleanPath field, Boolean value) {
    return value != null ? field.eq(value) : null;
  }

  public static BooleanExpression in(StringPath field, List<String> value) {
    return !CollectionUtils.isEmpty(value) ? field.in(value) : null;
  }

  public static BooleanExpression eq(EnumPath field, Object value) {
    return value != null ? field.eq(value) : null;
  }

  public static BooleanExpression goe(StringPath field, String value) {
    return value != null ? field.goe(value) : null;
  }

  public static BooleanExpression loe(StringPath field, String value) {
    return value != null ? field.loe(value) : null;
  }

  public static BooleanExpression goe(DateTimePath<LocalDateTime> field, LocalDateTime value) {
    return value != null ? field.goe(value) : null;
  }

  public static BooleanExpression loe(DateTimePath<LocalDateTime> field, LocalDateTime value) {
    return value != null ? field.loe(value) : null;
  }

  public static StringTemplate barogoDate(DateTimePath<Instant> field) {
    return Expressions.stringTemplate("to_char({0} + interval '2 hour', 'yyyymmdd')", field);
  }

  public static List<OrderSpecifier> getOrderSpecifier(Sort sort, EntityPath... entitys) {
    List<OrderSpecifier> orders = new ArrayList<>();
    List<EntityPath> paths = Arrays.stream(entitys).collect(Collectors.toList());

    for (Sort.Order order : sort) {
      Order direction = order.isAscending() ? Order.ASC : Order.DESC;
      String fieldName = order.getProperty().matches(".*\\..*") ? order.getProperty().split("\\.")[1] : order.getProperty();

      for (EntityPath path : paths) {
        boolean isMatch = isEntityMatch(order, path);
        if (!isMatch) {
          continue;
        }

        Field field = Arrays.stream(path.getClass().getDeclaredFields())
            .filter(it -> it.getName().equals(fieldName))
            .findFirst()
            .orElse(null);

        if (field != null) {
          PathBuilder orderByExpression = new PathBuilder<>(path.getType(), path.getMetadata());
          orders.add(new OrderSpecifier<>(direction, orderByExpression.get(fieldName)));
          break;
        }
      }
    }

    return orders;
  }

  public static boolean isEntityMatch(Sort.Order order, EntityPath path) {
    String field = order.getProperty();

    boolean isExistsPrefix = field.matches(".*\\..*");

    if (isExistsPrefix) {
      String pathEntityName = path.getMetadata().getName();
      String fieldEntityName = field.split("\\.")[0];
      if (!pathEntityName.equals(fieldEntityName)) {
        return false;
      }
    }

    return true;
  }

  public static BooleanExpression like(StringPath field, String value) {
    return hasText(value) ? field.contains(value) : null;
  }

}
