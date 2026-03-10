package com.keerthana.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

import com.keerthana.entity.Product;
import com.keerthana.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        sortProductsByPriceAscending(session);
        sortProductsByPriceDescending(session);
        getFirstThreeProducts(session);
        getNextThreeProducts(session);
        countTotalProducts(session);
        countProductsInStock(session);
        findMinMaxPrice(session);
        countProductsByDescription(session);
        filterProductsByPriceRange(session, 20.0, 100.0);
        findProductsStartingWith(session, "D");

        session.close();
        factory.close();
    }

    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\n=== Products Sorted by Price (Ascending) ===");

        for (Product product : products) {
            System.out.println(product);
        }
    }
    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";
        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\n=== Products Sorted by Price (Descending) ===");

        for (Product product : products) {
            System.out.println(product);
        }
    }
    public static void getFirstThreeProducts(Session session) {

        String hql = "FROM Product p";
        Query<Product> query = session.createQuery(hql, Product.class);

        query.setFirstResult(0);
        query.setMaxResults(3);

        List<Product> products = query.list();

        System.out.println("\n=== First 3 Products ===");

        for (Product product : products) {
            System.out.println(product);
        }
    }
    public static void getNextThreeProducts(Session session) {

        String hql = "FROM Product p";
        Query<Product> query = session.createQuery(hql, Product.class);

        query.setFirstResult(3);
        query.setMaxResults(3);

        List<Product> products = query.list();

        System.out.println("\n=== Next 3 Products ===");

        for (Product product : products) {
            System.out.println(product);
        }
    }
    public static void countTotalProducts(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();

        System.out.println("\n=== Total Products ===");
        System.out.println("Total Products: " + count);
    }
    public static void countProductsInStock(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();

        System.out.println("\n=== Products In Stock ===");
        System.out.println("Products In Stock: " + count);
    }
    public static void findMinMaxPrice(Session session) {

        String hql = "SELECT MIN(p.price), MAX(p.price) FROM Product p";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        Object[] result = query.uniqueResult();

        Double minPrice = (Double) result[0];
        Double maxPrice = (Double) result[1];

        System.out.println("\n=== Price Range ===");
        System.out.println("Minimum Price: $" + minPrice);
        System.out.println("Maximum Price: $" + maxPrice);
    }
    public static void countProductsByDescription(Session session) {

        String hql = "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> results = query.list();

        System.out.println("\n=== Products Grouped By Description ===");

        for (Object[] row : results) {
            String desc = (String) row[0];
            Long count = (Long) row[1];
            System.out.println(desc + " : " + count);
        }
    }
    public static void filterProductsByPriceRange(Session session, double min, double max) {

        String hql = "FROM Product p WHERE p.price BETWEEN :min AND :max";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("min", min);
        query.setParameter("max", max);

        List<Product> products = query.list();

        System.out.println("\n=== Products Between $" + min + " and $" + max + " ===");

        for (Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }
    public static void findProductsStartingWith(Session session, String prefix) {

        String hql = "FROM Product p WHERE p.name LIKE :pattern";
        Query<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("pattern", prefix + "%");

        List<Product> products = query.list();

        System.out.println("\n=== Products Starting With '" + prefix + "' ===");

        for (Product product : products) {
            System.out.println(product.getName());
        }
    }
}