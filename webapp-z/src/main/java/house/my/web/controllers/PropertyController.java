package house.my.web.controllers;

import static house.my.model.enums.ModelAttributeEnum.DESTINATION;
import static house.my.model.enums.ModelAttributeEnum.HAS_RIGHT_TO_REMOVE_PROPERTY;
import static house.my.model.enums.ModelAttributeEnum.LISTE;
import static house.my.model.enums.ModelAttributeEnum.MY_PROPERTIES_LIST;
import static house.my.model.enums.ModelAttributeEnum.PAGESIZE;
import static house.my.model.enums.ModelAttributeEnum.PARTIAL_LIST;
import static house.my.model.enums.ModelAttributeEnum.PROPERTY;
import static house.my.model.enums.ModelAttributeEnum.SIZE;
import static house.my.model.enums.controller.PageEnum.BASE_FORM;
import static house.my.model.enums.controller.PageEnum.HOME;
import static house.my.model.enums.controller.PageEnum.MY_PROPERTIES;
import static house.my.model.enums.controller.PageEnum.TEST;
import static house.my.model.enums.controller.UrlEnum.MYPROPERTIES;
import static house.my.model.enums.controller.UrlEnum.REDIRECT;
import house.my.model.Address;
import house.my.model.Member;
import house.my.model.Property;
import house.my.model.Role;
import house.my.model.enums.controller.FieldEnum;
import house.my.model.enums.controller.PageEnum;
import house.my.model.enums.controller.RCEnum;
import house.my.model.enums.controller.UrlEnum;
import house.my.model.web.PaginatedList;
import house.my.model.web.form.PropertyForm;
import house.my.support.ConstantsZ;
import house.my.web.filter.SpringSecurityFilterUser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "property", "propertyForm", "myProperties" })
public class PropertyController extends BaseController {

  @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
  public String displayHomePage(ModelMap model, HttpSession session, HttpServletRequest request,
      HttpServletResponse response) {

    return HOME.jsp();
  }

  @RequestMapping(value = { "/test" }, method = RequestMethod.GET)
  public String displayTestPage(ModelMap model, HttpSession session, HttpServletRequest request,
      HttpServletResponse response) {

    return TEST.jsp();
  }

  @RequestMapping(value = { "/myProperties" }, method = RequestMethod.GET)
  public String displayMyPropertyListPage(ModelMap model, HttpSession session, HttpServletRequest request,
      HttpServletResponse response) {

    // Boolean propertyCanBeRemoved = null;

    Member connectedMember = new Member();
    List<Property> myPropertiesList = new ArrayList<Property>();

    if (null != SpringSecurityFilterUser.getConnectedMember()) {
      connectedMember = SpringSecurityFilterUser.getConnectedMember();

      // admin can see and remove all properties
      if (null != connectedMember.getProperties()) {
        if (connectedMember.getRoles().contains(Role.ROLE_ADMIN)) {
          myPropertiesList = propertyService.getAllPropertiesList();
          model.addAttribute(HAS_RIGHT_TO_REMOVE_PROPERTY.attribute(), true);

        } else {
          myPropertiesList = connectedMember.getProperties();
        }
        model.addAttribute(MY_PROPERTIES_LIST.attribute(), myPropertiesList);
      }
      ;
    }
    return MY_PROPERTIES.jsp();
  }

  @RequestMapping(value = { "/add" }, method = RequestMethod.GET)
  public String displayAddPropertyForm(ModelMap model, HttpSession session, HttpServletRequest request,
      HttpServletResponse response) {

    Member connectedMember = new Member();
    List<Member> membersList = new ArrayList<Member>();

    if (null != SpringSecurityFilterUser.getConnectedMember()) {
      connectedMember = SpringSecurityFilterUser.getConnectedMember();
      // if (null != connectedMember.getLocalePreference()) {
      // Locale preferedLanguage = getCurrentUser().getLocalePreference();
      // RequestContextUtils.getLocaleResolver(request).setLocale(request,
      // response, preferedLanguage);
      // } else {
      // RequestContextUtils.getLocaleResolver(request).setLocale(request,
      // response,
      // ConstantsZ.LOCALE_ENGLISH);
      // }*/
      membersList.add(connectedMember);
    }

    Property property = new Property();
    property.setCreationDate(new Date());
    Address address = new Address();
    address.setLocality("");
    property.setAddress(address);
    property.setPrice(new BigDecimal(0));
    property.setMembers(membersList);

    model.addAttribute(PROPERTY.attribute(), property);

    // return REDIRECT.url() + ADD.url();
    //
    //
    // Property property = (Property)
    // session.getAttribute(PROPERTY.attribute());
    //
    // if (null == property) {
    // initProperty(model, locale);
    // }
    // model.addAllAttributes(getModelAttributesMap(property, locale));

    return BASE_FORM.jsp();
  }

