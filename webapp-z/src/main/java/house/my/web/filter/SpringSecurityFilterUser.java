package house.my.web.filter;

import house.my.model.Member;
import house.my.services.MemberService;
import house.my.services.impl.MemberServiceImpl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringSecurityFilterUser implements Filter {

  private static ThreadLocal<Member> member = new ThreadLocal<Member>();
  private static MemberService memberService;

  private static String getLoginConnectedMember() {

    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();

    if (null == authentication) {
      return null;
    }

    Object o = authentication.getPrincipal();
    String login = null;

    if (o instanceof UserDetails) {
      login = ((UserDetails) o).getUsername();
    } else {
      login = o.toString();
    }
    return login;
  }

  public static Member getConnectedMember() {
    if (member.get() == null) {
      String login = getLoginConnectedMember();
      if (!"anonymousUser".equals(login)) {
        // memberService null pour les tests unitaires
        member.set(null != memberService ? memberService.getMemberByLogin(login) : null);
      }
    }
    return member.get();
  }

  public void setSecuriteService(MemberServiceImpl service) {
    SpringSecurityFilterUser.memberService = service;
  }

  public static void resetConnectedUser() {
    member.set(null);
  }

  public void init(FilterConfig filterConfig) throws ServletException {
    WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    SpringSecurityFilterUser.memberService = context.getBean("MemberService", MemberService.class);
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {

    String login = getLoginConnectedMember();

    if (login != null && !"anonymousUser".equals(login)) {
      member.set(memberService.getMemberByLogin(login));
    }

    chain.doFilter(request, response);
    member.remove();
  }

  public void destroy() {
  }
}
