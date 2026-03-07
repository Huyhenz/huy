package com.example.demo1.model;

public class StaticProduct {
    private Long id;
    private String name;
    private double originalPrice;
    private double currentPrice;
    private String discountPercent; // e.g. "-15%"
    private String imageUrl;

    // Specifications (e.g. "6.7 inch", "256GB")
    private String screenSize;
    private String ram;
    private String storage;

    // Badges (e.g. "Mới", "Trợ giá lên đời")
    private String badgeText;

    // A flag to highlight specific items visually
    private boolean isHot;

    public StaticProduct() {
    }

    public StaticProduct(Long id, String name, double originalPrice, double currentPrice, String discountPercent,
            String imageUrl, String screenSize, String ram, String storage, String badgeText, boolean isHot) {
        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.discountPercent = discountPercent;
        this.imageUrl = imageUrl;
        this.screenSize = screenSize;
        this.ram = ram;
        this.storage = storage;
        this.badgeText = badgeText;
        this.isHot = isHot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }
}
