package house.my.web.filter;

import static house.my.model.enums.ModelAttributeEnum.REQUEST_PERSISTENT_ERROR_MESSAGE;
import static house.my.model.enums.ModelAttributeEnum.REQUEST_PERSISTENT_INFO_MESSAGE;
import static house.my.model.enums.ModelAttributeEnum.REQUEST_URL;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MessageFilter implements Filter {

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;

    HttpSession session = request.getSession(false);
    if (null != session) {
      // grab messages from the session and put them into request
      // this is so they're not lost in a redirect
      Object infos = session.getAttribute(REQUEST_PERSISTENT_INFO_MESSAGE.attribute());
      if (null != infos) {
        request.setAttribute(REQUEST_PERSISTENT_INFO_MESSAGE.attribute(), infos);
        session.removeAttribute(REQUEST_PERSISTENT_INFO_MESSAGE.attribute());
      }

      Object errors = session.getAttribute(REQUEST_PERSISTENT_ERROR_MESSAGE.attribute());
      if (null != errors) {
        request.setAttribute(REQUEST_PERSISTENT_ERROR_MESSAGE.attribute(), errors);
        session.removeAttribute(REQUEST_PERSISTENT_ERROR_MESSAGE.attribute());
      }
    }

    // set the requestURL as a request attribute for templates
    // particularly freemarker, which doesn't allow request.getRequestURL()
    request.setAttribute(REQUEST_URL.attribute(), request.getRequestURL());
    chain.doFilter(req, res);
  }

  public void init(FilterConfig filterConfig) {
  }

  public void destroy() {
  }
}
