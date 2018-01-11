package command;

import javax.servlet.http.HttpServletRequest;

public class ConferenceCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
//        ConferenceDAO dao = new ConferenceDAO();
//        List<Conference> conferences = dao.findByDate();
//        request.setAttribute("conferences", conferences);
//        page = ConfigurationManager.getProperty("path.page.conference");
        return page;
    }
}
