package edu.upc.dsa;

import java.util.*;

import edu.upc.dsa.Queue.EmptyQueueException;
import edu.upc.dsa.Queue.FullQueueException;
import edu.upc.dsa.Queue.QueueImpl;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;


public class ProductManagerImpl implements ProductManager {
    private static ProductManagerImpl instance;
    //ATRIBUTOS SIEMPRE EN MINUSCULA
    protected List<Product> l;
    protected HashMap<String, User> hm;
    protected QueueImpl<Order> q;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private ProductManagerImpl(){
        this.l =new ArrayList<>();
        this.hm =new HashMap<>();
        this.q =new QueueImpl<>(20);
    }
    public static ProductManagerImpl getInstance(){
        if(instance==null){
            instance=new ProductManagerImpl();
        }
        return instance;
    }

    @Override
    public List<Product> productsByPrice() {
        Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPrice);
        l.sort(priceComparator);
        logger.info("Ordered List by Price" + l);

        return l;
    }

    @Override
    public List<Product> productsBySales() {
        Comparator<Product> salesComparator = Comparator.comparingDouble(Product::getSales);
        l.sort(salesComparator.reversed()); //Queremos que nos devuelva la lista de ordenada de mayor a menor.
        logger.info("Ordered List by Sales" + l);
        return l;
    }

    @Override
    public void addProduct(String productId, String name, double price) {
        logger.info("Adding product " + productId + " with name " + name + " and price " + price);
        Product p = new Product(productId, name, price);
        l.add(p);
        logger.info("Added product");
    }

    @Override
    public void addOrder(Order order) {
        logger.info("Adding order " + order);
        try{
            q.push(order);
            User user = hm.get(order.getID());
            if(user != null){
                user.addComanda(order);

            }
            else{
                //logger.warn("User not found with ID: " + order.getID());
                throw new IllegalArgumentException("Usuario no encontrado para la orden con ID: " + order.getID());
            }
        }
        catch(FullQueueException fullQueueException){
            //logger.warn("Full Queue, can't add order");
            fullQueueException.printStackTrace();
        }
    }
    @Override
    public void addUser(String id, String name, String surname){
        logger.info("Adding user " + id);
        User u = new User(id,name,surname);
        hm.put(id, u);
        logger.info("Added user");
    }


    @Override
    public Order processOrder() {
        Order processedOrder = new Order();
        try{
            processedOrder = q.pop();
            logger.info("Processing order " + processedOrder);
            HashMap<String, Integer> productsInOrder = processedOrder.getPedido();

            for(Map.Entry<String, Integer> entry : productsInOrder.entrySet()){
                String productId = entry.getKey();
                Integer quantity = entry.getValue();
                updateProductSales(productId, quantity);
            }
        }
        catch (EmptyQueueException emptyQueueException){
            //logger.warn("The queue is empty");
            emptyQueueException.printStackTrace();
        }
        logger.info("Processed order");

        return processedOrder;

    }
    @Override
    public List<Order> ordersByUser(String userId){
        logger.info("Orders by user " + userId);
        User user = hm.get(userId);
        if(user != null){
            logger.info("Orders by user: " + user.getOrderList());
            return user.getOrderList();
        }
        else {
            logger.warn("User " + userId + " not found");
            return null;
        }
    }

    public void updateProductSales(String productId, int quantity){
        logger.info("Updating product sales " + productId + " with quantity " + quantity);
        Product product = new Product();
        for(Product p : l){
            if(p.getID().equals(productId)){
                product = p;
                break;
            }
        }
        logger.info("Sales updated");
        product.incrementSales(quantity);
    }

    @Override
    public int getProductsSize() {
        return l.size();
    }
}