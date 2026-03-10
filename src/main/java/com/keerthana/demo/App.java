package com.keerthana.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.keerthana.loader.ProductDataLoader;
import com.keerthana.util.HibernateUtil;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        ProductDataLoader.loadSampleProducts(session);

        session.close();
        factory.close();
    }
}