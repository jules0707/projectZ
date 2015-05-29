package house.my.xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import house.my.support.EMWLineArguments;

public class ErrorLineArgumentsAdapter extends XmlAdapter<String, EMWLineArguments> {

  @Override
  public EMWLineArguments unmarshal(String s) throws Exception {
    throw new Exception("cannot unmarshal ELA yet");
  }

  @Override
  public String marshal(EMWLineArguments ela) throws Exception {
    return ela.toString();
  }
}
