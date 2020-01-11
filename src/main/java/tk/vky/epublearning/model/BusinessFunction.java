/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vky.epublearning.model;

import java.io.Serializable;

/**
 *
 * @author akil
 */
public class BusinessFunction implements Serializable {

    private String businessFunction;
    private String businessFunctionDesc;

    public String getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(String businessFunction) {
        this.businessFunction = businessFunction;
    }

    public String getBusinessFunctionDesc() {
        return businessFunctionDesc;
    }

    public void setBusinessFunctionDesc(String businessFunctionDesc) {
        this.businessFunctionDesc = businessFunctionDesc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.businessFunction != null ? this.businessFunction.hashCode() : 0);
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
        final BusinessFunction other = (BusinessFunction) obj;
        if ((this.businessFunction == null) ? (other.businessFunction != null) : !this.businessFunction.equals(other.businessFunction)) {
            return false;
        }
        return true;
    }
}
