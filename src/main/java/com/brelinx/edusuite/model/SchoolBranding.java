package com.brelinx.edusuite.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "school_branding")
@Getter
@Setter
public class SchoolBranding extends BaseEntity {

    private String schoolId;
    private String schoolName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private String accentColor;
    private String fontFamily;
    private String welcomeMessage;
    private String footerText;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private String website;
    
    // Social media links
    private String facebookUrl;
    private String twitterUrl;
    private String linkedinUrl;
    private String instagramUrl;
    
    // Custom CSS for advanced branding
    private String customCss;
    
    // Default constructor with default branding
    public SchoolBranding() {
        this.primaryColor = "#4facfe";
        this.secondaryColor = "#00f2fe";
        this.accentColor = "#667eea";
        this.fontFamily = "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif";
        this.welcomeMessage = "Welcome to our School Management System";
        this.footerText = "© 2024 School Management System. All rights reserved.";
    }
}