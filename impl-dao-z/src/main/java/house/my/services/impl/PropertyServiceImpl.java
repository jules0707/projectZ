package house.my.services.impl;

import house.my.dao.GenericDao;
import house.my.dao.PropertyDao;
import house.my.model.Address;
import house.my.model.Member;
import house.my.model.Property;
import house.my.model.Role;
import house.my.model.web.PaginatedList;
import house.my.services.MemberService;
import house.my.services.PropertyService;
import house.my.support.ConstantsZ;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

  @Autowired
  @Setter
  private PropertyDao propertyDao;

  @Autowired
  @Setter
  private MemberService memberService;

  @Override
  public Property get(Integer id) {
    return propertyDao.getCompleteProperty(id);
  }

  @Override
  public Property save(Property property) {
    return save(property, propertyDao);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Property save(Property property, GenericDao dao) {
    return (Property) dao.save(property);
  }

  @Override
  public PaginatedList<Property> getAllPropertiesList(int startPosition, int maxResult, String orderBy,
      Integer idProperty, String locality, Date creationDateFrom, boolean useVireAccentsTiretsEspacesFunction,
      boolean isCanceled) {

    PaginatedList<Property> list = new PaginatedList<Property>();

    StringBuffer whereBuffer = new StringBuffer();
    whereBuffer.append("where 1=1 ");

    if (null != idProperty) {
      whereBuffer.append(" and p.idProperty = '");
      whereBuffer.append(idProperty);
      whereBuffer.append("'");
    }

    if (null != locality && StringUtils.isNotEmpty(locality)) {
      Address address = new Address(locality);
      whereBuffer.append(" and p.address.locality = '");
      whereBuffer.append(address.getLocality());
      whereBuffer.append("'");
    }

    if (null != creationDateFrom) {
      whereBuffer.append(" and p.creationDate >= '");
      whereBuffer.append(ConstantsZ.SDF_yyyy_MM_dd_DASH.format(creationDateFrom));
      whereBuffer.append("'");
    }

    if (false == isCanceled) {
      whereBuffer.append(" and p.cancelationDate IS NULL ");
    }

    String whereCondition = whereBuffer.toString();
    Integer size = (int) propertyDao.countProperties(whereCondition, true);
    List<Property> partialList = propertyDao.findPropertyList(startPosition, maxResult, orderBy, whereCondition, true);

    list.setFullListZize(size);
    list.setPartialList(partialList);
    return list;
  }

  @Override
  public boolean isAuthorizedToRemoveProperty(Property property, Member connectedMember) {
    if (null == connectedMember || null == property) {
      return false;
    }
    if (connectedMember.getRoles().contains(Role.ROLE_ADMIN)) {
      return true;
    }

    if (null != property.getMembers()) {
      int size = property.getMembers().size();
      if (1 == size) {
        if (null != property.getMembers().get(0).getName()) {
          if (StringUtils.equals(connectedMember.getName(), property.getMembers().get(0).getName())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean isAuthorizedToEditProperty(Property property, Member connectedMember) {
    if (null == connectedMember) {
      return false;
    }
    if (connectedMember.getRoles().contains(Role.ROLE_ADMIN)) {
      return true;
    }

    else if (connectedMember.getRoles().contains(Role.ROLE_USER)) {
      Integer idMember = connectedMember.getIdMember();

      if (null != idMember) {
        // count the number of lines in home table for that member
        StringBuffer whereBuffer = getPropertiesForMemberWhereCondition(idMember);
        String whereCondition = whereBuffer.toString();
        ;

        Integer propertiesCount = (int) propertyDao.countProperties(whereCondition, true);
        if (propertiesCount > 0) {
          return true;
        }
        else
          return false;
      }
    }
    return false;
  }

  @Override
  public PaginatedList<Property> getPropertiesForMember(int startPosition, int maxResult, String orderBy,
      String memberlogin) {

    // retrieve member
    if (StringUtils.isNotBlank(memberlogin)) {

      Member member = memberService.getMemberByLogin(memberlogin);
      Integer idMember = member.getIdMember();

      // retrieve properties for member
      PaginatedList<Property> list = new PaginatedList<Property>();
      StringBuffer whereBuffer = getPropertiesForMemberWhereCondition(idMember);

      String whereCondition = whereBuffer.toString();
      Integer size = (int) propertyDao.countProperties(whereCondition, true);

      List<Property> partialList = propertyDao
          .findPropertyList(startPosition, maxResult, orderBy, whereCondition, true);

      list.setFullListZize(size);
      list.setPartialList(partialList);
      return list;
    }
    return null;
  }

  private StringBuffer getPropertiesForMemberWhereCondition(Integer idMember) {
    StringBuffer whereBuffer = new StringBuffer();
    whereBuffer.append("select * from Property p join p.member m where p.id = :");

    if (null != idMember) {
      whereBuffer.append("'");
      whereBuffer.append(idMember);
      whereBuffer.append("'");
    }
    return whereBuffer;
  }

  public List<Property> getPropertiesForMember(Member member) {
    List<Property> properties = new ArrayList<Property>();
    if (null != member) {
      String memberLogin = member.getLogin();
      PaginatedList<Property> paginatedList = getPropertiesForMember(0, -1, null, memberLogin);
      if (null != paginatedList) {
        properties = paginatedList.getPartialList();
      }
    }
    return properties;
  }

  @Override
  public void removePropertyListing(Property property) {
    property.setCancelationDate(new Date());
    propertyDao.save(property);
  }

  @Override
  public List<Property> getAllPropertiesList() {
    List<Property> properties = new ArrayList<Property>();
    properties = propertyDao.getAll();
    return properties;
  }

}
