import lombok.With;

import java.time.Instant;
import java.util.List;
@With
public record Order(
        String id,
        List<Product> products,
        OrderStatus orderStatus,
        Instant creation
) {

    Order(String id, List<Product> products, OrderStatus orderStatus) {
        this(id, products, orderStatus, Instant.now() );
    }
    Order(String id,List<Product> products) {
        this(id,products,OrderStatus.PROCESSING,Instant.now());
    }
}
