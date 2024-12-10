import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderListRepo orderRepo = new OrderListRepo();
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2","brueste"));
        ShopService shopService = new ShopService(orderRepo,productRepo);
        List<String> products = new ArrayList<>();
       products.add("2");
       try{
        Order order=shopService.addOrder(products);
        System.out.println(order);
       List<Order>orders= shopService.getOrdersOfStatus(OrderStatus.PROCESSING);
        orders.stream().forEach(System.out::println);
    }catch (Exception e) {
       e.printStackTrace();}
    }
}