  @RequestMapping(value = "/remove_listing/{idProperty}", method = RequestMethod.GET)
  public String removePropertyListing(HttpServletRequest request, ModelMap model, @PathVariable Integer idProperty) {

    Property property = propertyService.get(idProperty);
    if (propertyService.isAuthorizedToRemoveProperty(property, getCurrentUser())) {
      propertyService.removePropertyListing(property);
    }

    return REDIRECT.url() + MYPROPERTIES.url();
  }

  // ajax method
  @RequestMapping(value = "/delete_listing", method = RequestMethod.POST,
      produces = "application/json; charset=utf-8")
  public @ResponseBody String listingDelete(@ModelAttribute("property") Property property,
      @RequestParam(value = "idProperty", required = true) Integer idProperty, ModelMap model)
      throws JSONException, IOException {

    if (null != idProperty) {
      JSONObject jsonObjectIdProperty = JSONObject.fromObject(idProperty);
      String idPropertyjsonStr = jsonObjectIdProperty.toString();
      model.addAttribute("idPropertyJson", idPropertyjsonStr);

      if (null != property) {
        if (0 == property.getIdProperty().compareTo(idProperty)) {
          propertyService.removePropertyListing(property);
          return HttpStatus.OK.toString();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.toString();
      }
    }
    return null;
  }

  @RequestMapping(value = { "/save" }, method = { RequestMethod.POST })
  public String saveProperty(ModelMap model, @ModelAttribute("property") Property property, BindingResult result,
      HttpSession session, Locale locale) {

    // if (null == session.getAttribute(PROPERTY.attribute())) {
    // return REDIRECT.url() + INIT.url();
    // }
    if (null == property) {
      property = (Property) model.get(PROPERTY.attribute());
    }

    propertyService.save(property);
    return HOME.jsp();
  }

  @RequestMapping(value = "/search")
  public String displaySearchPropertyForm(@ModelAttribute("PropertyForm") PropertyForm form,
      BindingResult result, HttpServletRequest request, ModelMap model) {

    Date creationDateFrom = null;

    if (StringUtils.isNotBlank(form.getCreationDateFrom())) {
      try {
        creationDateFrom = ConstantsZ.SDF_dd_MM_yyyy_SLASH.parse(form.getCreationDateFrom());
      } catch (Exception e) {
        result.rejectValue(FieldEnum.DATE_CREATION_FROM.field(), RCEnum.ERREUR_FORMAT_DATE_CREATIONFROM.rc());
      }
    }

    if (form.getNbElementsPerPage() <= 0 || form.getNbElementsPerPage() > 100) {
      form.setNbElementsPerPage(ConstantsZ.NB_ELEMENTS_PER_PAGE);
    }
    int startPosition = getStartPosition(request, form.getNbElementsPerPage(), PROPERTY);
    String orderBy = buildOrderBy(request);
    // String withDevise = form.getWithCurrency();
    // CurrencyEnum devise = null;
    // // on a aussi all et alors on ne filtre pas
    // if (!"all".equals(withDevise)) {
    // devise = CurrencyEnum.valueOf(withDevise);
    // }
    // form.setUserName(stringNormalizerService.normalize(form.getUserName(),
    // false, true, false));
    PaginatedList<Property> list = propertyService.getAllPropertiesList(startPosition, form.getNbElementsPerPage(),
        orderBy,
        form.getIdProperty(), form.getPropertyAddressLocality(), creationDateFrom, true, false);

    model.addAttribute(DESTINATION.attribute(), UrlEnum.SEARCH.url());

    // variables for display tag
    model.addAttribute(LISTE.attribute(), list.getPartialList());
    model.addAttribute(PAGESIZE.attribute(), form.getNbElementsPerPage());
    model.addAttribute(SIZE.attribute(), list.getFullListZize());
    model.addAttribute(PARTIAL_LIST.attribute(), true);

    return PageEnum.SEARCH.jsp();
  }

  private static String buildOrderBy(HttpServletRequest request) {
    String tableId = PROPERTY.attribute();
    String columnSort = request.getParameter(new ParamEncoder(tableId)
        .encodeParameterName(TableTagParameters.PARAMETER_SORT));
    String sortOrder = request.getParameter(new ParamEncoder(tableId)
        .encodeParameterName(TableTagParameters.PARAMETER_ORDER));

    StringBuffer orderBy = new StringBuffer(" ORDER BY ");

    if (null != columnSort && null != sortOrder) {
      orderBy.append("UPPER(TRIM(s.").append(columnSort).append(")) ");

      if (sortOrder.equals("2")) {
        orderBy.append("ASC");
      } else {
        orderBy.append("DESC");
      }
    } else {
      orderBy.append("p.creationDate DESC");
    }

    return orderBy.toString();
  }

  /**
   * property cannot be erased if owned by two or more members ???
   * 
   */
  // protected void manageRightToRemoveProperty(Property property, Member
  // connectedMember, Model model) {
  //
  // Boolean hasRightToRemoveProperty =
  // propertyService.isAuthorizedToRemoveProperty(property, connectedMember);
  //
  // if (false == hasRightToRemoveProperty) {
  // model.addAttribute(HAS_RIGHT_TO_REMOVE_PROPERTY.attribute(), false);
  // }
  // }
}
