package com.salesmanager.test.business.utils;

import com.salesmanager.core.business.utils.ProductUtils;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.order.orderproduct.OrderProductAttribute;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class ProductUtilsTest {

    @Test
    public void testBuildOrderProductDisplayName_noAttributes_returnsProductName() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName("Widget");

        String result = ProductUtils.buildOrderProductDisplayName(orderProduct);

        assertEquals("Widget", result);
    }

    @Test
    public void testBuildOrderProductDisplayName_singleAttribute() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName("T-Shirt");

        OrderProductAttribute attr = new OrderProductAttribute();
        attr.setProductAttributeName("Color");
        attr.setProductAttributeValueName("Red");

        Set<OrderProductAttribute> attributes = new HashSet<>();
        attributes.add(attr);
        orderProduct.setOrderAttributes(attributes);

        String result = ProductUtils.buildOrderProductDisplayName(orderProduct);

        assertEquals("T-Shirt [Color: Red]", result);
    }

    @Test
    public void testBuildOrderProductDisplayName_multipleAttributes_containsAllAttributes() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName("Jacket");

        OrderProductAttribute colorAttr = new OrderProductAttribute();
        colorAttr.setProductAttributeName("Color");
        colorAttr.setProductAttributeValueName("Blue");

        OrderProductAttribute sizeAttr = new OrderProductAttribute();
        sizeAttr.setProductAttributeName("Size");
        sizeAttr.setProductAttributeValueName("L");

        Set<OrderProductAttribute> attributes = new HashSet<>();
        attributes.add(colorAttr);
        attributes.add(sizeAttr);
        orderProduct.setOrderAttributes(attributes);

        String result = ProductUtils.buildOrderProductDisplayName(orderProduct);

        assertTrue("Result should start with product name", result.startsWith("Jacket"));
        assertTrue("Result should contain Color attribute", result.contains("Color: Blue"));
        assertTrue("Result should contain Size attribute", result.contains("Size: L"));
        assertTrue("Result should open bracket", result.contains("["));
        assertTrue("Result should close bracket", result.contains("]"));
    }

    @Test
    public void testBuildOrderProductDisplayName_emptyAttributes_returnsJustProductName() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName("Book");
        orderProduct.setOrderAttributes(new HashSet<>());

        String result = ProductUtils.buildOrderProductDisplayName(orderProduct);

        assertEquals("Book", result);
    }
}
