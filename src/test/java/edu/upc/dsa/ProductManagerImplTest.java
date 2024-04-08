package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductManagerImplTest{

    private ProductManagerImpl pm;
    private Order order;
    private Order processedOrder;

    @Before
    public void setUp() throws Exception {

        pm =ProductManagerImpl.getInstance();
        processedOrder = new Order();
        pm.addUser("1111", "Didac", "Arévalo");
        pm.addProduct("01", "Apple", 100.00);
        pm.addProduct("02", "Banana", 50.00);
        pm.addProduct("03", "Orange", 25.00);

        order = new Order("1111");
        order.addLP(2, "01");
        order.addLP(3, "02");
        order.addLP(4, "03");
        pm.addOrder(order);
    }
    @After
    public void tearDown() throws Exception {
        pm = null;
        order = null;
        processedOrder = null;
    }

    @Test
    public void realizarPedidoTest() throws Exception {

        // Obtener las órdenes del usuario y verificar si la orden se agregó correctamente
        List<Order> orders = pm.ordersByUser("1111");
        assertNotNull(orders); // Verificar si la lista de órdenes no es nula
        assertEquals(1, orders.size()); // Verificar si solo hay una orden para el usuario "1111"

        // Verificar si los detalles de la orden son correctos
        Order retrievedOrder = orders.get(0);
        assertEquals(order.getID(), retrievedOrder.getID()); // Verificar el ID del usuario en la orden
        assertEquals(order.getPedido(), retrievedOrder.getPedido()); // Verificar los productos en la orden
    }

    @Test
    public void servirPedidoTest() throws Exception {
        //Verificar si la orden procesada es la pedida.
        processedOrder = pm.processOrder();
        //Ver si no está vacío
        assertNotNull(processedOrder);
        //Ver si el ID de la order procesada es igual al del pedido
        assertEquals(order.getID(), processedOrder.getID());
        //Ver que el pedido ordenado es igual al pedido procesado
        assertEquals(order.getPedido(), processedOrder.getPedido());



    }
}