package pl.jkan.ecommerce.system.infrastructure;

import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.system.SystemUser;
import pl.jkan.ecommerce.system.SystemUserContext;

import javax.servlet.http.HttpSession;

public class HttpSessionSystemUserContext implements SystemUserContext {
    private static final String USER_KEY = "system_user_id";

    private HttpSession httpSession;

    public HttpSessionSystemUserContext(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public SystemUser getCurrentUser() {
        if (httpSession.getAttribute(USER_KEY) != null) {
            return new SystemUser(new Identifier(httpSession.getAttribute(USER_KEY).toString()));
        }

        Identifier newSystemUserId = Identifier.generateUUID();
        httpSession.setAttribute(USER_KEY, newSystemUserId.toString());

        return new SystemUser(newSystemUserId);
    }
}
