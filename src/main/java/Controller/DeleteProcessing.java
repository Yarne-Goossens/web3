package Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProcessing extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int deleteId=Integer.parseInt(request.getParameter("userid"));
        //service.delete(deleteId);
        return "Controller?command=Overview";
    }
}