package br.com.gabreuw.finances.shared.pagination;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.request.PageInfoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class PageInfo implements SelfValidation<PageInfo> {

    @PositiveOrZero
    private final int pageNumber;
    @Positive
    private final int pageSize;
    private final Order[] orders;

    private final int pageInterval;
    private final int firstElementPosition;
    private final int lastElementPosition;

    private PageInfo(int pageNumber, int pageSize, Order[] orders, int pageInterval, int firstElementPosition, int lastElementPosition) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orders = orders == null ? new Order[]{} : orders;
        this.pageInterval = pageInterval;
        this.firstElementPosition = firstElementPosition;
        this.lastElementPosition = lastElementPosition;

        validate(this);
    }

    public static PageInfo create(int pageNumber, int pageSize, Order... orders) {
        int pageInterval = pageSize - 1;
        int lastElementPosition = (pageNumber + 1) * pageInterval + (pageNumber + 1);
        int firstElementPosition = lastElementPosition - pageInterval;

        return new PageInfo(pageNumber, pageSize, orders, pageInterval, firstElementPosition, lastElementPosition);
    }

    public static PageInfo from(PageInfoRequest pageInfoRequest) {
        if (pageInfoRequest == null) {
            return PageInfo.createDefault();
        }

        return PageInfo.create(
                pageInfoRequest.pageNumber(),
                pageInfoRequest.pageSize(),
                pageInfoRequest.orders()
        );
    }

    public static PageInfo createDefault() {
        return create(0, 20);
    }

    public boolean hasOrders() {
        return orders.length > 0;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(pageNumber, pageSize, Sort.by(mapToSpringOrders()));
    }

    private List<Sort.Order> mapToSpringOrders() {
        return Arrays.stream(this.orders)
                .map(Order::toSpringOrder)
                .collect(Collectors.toList());
    }

    public record Order(
            @NotNull
            Direction direction,
            @NotBlank
            String property
    ) implements SelfValidation<Order> {
        public Order(Direction direction, String property) {
            this.direction = direction;
            this.property = property;

            validate(this);
        }

        public Sort.Order toSpringOrder() {
            return switch (this.direction) {
                case ASC -> Sort.Order.asc(this.property);
                case DESC -> Sort.Order.desc(this.property);
            };
        }

    }

    public enum Direction {
        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Direction fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException("direção inválida. Valores aceitos: 'ASC' ou 'DESC'", e);
            }
        }

        public static Optional<Direction> fromOptionalString(String value) {
            try {
                return Optional.of(fromString(value));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
    }

}
