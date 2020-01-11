/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Vivek Gajbhiye - Cropdata
 */
public class KeyValuePair implements Serializable {

    private String key;
    private String value;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public KeyValuePair(int key, String value) {
        this.key = Integer.toString(key);
        this.value = value;
    }

    public KeyValuePair() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.key != null ? this.key.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeyValuePair other = (KeyValuePair) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }

        return true;
    }

    

    @Override
    public String toString() {
        return "KeyValuePair{" + "key=" + key + ", value=" + value + '}';
    }
    
}
