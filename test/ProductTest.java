/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author QinYing
 */
public class ProductTest {
    
    public ProductTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getPtype method, of class Product.
     */
    @Test
    public void testGetPtype() {
        System.out.println("getPtype");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getPtype();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPtype method, of class Product.
     */
    @Test
    public void testSetPtype() {
        System.out.println("setPtype");
        String ptype = "";
        Product instance = new Product();
        instance.setPtype(ptype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Product instance = new Product();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Product.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Product.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Product instance = new Product();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = new Product();
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        Product instance = new Product();
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class Product.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantity method, of class Product.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 0;
        Product instance = new Product();
        instance.setQuantity(quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Product.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Product o = null;
        Product instance = new Product();
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Product.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Product instance = new Product();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
