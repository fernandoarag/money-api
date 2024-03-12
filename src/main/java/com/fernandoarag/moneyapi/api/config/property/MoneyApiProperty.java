package com.fernandoarag.moneyapi.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("money")
public class MoneyApiProperty {

    private String originPermitted = "http://localhost:8000";

    final private Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public String getOriginPermitted() {
        return originPermitted;
    }

    public void setOriginPermitted(String originPermitted) {
        this.originPermitted = originPermitted;
    }

    public static class Security {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

}
