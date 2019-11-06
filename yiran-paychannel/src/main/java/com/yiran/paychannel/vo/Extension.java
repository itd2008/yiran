package com.yiran.paychannel.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Extension implements Serializable
{
  private static final long serialVersionUID = 8034857888313309344L;
  private List<Kvp> entryList = new ArrayList();

  public void add(String key, String value)
  {
    remove(key);
    this.entryList.add(new Kvp(key, value));
  }

  public String getValue(String key)
  {
    if ((this.entryList == null) || (this.entryList.size() == 0)) {
      return null;
    }
    for (Kvp kvp : this.entryList) {
      if (kvp != null)
      {
        if (StringUtils.equals(kvp.getKey(), key)) {
          return kvp.getValue();
        }
      }
    }
    return null;
  }

  public void remove(String key)
  {
    if ((this.entryList == null) || (this.entryList.size() == 0)) {
      return;
    }
    for (Iterator it = this.entryList.iterator(); it.hasNext(); ) {
      Kvp kvp = (Kvp)it.next();
      if (kvp != null)
      {
        if (StringUtils.equals(kvp.getKey(), key)) {
          it.remove();
          return;
        }
      }
    }
  }

  public List<Kvp> getEntryList() { return this.entryList; }

  public void setEntryList(List<Kvp> entryList)
  {
    this.entryList = entryList;
  }
}