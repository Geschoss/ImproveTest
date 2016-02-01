package ru.priceList.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import ru.priceList.models.Product;
import ru.priceList.service.JPA.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;


public class HibernateStorageTest {


    @Test
    public void testCreate() throws Exception {
       // ObjectMapper mapper = new ObjectMapper();
      /*  SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        String parCategoryName = "Printer";
        String parProductName = "Printer";
        BigDecimal parPriceFrom = new BigDecimal(1000);
        BigDecimal parPriceTo = new BigDecimal(10000);



        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Product.class,"product");
            criteria.createCriteria("product.category", "cat");
            criteria.add(Restrictions.like("cat.name", parCategoryName, MatchMode.START).ignoreCase());
            criteria.add(Restrictions.like("product.name", parProductName,MatchMode.START).ignoreCase());
            criteria.add(Restrictions.between("product.price",parPriceFrom,parPriceTo));
            List<Product> product = criteria.list();

            for (Product prod : product){
                System.out.println(String.format("Name: %s, category: %s, price: %f", prod.getName(),prod.getCategory().getName(),prod.getPrice()));
            }
            session.beginTransaction().commit();
        }catch (Exception e){
            session.beginTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();

        }*/

    }

}
