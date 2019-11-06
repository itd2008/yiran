
package com.yiran.paychannel.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.netfinworks.common.domain.Kvp;
import com.netfinworks.common.lang.StringUtil;


/**
 * <p>Java class for map complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="map">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entry" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "map", propOrder = {
    "entry"
})
public class CustomMap implements Serializable{

    @XmlElement(required = true)
    protected List<CustomMap.Entry> entry;

    /**
     * Gets the value of the entry property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entry property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntry().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomMap.Entry }
     *
     *
     */
    public List<CustomMap.Entry> getEntry() {
        if (entry == null) {
            entry = new ArrayList<CustomMap.Entry>();
        }
        return this.entry;
    }

    /**
     * 增加键值
     * @param key
     * @param value
     */
    public void add(String key, String value) {
    	CustomMap.Entry map = new CustomMap.Entry();
    	map.setKey(key);
    	map.setValue(value);
        this.getEntry().add(map);
    }

    /**
     * 根据键对象获取值对象
     * @param key
     * @return
     */
    public String getValue(String key) {
        if (entry == null || entry.size() == 0) {
            return null;
        }
        for (CustomMap.Entry mapEntry : entry) {
            if (mapEntry == null) {
                continue;
            }
            if (StringUtil.equals(mapEntry.getKey(), key)) {
                return mapEntry.getValue();
            }
        }

        return null;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Entry {

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String key;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the key property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets the value of the key property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setKey(String value) {
            this.key = value;
        }

		@Override
		public String toString() {
			return "Entry [key=" + key + ", value=" + value + "]";
		}

    }

}
