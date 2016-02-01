package ru.priceList;


import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import ru.priceList.models.Product;
import ru.priceList.service.JPA.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Основной класс для обработки и поиска данных
 * @author pe.kolomnikov
 * @version 1.0
 */
public class FindDataFromPriceList extends HttpServlet {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private List<Product> product_list;
    private String fullName;
    private String category;
    private BigDecimal parPriceFrom;
    private BigDecimal parPriceTo;

    /**
     *закрывает фабрику подключение Hibernate
     */
    @Override
    public void destroy() {
        super.destroy();
        sessionFactory.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/MainMenu.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Content-Type", "application/json; charset=utf-8");
        creatingVariables(req);
        findProductFromBD(fullName, category, parPriceFrom, parPriceTo);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(product_list));
    }

    /**
     * Создает параметры(в нужных типах) для запроса из строк от клиента
     * @param req входной поток
     */
    private void creatingVariables(HttpServletRequest req) {
        fullName = req.getParameter("name");
        category = req.getParameter("category");

        parPriceFrom = new BigDecimal(0);
        if (!"".equals(req.getParameter("priceFrom"))) {
            parPriceFrom = parPriceFrom.add(new BigDecimal(req.getParameter("priceFrom")));
        }
        parPriceTo = new BigDecimal(0);
        if (!"".equals(req.getParameter("priceTo"))) {
            parPriceTo = parPriceTo.add(new BigDecimal(req.getParameter("priceTo")));
        }
    }

    /**
     * Функция подулючается к базе и получает список товара по переданным ей параметрам
     * @param parProductName
     * @param parCategoryName
     * @param parPriceFrom
     * @param parPriceTo
     */
    private void findProductFromBD(String parProductName, String parCategoryName, BigDecimal parPriceFrom, BigDecimal parPriceTo) {

        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Product.class, "product");
            criteria.createCriteria("product.category", "cat");
            if (!parCategoryName.equals("")) {
                criteria.add(Restrictions.like("cat.name", parCategoryName, MatchMode.START).ignoreCase());
            }
            if (!parProductName.equals("")) {
                criteria.add(Restrictions.like("product.name", parProductName, MatchMode.START).ignoreCase());
            }

            if ((!parPriceFrom.equals(BigDecimal.ZERO)) && (!parPriceTo.equals(BigDecimal.ZERO))) {
                criteria.add(Restrictions.between("product.price", parPriceFrom, parPriceTo));
            }
            if (!parPriceTo.equals(BigDecimal.ZERO)) {
                criteria.add(Restrictions.le("product.price", parPriceTo));
            }
            if (!parPriceFrom.equals(BigDecimal.ZERO)) {
                criteria.add(Restrictions.ge("product.price", parPriceFrom));
            }

            product_list = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}