import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@RequiredArgsConstructor
//@AllArgsConstructor
public class ShopService {

 private ProductRepo productRepo = new ProductRepo();
 private OrderRepo orderRepo = new OrderMapRepo();

    public ShopService(OrderListRepo orderRepo, ProductRepo productRepo) {
    this.productRepo=productRepo;
    this.orderRepo=orderRepo;

    }


    public Order addOrder(List<String> productIds)throws ProductDoesNotExistExeption {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                throw new ProductDoesNotExistExeption();
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products);

        return orderRepo.addOrder(newOrder);
    }
    public List<Order> getOrdersOfStatus(OrderStatus status) {
        List<Order> orders = new ArrayList<>();
       orders=orderRepo.getOrders().stream().map(e->e.orderStatus()==status?e:null).collect(Collectors.toList());
       return orders;
    }
    public void updateOrder(String id,OrderStatus status) throws ProductDoesNotExistExeption {
       Order order = orderRepo.getOrderById(id);
       if (order!=null) {

           order= order.withOrderStatus(status);
           orderRepo.removeOrder(id);

            orderRepo.addOrder(order);
       }



    }
}
