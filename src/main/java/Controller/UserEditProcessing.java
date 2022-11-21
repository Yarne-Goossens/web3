package Controller;

import Controller.RequestHandler;
import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class UserEditProcessing extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("userid"));
        User editUser = service.getUserWithId(id);
        User edit = new User();

        ArrayList<String> errors = new ArrayList<String>();

        request.setAttribute("userid", id);
        request.setAttribute("tobeEdited", editUser);

        edit.setEmailRequest( request, errors);
        edit.setFirstNameRequest( request, errors);
        edit.setLastNameRequest( request, errors);
        edit.setRoleRequest( request, errors);
        edit.setTeamRequest( request, errors);

        if (errors.size() == 0) {
            try {
                service.updateUser(id,edit);

                return "Controller?command=UserOverview";
            } catch (DbException d) {
                errors.add(d.getMessage());
            } catch (NumberFormatException n) {
                errors.add(n.getMessage());
            } catch (IllegalArgumentException n) {
                errors.add(n.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=UserEditForm";
    }
}