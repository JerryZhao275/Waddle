package com.example.educationapplication.integration.database.config;

public final class WaddleDatabaseConfiguration {

    private final boolean mocked;

    public WaddleDatabaseConfiguration() {
        // init defaults...
        this.mocked = false;
    }

    private WaddleDatabaseConfiguration(boolean mocked) {
        super();
        this.mocked = mocked;
    }

    public boolean isMocked() {
        return this.mocked;
    }

    public WaddleDatabaseConfiguration withMocking(boolean mocked) {
        return new WaddleDatabaseConfiguration(mocked); // we keep the config object immutable
    }

}
