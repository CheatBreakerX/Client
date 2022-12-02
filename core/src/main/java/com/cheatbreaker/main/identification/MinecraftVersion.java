package com.cheatbreaker.main.identification;

public enum MinecraftVersion {
    v1_7_10("1.7.10"),
    v1_8_9("1.8.9"),
    v1_9_4("1.9.4"),
    v1_10_2("1.10.2"),
    v1_11_2("1.11.2"),
    v1_12_2("1.12.2"),
    v1_13_2("1.13.2"),
    v1_14_4_FORGE("1.14.4-Forge"),
    v1_14_4_FABRIC("1.14.4-Fabric"),
    v1_15_2_FORGE("1.15.2-Forge"),
    v1_15_2_FABRIC("1.15.2-Fabric"),
    v1_16_5_FORGE("1.16.5-Forge"),
    v1_16_5_FABRIC("1.16.5-Fabric"),
    v1_17_1_FORGE("1.17.1-Forge"),
    v1_17_1_FABRIC("1.17.1-Fabric"),
    v1_18_2_FORGE("1.18.2-Forge"),
    v1_18_2_FABRIC("1.18.2-Fabric"),
    v1_19_FORGE("1.19-Forge"),
    v1_19_FABRIC("1.19-Fabric"),
    v1_19_2_FORGE("1.19.2-Forge"),
    v1_19_2_FABRIC("1.19.2-Fabric");

    private final String asString;

    MinecraftVersion(String asString) {
        this.asString = asString;
    }

    public String toString() {
        return this.asString;
    }

    public boolean isFabric() {
        return this.toString().contains("-Fabric");
    }

    public boolean isForge() {
        return !this.isFabric();
    }
}
