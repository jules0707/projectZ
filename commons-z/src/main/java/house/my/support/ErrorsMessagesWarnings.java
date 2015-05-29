package house.my.support;

import house.my.model.enums.EMWEnum;
import house.my.model.enums.ServiceEnum;
import house.my.xml.adapters.ErrorLineArgumentsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

@Data
@XmlRootElement(name = "emw")
@XmlAccessorType(XmlAccessType.FIELD)
@Log4j
public class ErrorsMessagesWarnings implements Marshallable {

  private static final String LINUX_CLEAR = "[H[2J";

  @XmlElement(name = "errors")
  @XmlJavaTypeAdapter(value = ErrorLineArgumentsAdapter.class)
  private final List<EMWLineArguments> errors = new ArrayList<EMWLineArguments>();

  @XmlElement(name = "messages")
  @XmlJavaTypeAdapter(value = ErrorLineArgumentsAdapter.class)
  private final List<EMWLineArguments> messages = new ArrayList<EMWLineArguments>();

  @XmlElement(name = "warnings")
  @XmlJavaTypeAdapter(value = ErrorLineArgumentsAdapter.class)
  private final List<EMWLineArguments> warnings = new ArrayList<EMWLineArguments>();

  public void addError(EMWLineArguments errorLineArguments) {
    errors.add(errorLineArguments);
  }

  public void addError(int line, EMWEnum error, Object... args) {
    addError(new EMWLineArguments(error, line, args));
  }

  public void addError(EMWEnum error, Object... args) {
    addError(-1, error, args);
  }

  public void addError(int line, EMWEnum error) {
    addError(line, error, (Object[]) null);
  }

  public void addError(final EMWEnum error) {
    addError(-1, error, (Object[]) null);
  }

  public void addMessage(EMWLineArguments errorLineArguments) {
    messages.add(errorLineArguments);
  }

  public void addMessage(int line, EMWEnum message, Object... args) {
    addMessage(new EMWLineArguments(message, line, args));
  }

  public void addMessage(EMWEnum message, Object... args) {
    addMessage(-1, message, args);
  }

  public void addMessage(int line, EMWEnum message) {
    addMessage(line, message, (Object[]) null);
  }

  public void addMessage(final EMWEnum message) {
    addMessage(-1, message, (Object[]) null);
  }

  private void addWarning(EMWLineArguments errorLineArguments) {
    warnings.add(errorLineArguments);
  }

  public void addWarning(int line, EMWEnum warning, Object... args) {
    addWarning(new EMWLineArguments(warning, line, args));
  }

  public void addWarning(EMWEnum warning, Object... args) {
    addWarning(-1, warning, args);
  }

  public void addWarning(int line, EMWEnum warning) {
    addWarning(line, warning, (Object[]) null);
  }

  public void addWarning(final EMWEnum warning) {
    addWarning(-1, warning, (Object[]) null);
  }

  public void addAll(final ErrorsMessagesWarnings emw) {
    errors.addAll(emw.getErrors());
    messages.addAll(emw.getMessages());
    warnings.addAll(emw.getWarnings());
  }

  public boolean hasErrors() {
    return !errors.isEmpty();
  }

  public boolean hasWarnings() {
    return !warnings.isEmpty();
  }

  @SuppressWarnings("unchecked")
  public List<EMWEnum> getErrorEnums() {
    return (List<EMWEnum>) CollectionUtils.collect(errors, new Transformer() {

      public Object transform(Object input) {
        return ((EMWLineArguments) input).getError();
      }
    });
  }

  public void dumpErrors() {
    for (EMWLineArguments ela : errors) {
      // suppression de la commande linux 'clear' éventuelle
      log.error(ela.toString().replace(LINUX_CLEAR, ""));
    }
  }

  public void dumpMessages() {
    for (EMWLineArguments ela : messages) {
      log.info(ela.toString().replace(LINUX_CLEAR, ""));
    }
  }

  public void dumpWarnings() {
    StringBuffer sb = new StringBuffer();
    dumpWarnings(sb);
    log.info(sb);
  }

  public void dumpWarnings(StringBuffer sb) {
    for (EMWLineArguments ela : warnings) {
      sb.append(ela.toString().replace(LINUX_CLEAR, "")).append(ServiceEnum.EOL.value());
    }
  }

  public void dumpAll() {
    dumpErrors();
    dumpMessages();
  }
}
