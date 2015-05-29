package house.my.web.controllers;

import static house.my.model.enums.ModelAttributeEnum.REQUEST_PERSISTENT_ERROR_MESSAGE;
import static house.my.model.enums.ModelAttributeEnum.REQUEST_PERSISTENT_INFO_MESSAGE;
import house.my.model.Member;
import house.my.model.editors.ZBigDecimalEditor;
import house.my.model.editors.ZDateEditor;
import house.my.model.enums.ModelAttributeEnum;
import house.my.model.enums.ServiceEnum;
import house.my.model.enums.controller.FieldEnum;
import house.my.model.enums.controller.RCEnum;
import house.my.services.PropertyService;
import house.my.services.StringNormalizerService;
import house.my.utils.ZProperties;
import house.my.web.filter.SpringSecurityFilterUser;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Setter;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.support.RequestContextUtils;

public abstract class BaseController {

  @Autowired
  @Setter
  protected ZProperties ZProperties;
  @Autowired
  @Setter
  protected PropertyService propertyService;
  @Autowired
  protected MessageSource messageSource;
  @Autowired
  @Setter
  protected StringNormalizerService stringNormalizerService;
  protected int step;

  @InitBinder
  public void initDataBinder(WebDataBinder binder, WebRequest webRequest, HttpSession session,
      HttpServletRequest request) {
    // managing date format in forms
    Locale locale = RequestContextUtils.getLocale(request);
    String dateFormatStr = messageSource.getMessage("engine.form.date.format", null, locale);
    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
    binder.registerCustomEditor(java.util.Date.class, new ZDateEditor(df));
    binder.registerCustomEditor(BigDecimal.class, new ZBigDecimalEditor());
    binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    binder.setDisallowedFields(new String[] { "id" });
  }

  @ModelAttribute("environnement")
  public String getEnvironnement(HttpServletRequest request) {
    return ZProperties.getEnvironnement();
  }

  @ModelAttribute("hostname")
  public String getHostname(HttpServletRequest request) {
    return request.getRemoteAddr() + " " + request.getRemoteHost() + " " + request.getLocalName();
  }

  @ModelAttribute("user")
  public Member getCurrentUser() {
    return SpringSecurityFilterUser.getConnectedMember();
  }

  protected int getStartPosition(HttpServletRequest request, int eltsPerPage,
      ModelAttributeEnum modelAttribute) {
    int startPosition;
    String paramPage = request.getParameter((new
        ParamEncoder(modelAttribute.attribute())
            .encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
    if (paramPage == null || paramPage.equals("1")) {
      startPosition = 0;
    } else {
      startPosition = ((Integer.parseInt(paramPage)) - 1) * eltsPerPage;
    }
    return startPosition;
  }

  protected String buildOrderBy(HttpServletRequest request, ModelAttributeEnum persistentClass,
      ModelAttributeEnum persistentClassAlias, FieldEnum defaultSort) {

    String tableId = persistentClass.attribute();
    String columnSort = request.getParameter(new ParamEncoder(tableId)
        .encodeParameterName(TableTagParameters.PARAMETER_SORT));
    String sortOrder = request.getParameter(new ParamEncoder(tableId)
        .encodeParameterName(TableTagParameters.PARAMETER_ORDER));

    StringBuffer orderBy = new StringBuffer(" ORDER BY ");

    if (null != columnSort && null != sortOrder) {
      // pas de trim et upper pour des integer sinon ca ne classe pas bien
      if (!columnSort.contains("id")) {
        orderBy.append("UPPER(TRIM(").append(persistentClassAlias.attribute()).append(ServiceEnum.DOT.value())
            .append(columnSort).append(")) ");
      } else {
        orderBy.append(persistentClassAlias.attribute()).append(ServiceEnum.DOT.value()).append(columnSort)
            .append(ServiceEnum.SPACE.value());
      }

      if (sortOrder.equals("2")) {
        orderBy.append("ASC");
      } else {
        orderBy.append("DESC");
      }
    } else {
      orderBy.append(persistentClassAlias.attribute()).append(ServiceEnum.DOT.value()).append(defaultSort.field())
          .append(" DESC");
    }

    return orderBy.toString();
  }

  protected void addErrorToModel(ModelMap model, RCEnum error, Locale locale) {
    addErrorToModel(model, error, locale, (String) null);
  }

  protected void addErrorToModel(ModelMap model, RCEnum error, Locale locale, String... args) {
    addErrorToModel(model, messageSource.getMessage(error.rc(), null, locale));
  }

  protected void addErrorToModel(ModelMap model, String serror) {
    model.addAttribute(REQUEST_PERSISTENT_ERROR_MESSAGE.attribute(), serror);
  }

  protected void addErrorToRequest(HttpServletRequest request, RCEnum error, Locale locale) {
    addErrorToRequest(request, error, locale, (String) null);
  }

  protected void addErrorToRequest(HttpServletRequest request, RCEnum error, Locale locale, String... args) {
    request.getSession(false).setAttribute(REQUEST_PERSISTENT_ERROR_MESSAGE.attribute(),
        messageSource.getMessage(error.rc(), args, locale));
  }

  protected void addInfoToModel(ModelMap model, RCEnum info, Locale locale) {
    addInfoToModel(model, info, locale, (String) null);
  }

  protected void addInfoToModel(ModelMap model, RCEnum info, Locale locale, String... args) {
    addInfoToModel(model, messageSource.getMessage(info.rc(), args, locale));
  }

  protected void addInfoToModel(ModelMap model, String sinfo) {
    model.addAttribute(REQUEST_PERSISTENT_INFO_MESSAGE.attribute(), sinfo);
  }

  protected void addInfoToRequest(HttpServletRequest request, RCEnum info, Locale locale) {
    addInfoToRequest(request, info, locale, (String) null);
  }

  protected void addInfoToRequest(HttpServletRequest request, RCEnum info, Locale locale, String... args) {
    request.getSession(false).setAttribute(REQUEST_PERSISTENT_INFO_MESSAGE.attribute(),
        messageSource.getMessage(info.rc(), args, locale));
  }

  protected void addInfoToRequestSf(HttpServletRequest request, RCEnum info, Locale locale, String... args) {
    request.getSession(false).setAttribute(REQUEST_PERSISTENT_INFO_MESSAGE.attribute(),
        String.format(messageSource.getMessage(info.rc(), null, locale), (Object[]) args));
  }
}