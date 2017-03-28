package com.mercadopago.model;

import java.math.BigDecimal;
import java.util.List;

public class PaymentMethod {

    private List<String> additionalInfoNeeded;
    private String id;
    private String name;
    private String paymentTypeId;
    private String status;
    private String secureThumbnail;
    private String thumbnail;
    private String deferredCapture;
    private List<Setting> settings;
    private BigDecimal minAllowedAmount;
    private BigDecimal maxAllowedAmount;
    private Integer accreditationTime;
    private List<FinancialInstitution> financialInstitutions;


    public List<FinancialInstitution> getFinancialInstitutions() {
        return financialInstitutions;
    }

    public void setFinancialInstitutions(List<FinancialInstitution> financialInstitutions) {
        this.financialInstitutions = financialInstitutions;
    }

    public List<String> getAdditionalInfoNeeded() {
        return additionalInfoNeeded;
    }

    public void setAdditionalInfoNeeded(List<String> additionalInfoNeeded) {
        this.additionalInfoNeeded = additionalInfoNeeded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isIssuerRequired() {

        return isAdditionalInfoNeeded("issuer_id");
    }

    public boolean isSecurityCodeRequired(String bin) {
        Setting setting = Setting.getSettingByBin(settings, bin);
        if ((setting != null) && (setting.getSecurityCode() != null) &&
                (setting.getSecurityCode().getLength() != 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isIdentificationTypeRequired() {
        return isAdditionalInfoNeeded("cardholder_identification_type");
    }

    public boolean isIdentificationNumberRequired() {
        return isAdditionalInfoNeeded("cardholder_identification_number");
    }

    public boolean isEntityTypeRequired(){
        return isAdditionalInfoNeeded("entity_type");
    }

    public boolean isFinancialInstitutionsRequired(){
        return financialInstitutions!= null && !financialInstitutions.isEmpty();
    }

    private boolean isAdditionalInfoNeeded(String param) {

        if ((additionalInfoNeeded != null) && (additionalInfoNeeded.size() > 0)) {
            for (int i = 0; i < additionalInfoNeeded.size(); i++) {
                if (additionalInfoNeeded.get(i).equals(param)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidForBin(String bin) {

        return (Setting.getSettingByBin(this.getSettings(), bin) != null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDeferredCapture() {
        return deferredCapture;
    }

    public void setDeferredCapture(String deferredCapture) {
        this.deferredCapture = deferredCapture;
    }

    public BigDecimal getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(BigDecimal minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public BigDecimal getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(BigDecimal maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public void setAccreditationTime(Integer accreditationTime) {
        this.accreditationTime = accreditationTime;
    }

    public Integer getAccreditationTime() {
        return accreditationTime;
    }

    public boolean isAdditionalInfoNeeded() {
        return ((additionalInfoNeeded != null) && (additionalInfoNeeded.size() > 0)) || isFinancialInstitutionsRequired();
    }

    public boolean isOnPaymentMethod() {
        return paymentTypeId.equals("credit_card") || paymentTypeId.equals("debit_card");
    }
}
