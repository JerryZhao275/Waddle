package com.example.educationapplication.integration.database.config;

public final class WaddleDatabaseConfiguration {

    final private String baseUrl;
    final private String reference;
    final private boolean mocked;

    public WaddleDatabaseConfiguration(String baseUrl, String reference, boolean isMocked) {
        this.baseUrl = baseUrl;
        this.reference = reference;
        this.mocked = isMocked;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getReference() {
        return this.reference;
    }

    public boolean isMocked() {
        return this.mocked;
    }

    public WaddleDatabaseConfiguration withBaseUrl(String baseUrl) {
        return new WaddleDatabaseConfiguration(baseUrl, reference, mocked);
    }

    public WaddleDatabaseConfiguration withReference(String reference) {
        return new WaddleDatabaseConfiguration(baseUrl, reference, mocked);
    }

    public WaddleDatabaseConfiguration withMocking(boolean mocked) {
        return new WaddleDatabaseConfiguration(baseUrl, reference, mocked);
    }

}
