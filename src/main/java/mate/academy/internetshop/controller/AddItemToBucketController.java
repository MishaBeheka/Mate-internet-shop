package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class AddItemToBucketController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddItemToBucketController.class);
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            String itemId = req.getParameter("item_id");
            Item item = itemService.get(Long.valueOf(itemId));
            bucketService.addItem(bucket, item);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("error_msg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/errorDB.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/getAllItems");
    }
}
