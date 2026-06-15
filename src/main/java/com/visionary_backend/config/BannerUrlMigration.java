package com.visionary_backend.config;

import com.visionary_backend.repository.IndustryRepository;
import com.visionary_backend.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * One-time idempotent migration: corrects bannerUrl values in the database
 * so they match the actual filenames on disk under /public/images/.
 *
 * Runs at order 2 (after DataInitializer which runs at default order).
 * Each update is guarded — only applied when the current value is wrong.
 */
@Component
@Order(2)
public class BannerUrlMigration implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BannerUrlMigration.class);

    private final ServiceRepository serviceRepository;
    private final IndustryRepository industryRepository;

    // slug → correct bannerUrl (matching actual filename on disk)
    private static final Map<String, String> SERVICE_FIXES = Map.ofEntries(
        Map.entry("application-services",              "/images/services/application-services-banner.jpg"),
        Map.entry("nextops-business-process-services", "/images/services/nextops-business-process-services-banner.jpg"),
        Map.entry("it-value-stream-acceleration-devops", "/images/services/it-value-stream-acceleration-devops-banner.jpg"),
        Map.entry("devops-automation-services",        "/images/services/devops-automation-services-banner.jpg"),
        Map.entry("enterprise-automation",             "/images/services/enterprise-automation-banner.jpg"),
        Map.entry("governance-risk-and-compliance",    "/images/services/governance-risk-and-compliance-banner.jpg"),
        Map.entry("infrastructure-services",           "/images/services/infrastructure-services-banner.jpg"),
        Map.entry("aws-services",                      "/images/services/aws-services-banner.jpg"),
        Map.entry("azure-services",                    "/images/services/azure-services-banner.jpg"),
        Map.entry("agile-it-operations",               "/images/services/agile-it-operations-banner.jpg"),
        Map.entry("product-engineering-services",      "/images/services/product-engineering-services-banner.jpg"),
        Map.entry("platforms-and-protocols-xaap",      "/images/services/platforms-and-protocols-xaap-banner.jpg"),
        Map.entry("salesforce-consulting-and-services-coe", "/images/services/salesforce-consulting-and-services-coe-banner.jpg"),
        Map.entry("gcp-services",                      "/images/services/gcp-services-banner.jpg"),
        Map.entry("vmware-tanzu-services",             "/images/services/vmware-tanzu-services-banner.jpg"),
        Map.entry("enterprise-agency-platform-tria",   "/images/services/enterprise-agency-platform-tria-banner.jpg"),
        Map.entry("product-line-mphasis-modernize",    "/images/services/product-line-mphasis-modernize-banner.jpg"),
        Map.entry("product-line-mphasis-optimize",     "/images/services/product-line-mphasis-optimize-banner.jpg")
    );

    private static final Map<String, String> INDUSTRY_FIXES = Map.of(
        "banking-financial-services", "/images/industries/banking-financial-services-banner.jpg",
        "telecommunications",         "/images/industries/telecommunications-banner.jpg",
        "retail-ecommerce",           "/images/industries/retail-ecommerce-banner.jpg",
        "media-entertainment",        "/images/industries/media-entertainment-banner.jpg"
    );

    public BannerUrlMigration(ServiceRepository serviceRepository,
                               IndustryRepository industryRepository) {
        this.serviceRepository = serviceRepository;
        this.industryRepository = industryRepository;
    }

    @Override
    public void run(String... args) {
        int serviceFixed = 0;
        for (var entry : SERVICE_FIXES.entrySet()) {
            var opt = serviceRepository.findBySlug(entry.getKey());
            if (opt.isPresent()) {
                var svc = opt.get();
                if (!entry.getValue().equals(svc.getBannerUrl())) {
                    log.info("Fixing service bannerUrl [{}]: {} → {}", svc.getSlug(), svc.getBannerUrl(), entry.getValue());
                    svc.setBannerUrl(entry.getValue());
                    serviceRepository.save(svc);
                    serviceFixed++;
                }
            } else {
                log.warn("Service slug not found during banner migration: {}", entry.getKey());
            }
        }

        int industryFixed = 0;
        for (var entry : INDUSTRY_FIXES.entrySet()) {
            var opt = industryRepository.findBySlug(entry.getKey());
            if (opt.isPresent()) {
                var ind = opt.get();
                if (!entry.getValue().equals(ind.getBannerUrl())) {
                    log.info("Fixing industry bannerUrl [{}]: {} → {}", ind.getSlug(), ind.getBannerUrl(), entry.getValue());
                    ind.setBannerUrl(entry.getValue());
                    industryRepository.save(ind);
                    industryFixed++;
                }
            } else {
                log.warn("Industry slug not found during banner migration: {}", entry.getKey());
            }
        }

        if (serviceFixed > 0 || industryFixed > 0) {
            log.info("BannerUrlMigration complete — {} service(s) and {} industry(s) updated.", serviceFixed, industryFixed);
        } else {
            log.info("BannerUrlMigration — all bannerUrls already correct, no updates needed.");
        }
    }
}
