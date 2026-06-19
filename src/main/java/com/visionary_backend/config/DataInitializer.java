package com.visionary_backend.config;

import com.visionary_backend.entity.*;
import com.visionary_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final StatRepository statRepository;
    private final ClientRepository clientRepository;
    private final TestimonialRepository testimonialRepository;
    private final AwardRepository awardRepository;
    private final ServiceRepository serviceRepository;
    private final IndustryRepository industryRepository;
    private final InsightRepository insightRepository;
    private final JobRepository jobRepository;
    private final OfficeLocationRepository officeLocationRepository;
    private final LeaderRepository leaderRepository;
    private final MortgageServiceRepository mortgageServiceRepository;
    private final CompanySettingsRepository companySettingsRepository;
    private final NavigationLinkRepository navigationLinkRepository;
    private final CtaSectionRepository ctaSectionRepository;
    private final HeroSectionRepository heroSectionRepository;

    public DataInitializer(StatRepository statRepository,
                           ClientRepository clientRepository,
                           TestimonialRepository testimonialRepository,
                           AwardRepository awardRepository,
                           ServiceRepository serviceRepository,
                           IndustryRepository industryRepository,
                           InsightRepository insightRepository,
                           JobRepository jobRepository,
                           OfficeLocationRepository officeLocationRepository,
                           LeaderRepository leaderRepository,
                           MortgageServiceRepository mortgageServiceRepository,
                           CompanySettingsRepository companySettingsRepository,
                           NavigationLinkRepository navigationLinkRepository,
                           CtaSectionRepository ctaSectionRepository,
                           HeroSectionRepository heroSectionRepository) {
        this.statRepository = statRepository;
        this.clientRepository = clientRepository;
        this.testimonialRepository = testimonialRepository;
        this.awardRepository = awardRepository;
        this.serviceRepository = serviceRepository;
        this.industryRepository = industryRepository;
        this.insightRepository = insightRepository;
        this.jobRepository = jobRepository;
        this.officeLocationRepository = officeLocationRepository;
        this.leaderRepository = leaderRepository;
        this.mortgageServiceRepository = mortgageServiceRepository;
        this.companySettingsRepository = companySettingsRepository;
        this.navigationLinkRepository = navigationLinkRepository;
        this.ctaSectionRepository = ctaSectionRepository;
        this.heroSectionRepository = heroSectionRepository;
    }

    @Override
    public void run(String... args) {
        seedStats();
        seedClients();
        seedTestimonials();
        seedAwards();
        seedServices();
        seedIndustries();
        seedInsights();
        seedJobs();
        seedOfficeLocations();
        seedLeaders();
        seedMortgageServices();
        seedCompanySettings();
        seedNavigationLinks();
        seedCtaSections();
        seedHeroSections();
    }

    // ─── Stats ────────────────────────────────────────────────────────────────

    private void seedStats() {
        if (statRepository.count() > 0) return;

        // ── context: home (home page stats strip) ────────────────────────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Clients Served").value("20").suffix("+").sub(null).context("home").displayOrder(1).build(),
                Stat.builder().label("Employees").value("11").suffix("K+").sub(null).context("home").displayOrder(2).build(),
                Stat.builder().label("Years of Experience").value("4").suffix("+").sub(null).context("home").displayOrder(3).build()
        ));

        // ── context: global (trust strip on service/mortgage/industry detail pages) ─
        statRepository.saveAll(List.of(
                Stat.builder().label("Global Clients").value("20+").suffix(null).sub("Across industries").context("global").displayOrder(1).build(),
                Stat.builder().label("Client Retention").value("99%").suffix(null).sub("Year-on-year").context("global").displayOrder(2).build(),
                Stat.builder().label("Global Delivery").value("24/7").suffix(null).sub("Follow-the-sun model").context("global").displayOrder(3).build(),
                Stat.builder().label("Years of Expertise").value("4+").suffix(null).sub("Enterprise-grade delivery").context("global").displayOrder(4).build()
        ));

        // ── context: about_overview (About page overview panel) ──────────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Years of enterprise expertise").value("4").suffix("+").sub(null).context("about_overview").displayOrder(1).build(),
                Stat.builder().label("Global enterprise clients").value("20").suffix("+").sub(null).context("about_overview").displayOrder(2).build(),
                Stat.builder().label("Professionals worldwide").value("11").suffix("K+").sub(null).context("about_overview").displayOrder(3).build()
        ));

        // ── context: about_global (About page global presence panel) ─────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Office Locations").value("3").suffix(null).sub(null).context("about_global").displayOrder(1).build(),
                Stat.builder().label("Delivery centres").value("20").suffix("+").sub(null).context("about_global").displayOrder(2).build(),
                Stat.builder().label("Nationalities").value("10").suffix("+").sub(null).context("about_global").displayOrder(3).build(),
                Stat.builder().label("Global coverage").value("24/7").suffix(null).sub(null).context("about_global").displayOrder(4).build()
        ));

        // ── context: careers (Careers page stats strip) ──────────────────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Employees globally").value("11").suffix("K+").sub(null).context("careers").displayOrder(1).build(),
                Stat.builder().label("Office locations").value("3").suffix("+").sub(null).context("careers").displayOrder(2).build(),
                Stat.builder().label("Nationalities represented").value("10").suffix("+").sub(null).context("careers").displayOrder(3).build(),
                Stat.builder().label("Internal promotion rate").value("68").suffix("%").sub(null).context("careers").displayOrder(4).build()
        ));

        // ── context: industry_bfs (Banking & Financial Services highlights) ───
        statRepository.saveAll(List.of(
                Stat.builder().label("Banking & insurance clients").value("5+").suffix(null).sub(null).context("industry_bfs").displayOrder(1).build(),
                Stat.builder().label("Regulatory compliance rate").value("99%").suffix(null).sub(null).context("industry_bfs").displayOrder(2).build(),
                Stat.builder().label("Reduction in onboarding drop-off").value("40%").suffix(null).sub(null).context("industry_bfs").displayOrder(3).build(),
                Stat.builder().label("Fraud operations coverage").value("24/7").suffix(null).sub(null).context("industry_bfs").displayOrder(4).build()
        ));

        // ── context: industry_healthcare (Healthcare highlights) ─────────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Healthcare clients across payer & provider").value("4+").suffix(null).sub(null).context("industry_healthcare").displayOrder(1).build(),
                Stat.builder().label("Claims accuracy rate").value("98%").suffix(null).sub(null).context("industry_healthcare").displayOrder(2).build(),
                Stat.builder().label("Reduction in prior auth turnaround").value("35%").suffix(null).sub(null).context("industry_healthcare").displayOrder(3).build(),
                Stat.builder().label("Fully compliant operations").value("HIPAA").suffix(null).sub(null).context("industry_healthcare").displayOrder(4).build()
        ));

        // ── context: industry_telecom (Telecommunications highlights) ────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Telecom operators supported").value("3+").suffix(null).sub(null).context("industry_telecom").displayOrder(1).build(),
                Stat.builder().label("First-call resolution improvement").value("60%").suffix(null).sub(null).context("industry_telecom").displayOrder(2).build(),
                Stat.builder().label("Reduction in churn through retention").value("45%").suffix(null).sub(null).context("industry_telecom").displayOrder(3).build(),
                Stat.builder().label("Ready delivery frameworks").value("5G").suffix(null).sub(null).context("industry_telecom").displayOrder(4).build()
        ));

        // ── context: industry_retail (Retail & E-commerce highlights) ────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Retail & e-commerce clients").value("5+").suffix(null).sub(null).context("industry_retail").displayOrder(1).build(),
                Stat.builder().label("Order processing accuracy").value("99.5%").suffix(null).sub(null).context("industry_retail").displayOrder(2).build(),
                Stat.builder().label("Peak season scale-up capacity").value("3\u00d7").suffix(null).sub(null).context("industry_retail").displayOrder(3).build(),
                Stat.builder().label("Average seller onboarding time").value("48h").suffix(null).sub(null).context("industry_retail").displayOrder(4).build()
        ));

        // ── context: industry_media (Media & Entertainment highlights) ────────
        statRepository.saveAll(List.of(
                Stat.builder().label("Languages moderated").value("10+").suffix(null).sub(null).context("industry_media").displayOrder(1).build(),
                Stat.builder().label("Content review accuracy").value("99.5%").suffix(null).sub(null).context("industry_media").displayOrder(2).build(),
                Stat.builder().label("Streaming platform clients").value("3+").suffix(null).sub(null).context("industry_media").displayOrder(3).build(),
                Stat.builder().label("Escalation response time").value("<2h").suffix(null).sub(null).context("industry_media").displayOrder(4).build()
        ));
    }

    // ─── Clients ──────────────────────────────────────────────────────────────

    private void seedClients() {
        if (clientRepository.count() > 0) return;
        clientRepository.saveAll(List.of(
                Client.builder().name("Citi").logoUrl("/images/clients/citi.png").displayOrder(1).build(),
                Client.builder().name("HSBC").logoUrl("/images/clients/hsbc.png").displayOrder(2).build(),
                Client.builder().name("Aetna").logoUrl("/images/clients/aetna.png").displayOrder(3).build(),
                Client.builder().name("T-Mobile").logoUrl("/images/clients/tmobile.png").displayOrder(4).build(),
                Client.builder().name("Comcast").logoUrl("/images/clients/comcast.png").displayOrder(5).build(),
                Client.builder().name("American Express").logoUrl("/images/clients/amex.png").displayOrder(6).build(),
                Client.builder().name("Vodafone").logoUrl("/images/clients/vodafone.png").displayOrder(7).build(),
                Client.builder().name("Walgreens").logoUrl("/images/clients/walgreens.png").displayOrder(8).build()
        ));
    }

    // ─── Testimonials ─────────────────────────────────────────────────────────

    private void seedTestimonials() {
        if (testimonialRepository.count() > 0) return;
        testimonialRepository.saveAll(List.of(
                Testimonial.builder()
                        .quote("Visionary Inspire transformed our customer service operations. Their team delivered measurable improvements in first-call resolution within the first quarter.")
                        .authorName("Sarah Mitchell").authorTitle("VP of Customer Operations").company("Citi").build(),
                Testimonial.builder()
                        .quote("Their data analytics practice helped us identify revenue leakage we had been missing for years. The ROI was evident within six months of engagement.")
                        .authorName("James Thornton").authorTitle("Chief Data Officer").company("HSBC").build(),
                Testimonial.builder()
                        .quote("We engaged Visionary Inspire for a large-scale digital transformation programme and the results exceeded our expectations on every key metric.")
                        .authorName("Priya Sharma").authorTitle("Director of Digital Strategy").company("Aetna").build(),
                Testimonial.builder()
                        .quote("The automation solutions delivered by their intelligent automation team cut our processing time by 60%, freeing our staff to focus on higher-value work.")
                        .authorName("Michael Brennan").authorTitle("Head of Operations").company("T-Mobile").build(),
                Testimonial.builder()
                        .quote("Their trust and safety team provided round-the-clock content moderation that scaled with our platform growth without compromising quality.")
                        .authorName("Linda Chen").authorTitle("Head of Trust & Safety").company("Comcast").build(),
                Testimonial.builder()
                        .quote("Visionary Inspire brought genuine domain expertise to our healthcare BPO engagement. Their team understood compliance requirements from day one.")
                        .authorName("Dr. Robert Haines").authorTitle("COO").company("Aetna").build()
        ));
    }

    // ─── Awards ───────────────────────────────────────────────────────────────

    private void seedAwards() {
        if (awardRepository.count() > 0) return;
        awardRepository.saveAll(List.of(
                Award.builder().title("Best BPO Company of the Year").year(2024).logoUrl("/images/awards/bpo-award.png").displayOrder(1).build(),
                Award.builder().title("Everest Group PEAK Matrix Leader").year(2024).logoUrl("/images/awards/everest.png").displayOrder(2).build(),
                Award.builder().title("Gartner Magic Quadrant Visionary").year(2023).logoUrl("/images/awards/gartner.png").displayOrder(3).build(),
                Award.builder().title("ISG Star of Excellence Award").year(2023).logoUrl("/images/awards/isg.png").displayOrder(4).build(),
                Award.builder().title("Great Place to Work Certified").year(2024).logoUrl("/images/awards/gptw.png").displayOrder(5).build()
        ));
    }

    // ─── Services ─────────────────────────────────────────────────────────────

    @Transactional
    void seedServices() {
        if (serviceRepository.findBySlug("nextops-business-process-services")
                .map(s -> "Operations & BPO".equals(s.getCategory()))
                .orElse(false)) {
            return;
        }
        serviceRepository.deleteAll();

        // ═══════════════════════════════════════════════════════════════════
        // CATEGORY: Application Services  (1–7)
        // ═══════════════════════════════════════════════════════════════════

        // ── 1. Application Services ──────────────────────────────────────────
        // ── 1. Application Services
        ServiceEntity s01 = ServiceEntity.builder()
                .slug("application-services").title("Application Services")
                .description("We deliver end-to-end application services spanning design, build, integration, and support — helping enterprises create resilient, scalable software that drives competitive advantage. Our cross-functional teams combine deep engineering expertise with agile delivery to reduce time-to-market and lower total cost of ownership across your application portfolio.")
                .iconUrl("/images/services/app-services.svg").bannerUrl("/images/services/app-services-banner.jpg")
                .category("Application Services").displayOrder(1).build();
        s01.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("End-to-End Delivery").description("Full lifecycle coverage from requirements and architecture through development, testing, and production support.").service(s01).build(),
                ServiceBenefit.builder().title("Agile Engineering").description("Sprint-based delivery with continuous integration and automated pipelines that compress release cycles.").service(s01).build(),
                ServiceBenefit.builder().title("Cloud-Native Architecture").description("Microservices and containerised designs built for elasticity, resilience, and hyperscaler deployment.").service(s01).build(),
                ServiceBenefit.builder().title("Legacy Integration").description("API-led connectors and middleware that bridge modern applications with existing enterprise systems without disruptive rewrites.").service(s01).build(),
                ServiceBenefit.builder().title("Managed Application Support").description("SLA-governed run and maintain services keeping critical applications available, secure, and continuously improved.").service(s01).build()
        ));

        // ── 2. NextOps – Business Process Services
        ServiceEntity s03 = ServiceEntity.builder()
                .slug("nextops-business-process-services").title("NextOps – Business Process Services")
                .description("NextOps is our next-generation business process services model that combines intelligent automation, AI-assisted decisioning, and analytics-driven continuous improvement to deliver operations that are faster, cheaper, and more accurate than traditional BPO. We reimagine core enterprise processes rather than simply lift-and-shift them offshore.")
                .iconUrl("/images/services/nextops.svg").bannerUrl("/images/services/nextops-banner.jpg")
                .category("Operations & BPO").displayOrder(3).build();
        s03.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Intelligent Process Automation").description("RPA, AI, and decision engines working in concert to eliminate manual effort from high-volume transactional processes.").service(s03).build(),
                ServiceBenefit.builder().title("Analytics-Led Improvement").description("Real-time process mining and performance dashboards that continuously surface optimisation opportunities.").service(s03).build(),
                ServiceBenefit.builder().title("Outcome-Based Commercials").description("Pricing models tied to business outcomes — cost per transaction, error rates, and throughput — aligning incentives with client value.").service(s03).build(),
                ServiceBenefit.builder().title("Scalable Delivery Model").description("Elastic capacity that scales instantly with business demand without proportional headcount increases.").service(s03).build(),
                ServiceBenefit.builder().title("Compliance by Design").description("Built-in controls, audit trails, and governance frameworks ensuring every automated process meets regulatory standards.").service(s03).build()
        ));

        // ── 4. Cognitive
        ServiceEntity s04 = ServiceEntity.builder()
                .slug("cognitive").title("Cognitive")
                .description("We deploy cognitive computing capabilities — including natural language processing, machine vision, and knowledge graph technologies — to automate complex judgement-intensive tasks that previously required human expertise. Our cognitive solutions learn continuously, improving accuracy with every interaction and unlocking new levels of enterprise productivity.")
                .iconUrl("/images/services/cognitive.svg").bannerUrl("/images/services/cognitive-banner.jpg")
                .category("AI & Automation").displayOrder(4).build();
        s04.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Intelligent Document Processing").description("Cognitive extraction and classification of unstructured documents with human-level accuracy at enterprise scale.").service(s04).build(),
                ServiceBenefit.builder().title("Natural Language Understanding").description("Deep NLP pipelines for sentiment analysis, intent recognition, and entity extraction across customer and operational data.").service(s04).build(),
                ServiceBenefit.builder().title("Knowledge Graph Construction").description("Enterprise knowledge graphs that connect disparate data sources, enabling richer search and contextual recommendations.").service(s04).build(),
                ServiceBenefit.builder().title("Conversational AI").description("Intelligent virtual agents and chatbots that handle complex, multi-turn dialogues across customer and employee channels.").service(s04).build(),
                ServiceBenefit.builder().title("Continuous Learning").description("Feedback loops and retraining pipelines ensuring cognitive models improve accuracy as they process more enterprise data.").service(s04).build()
        ));

        // ── 5. Cyber Security
        ServiceEntity s05 = ServiceEntity.builder()
                .slug("cyber-security").title("Cyber Security")
                .description("We protect enterprise digital estates through a comprehensive suite of cybersecurity services — spanning threat detection and response, identity management, vulnerability governance, and regulatory compliance. Our security professionals operate as an extension of your team, building a proactive, resilient security posture that keeps pace with an evolving threat landscape.")
                .iconUrl("/images/services/cyber-security.svg").bannerUrl("/images/services/cyber-security-banner.jpg")
                .category("Governance & Infrastructure").displayOrder(5).build();
        s05.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("24/7 Security Operations").description("Round-the-clock threat monitoring, detection, and incident response powered by SIEM and SOAR platforms.").service(s05).build(),
                ServiceBenefit.builder().title("Zero-Trust Identity Management").description("MFA, PAM, and lifecycle governance frameworks that eliminate credential-based attack vectors.").service(s05).build(),
                ServiceBenefit.builder().title("Vulnerability & Patch Management").description("Continuous scanning, risk-prioritised remediation, and patch automation across hybrid infrastructure.").service(s05).build(),
                ServiceBenefit.builder().title("Penetration Testing").description("Simulated adversarial attacks that expose exploitable weaknesses before real threat actors can act on them.").service(s05).build(),
                ServiceBenefit.builder().title("Compliance Assurance").description("Audit-ready controls and evidence packages for ISO 27001, SOC 2, NIST, and sector-specific security frameworks.").service(s05).build()
        ));

        // ── 6. IT Value Stream Acceleration – DevOps Services
        ServiceEntity s06 = ServiceEntity.builder()
                .slug("it-value-stream-acceleration-devops").title("IT Value Stream Acceleration – DevOps Services")
                .description("We help enterprises eliminate waste across their IT value stream by embedding DevOps principles, lean engineering practices, and flow-based delivery models that dramatically reduce lead times from idea to production. Our value stream acceleration engagements combine VSM analysis, toolchain modernisation, and cultural transformation to make software delivery a competitive differentiator.")
                .iconUrl("/images/services/devops-vsa.svg").bannerUrl("/images/services/devops-vsa-banner.jpg")
                .category("Cloud & Digital").displayOrder(6).build();
        s06.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Value Stream Mapping").description("Structured analysis that identifies bottlenecks, handoff delays, and waste across the entire software delivery pipeline.").service(s06).build(),
                ServiceBenefit.builder().title("CI/CD Pipeline Modernisation").description("Automated build, test, and deployment pipelines that enforce quality gates and cut release cycle times by up to 70%.").service(s06).build(),
                ServiceBenefit.builder().title("Platform Engineering").description("Self-service developer platforms that reduce cognitive load and give teams golden paths to production.").service(s06).build(),
                ServiceBenefit.builder().title("Flow Metrics & DORA Tracking").description("Deployment frequency, lead time, change failure rate, and MTTR dashboards providing objective delivery performance visibility.").service(s06).build(),
                ServiceBenefit.builder().title("DevOps Culture Coaching").description("Embedded coaches and workshops that build lasting DevOps capability within your engineering organisation.").service(s06).build()
        ));

        // ── 7. DevOps Automation Services
        ServiceEntity s07 = ServiceEntity.builder()
                .slug("devops-automation-services").title("DevOps Automation Services")
                .description("We design and implement the automation fabric that makes modern DevOps possible — from infrastructure provisioning and configuration management to release orchestration and policy-as-code enforcement. Our DevOps automation practice eliminates manual gates, reduces human error, and ensures every environment is consistent, auditable, and deployable on demand.")
                .iconUrl("/images/services/devops-auto.svg").bannerUrl("/images/services/devops-auto-banner.jpg")
                .category("Cloud & Digital").displayOrder(7).build();
        s07.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Infrastructure as Code").description("Terraform, Ansible, and Pulumi-based automation ensuring consistent, version-controlled environment provisioning.").service(s07).build(),
                ServiceBenefit.builder().title("Release Orchestration").description("End-to-end release pipelines with automated approvals, rollback triggers, and blue-green deployment strategies.").service(s07).build(),
                ServiceBenefit.builder().title("Policy as Code").description("Automated compliance and security policy enforcement integrated into every pipeline stage — no manual gate-keeping required.").service(s07).build(),
                ServiceBenefit.builder().title("Test Automation Integration").description("Unit, integration, performance, and security tests wired into CI pipelines for continuous quality assurance.").service(s07).build(),
                ServiceBenefit.builder().title("Observability & Feedback Loops").description("Automated alerting, distributed tracing, and deployment health dashboards that close the feedback loop from production to development.").service(s07).build()
        ));

        // ── 8. AI
        ServiceEntity s08 = ServiceEntity.builder()
                .slug("ai").title("AI")
                .description("We help enterprises move beyond AI experimentation to production-grade deployment — building the models, infrastructure, and governance required to generate measurable business value at scale. Our AI practice covers the full lifecycle: use case prioritisation, data engineering, model development, MLOps, and responsible AI controls.")
                .iconUrl("/images/services/ai.svg").bannerUrl("/images/services/ai-banner.jpg")
                .category("AI & Automation").displayOrder(8).build();
        s08.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("AI Use Case Discovery").description("Structured workshops that identify and size the highest-value AI opportunities across your business functions.").service(s08).build(),
                ServiceBenefit.builder().title("Machine Learning Engineering").description("End-to-end model development from feature engineering and training through to evaluation and production deployment.").service(s08).build(),
                ServiceBenefit.builder().title("MLOps & Model Governance").description("Automated retraining pipelines, drift detection, and model lifecycle management ensuring sustained production performance.").service(s08).build(),
                ServiceBenefit.builder().title("NLP & Computer Vision").description("Purpose-built language and vision models that automate document processing, quality inspection, and customer interaction.").service(s08).build(),
                ServiceBenefit.builder().title("Responsible AI Framework").description("Explainability, bias testing, and audit controls ensuring AI systems operate ethically and within regulatory boundaries.").service(s08).build(),
                ServiceBenefit.builder().title("AI Integration").description("Seamless embedding of AI capabilities into existing enterprise applications, workflows, and data platforms.").service(s08).build()
        ));

        // ── 9. Enterprise Automation
        ServiceEntity s09 = ServiceEntity.builder()
                .slug("enterprise-automation").title("Enterprise Automation")
                .description("We deliver intelligent automation programmes that combine RPA, AI, process mining, and low-code orchestration to eliminate manual effort across the enterprise. Our automation factory model provides rapid deployment of digital workers that operate 24/7 with measurable accuracy, freeing your skilled workforce for higher-value activities that drive growth.")
                .iconUrl("/images/services/enterprise-auto.svg").bannerUrl("/images/services/enterprise-auto-banner.jpg")
                .category("AI & Automation").displayOrder(9).build();
        s09.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("RPA at Scale").description("Enterprise-grade robotic process automation on UiPath, Automation Anywhere, and Blue Prism — deployed and managed by certified specialists.").service(s09).build(),
                ServiceBenefit.builder().title("Process Mining").description("Data-driven discovery of automation opportunities by analysing event logs to identify inefficiencies and bottlenecks.").service(s09).build(),
                ServiceBenefit.builder().title("Intelligent Document Automation").description("AI-powered extraction and processing of invoices, contracts, and forms with near-zero manual intervention.").service(s09).build(),
                ServiceBenefit.builder().title("Automation CoE Setup").description("Design and establishment of an internal Centre of Excellence with governance, pipeline, and operating model.").service(s09).build(),
                ServiceBenefit.builder().title("ROI Measurement").description("Transparent tracking of cost savings, error reduction, and FTE reallocation attributable to each automation deployment.").service(s09).build()
        ));

        // ── 10. Experience Design
        ServiceEntity s10 = ServiceEntity.builder()
                .slug("experience-design").title("Experience Design")
                .description("We design human-centred digital experiences that delight users, drive adoption, and deliver measurable business outcomes. Our experience design practice integrates UX research, service design, and product design into every stage of the development lifecycle — ensuring that technology investments translate into experiences people actually want to use.")
                .iconUrl("/images/services/experience-design.svg").bannerUrl("/images/services/experience-design-banner.jpg")
                .category("Application Services").displayOrder(10).build();
        s10.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("UX Research & Insight").description("User interviews, usability testing, and behavioural analytics that ground every design decision in real user evidence.").service(s10).build(),
                ServiceBenefit.builder().title("Service Design").description("End-to-end service blueprinting that aligns front-stage experiences with back-office operations and technology.").service(s10).build(),
                ServiceBenefit.builder().title("Interaction & Visual Design").description("Pixel-precise UI design systems, component libraries, and accessibility-compliant interfaces built for scale.").service(s10).build(),
                ServiceBenefit.builder().title("Prototyping & Validation").description("Rapid interactive prototypes validated with real users before a single line of production code is written.").service(s10).build(),
                ServiceBenefit.builder().title("Design Operations").description("DesignOps frameworks, tooling standards, and governance models that scale design capability across large engineering organisations.").service(s10).build()
        ));

        // ── 11. Governance, Risk & Compliance
        ServiceEntity s11 = ServiceEntity.builder()
                .slug("governance-risk-and-compliance").title("Governance, Risk & Compliance")
                .description("We help enterprises build robust GRC frameworks that manage risk systematically, satisfy regulatory obligations, and embed governance into day-to-day operations. Our GRC practice combines policy design, risk quantification, control implementation, and audit readiness — giving leadership the confidence to operate in complex, highly regulated environments.")
                .iconUrl("/images/services/grc.svg").bannerUrl("/images/services/grc-banner.jpg")
                .category("Governance & Infrastructure").displayOrder(11).build();
        s11.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Enterprise Risk Management").description("Structured risk identification, quantification, and treatment frameworks aligned to ISO 31000 and COSO standards.").service(s11).build(),
                ServiceBenefit.builder().title("Regulatory Compliance Management").description("Compliance programme design and monitoring for GDPR, SOX, PCI-DSS, HIPAA, and sector-specific mandates.").service(s11).build(),
                ServiceBenefit.builder().title("Internal Audit Support").description("Risk-based audit planning, fieldwork execution, and findings management that strengthen the control environment.").service(s11).build(),
                ServiceBenefit.builder().title("Policy & Control Framework").description("Enterprise policy hierarchies and control libraries that make governance tangible, measurable, and consistently applied.").service(s11).build(),
                ServiceBenefit.builder().title("Third-Party Risk Management").description("Vendor due diligence, ongoing monitoring, and contractual governance protecting against supply chain risk.").service(s11).build()
        ));

        // ── 12. Infrastructure Services
        ServiceEntity s12 = ServiceEntity.builder()
                .slug("infrastructure-services").title("Infrastructure Services")
                .description("We design, deploy, and manage the enterprise infrastructure that modern digital businesses depend on — from data centre and hybrid cloud environments to network, storage, and compute platforms. Our infrastructure services deliver the reliability, security, and performance that mission-critical workloads demand, backed by 24/7 managed operations and proactive capacity governance.")
                .iconUrl("/images/services/infrastructure.svg").bannerUrl("/images/services/infrastructure-banner.jpg")
                .category("Governance & Infrastructure").displayOrder(12).build();
        s12.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Hybrid Infrastructure Management").description("Unified management of on-premise, colocation, and public cloud infrastructure through a single operational model.").service(s12).build(),
                ServiceBenefit.builder().title("24/7 Managed Operations").description("Round-the-clock monitoring, incident response, and change management keeping infrastructure performant and available.").service(s12).build(),
                ServiceBenefit.builder().title("Infrastructure Automation").description("IaC-driven provisioning and configuration management eliminating manual effort and configuration drift.").service(s12).build(),
                ServiceBenefit.builder().title("Capacity & Cost Governance").description("Right-sizing analysis, reserved capacity planning, and FinOps practices that maximise infrastructure ROI.").service(s12).build(),
                ServiceBenefit.builder().title("Disaster Recovery & Resilience").description("Tested DR runbooks, RTO/RPO-aligned architectures, and automated failover ensuring business continuity.").service(s12).build()
        ));

        // ── 13. Microsoft COE
        ServiceEntity s13 = ServiceEntity.builder()
                .slug("microsoft-coe").title("Microsoft COE")
                .description("Our Microsoft Centre of Excellence delivers deep specialisation across the full Microsoft technology stack — from Azure infrastructure and data platforms to Microsoft 365, Dynamics 365, and Power Platform. We combine certified Microsoft expertise with enterprise delivery experience to help organisations maximise their Microsoft investment and accelerate digital transformation.")
                .iconUrl("/images/services/microsoft-coe.svg").bannerUrl("/images/services/microsoft-coe-banner.jpg")
                .category("Governance & Infrastructure").displayOrder(13).build();
        s13.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Azure Architecture & Migration").description("Well-Architected Azure solutions and migration execution covering IaaS, PaaS, and serverless workloads.").service(s13).build(),
                ServiceBenefit.builder().title("Microsoft 365 & Modern Workplace").description("Teams, SharePoint, and Copilot for M365 deployments that drive collaboration, productivity, and adoption.").service(s13).build(),
                ServiceBenefit.builder().title("Dynamics 365 Implementation").description("CRM and ERP implementations on Dynamics 365 Sales, Customer Service, Finance, and Supply Chain modules.").service(s13).build(),
                ServiceBenefit.builder().title("Power Platform Development").description("Low-code applications, automated workflows, and analytical dashboards built on Power Apps, Power Automate, and Power BI.").service(s13).build(),
                ServiceBenefit.builder().title("Azure AI & Cognitive Services").description("Enterprise AI solutions built on Azure OpenAI, AI Search, and Cognitive Services for intelligent automation and analytics.").service(s13).build()
        ));

        // ── 14. AWS Services
        ServiceEntity s14 = ServiceEntity.builder()
                .slug("aws-services").title("AWS Services")
                .description("As an AWS Partner, we help enterprises maximise the value of the Amazon Web Services platform — from architecture design and workload migration through to managed operations, cost governance, and cloud-native development. Our AWS-certified engineers bring deep service expertise and a proven track record of enterprise AWS transformations across regulated and high-scale environments.")
                .iconUrl("/images/services/aws.svg").bannerUrl("/images/services/aws-banner.jpg")
                .category("Governance & Infrastructure").displayOrder(14).build();
        s14.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("AWS Well-Architected Reviews").description("Independent assessment of your AWS workloads against the six pillars of the Well-Architected Framework with a remediation roadmap.").service(s14).build(),
                ServiceBenefit.builder().title("AWS Migration & Modernisation").description("End-to-end planning and execution of workload migrations to AWS using proven MAP methodology and tooling.").service(s14).build(),
                ServiceBenefit.builder().title("AWS Managed Services").description("24/7 operational management of your AWS estate including patching, monitoring, incident response, and change management.").service(s14).build(),
                ServiceBenefit.builder().title("AWS Data & AI").description("Data lake, analytics pipeline, and AI/ML implementations on Amazon S3, Redshift, SageMaker, and Bedrock.").service(s14).build(),
                ServiceBenefit.builder().title("AWS Cost Optimisation").description("FinOps practices, reserved instance planning, and tagging governance maximising return on your AWS investment.").service(s14).build(),
                ServiceBenefit.builder().title("AWS Security Baseline").description("GuardDuty, Security Hub, and compliance automation ensuring your AWS environment meets enterprise security standards.").service(s14).build()
        ));

        // ── 15. Azure Services
        ServiceEntity s15 = ServiceEntity.builder()
                .slug("azure-services").title("Azure Services")
                .description("As a Microsoft Azure Partner, we help enterprises unlock the full potential of the Azure ecosystem — spanning infrastructure, data, AI, security, and modern application development. Our Azure practice delivers end-to-end services from initial architecture through to ongoing managed operations, with deep specialisation in Azure OpenAI, Synapse Analytics, and enterprise identity.")
                .iconUrl("/images/services/azure.svg").bannerUrl("/images/services/azure-banner.jpg")
                .category("Governance & Infrastructure").displayOrder(15).build();
        s15.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Azure Architecture & Design").description("Secure, scalable, cost-efficient Azure landing zones and workload architectures designed by certified cloud architects.").service(s15).build(),
                ServiceBenefit.builder().title("Azure Migration Services").description("Assessment, planning, and execution of workload migrations to Azure using Azure Migrate and proven methodology.").service(s15).build(),
                ServiceBenefit.builder().title("Azure OpenAI & AI Services").description("Enterprise-grade generative AI solutions built on Azure OpenAI Service, Cognitive Services, and AI Search.").service(s15).build(),
                ServiceBenefit.builder().title("Azure Data Platform").description("Enterprise data platforms on Azure Synapse, Data Factory, Databricks, and Microsoft Fabric.").service(s15).build(),
                ServiceBenefit.builder().title("Azure Managed Operations").description("Proactive 24/7 management of your Azure estate with monitoring, patching, and governance enforcement.").service(s15).build(),
                ServiceBenefit.builder().title("Azure Security & Compliance").description("Defender for Cloud, Sentinel SIEM, and policy-as-code controls securing regulated Azure workloads.").service(s15).build()
        ));

        // ── 16. Modernization
        ServiceEntity s16 = ServiceEntity.builder()
                .slug("modernization").title("Modernization")
                .description("We help enterprises systematically modernise their technology landscape — replacing or re-platforming legacy systems, adopting cloud-native architectures, and embedding modern engineering practices that restore agility and reduce technical debt. Our modernisation factory approach delivers continuous value rather than a single disruptive transformation programme.")
                .iconUrl("/images/services/modernization.svg").bannerUrl("/images/services/modernization-banner.jpg")
                .category("Modernization & Data").displayOrder(16).build();
        s16.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Legacy Assessment & Roadmap").description("Structured technical debt analysis and prioritised modernisation roadmap with clear commercial justification.").service(s16).build(),
                ServiceBenefit.builder().title("Rehost, Refactor & Re-platform").description("Appropriate modernisation patterns applied per application based on business value and migration complexity.").service(s16).build(),
                ServiceBenefit.builder().title("Strangler Fig Approach").description("Incremental replacement reducing big-bang migration risk while delivering continuous capability improvement.").service(s16).build(),
                ServiceBenefit.builder().title("Reduced Total Cost of Ownership").description("Elimination of costly legacy licensing, maintenance, and infrastructure overhead through targeted modernisation.").service(s16).build(),
                ServiceBenefit.builder().title("Accelerated Change Velocity").description("Modern architectures that cut feature lead times and allow faster response to evolving business requirements.").service(s16).build()
        ));

        // ── 17. Next-Gen Data
        ServiceEntity s17 = ServiceEntity.builder()
                .slug("next-gen-data").title("Next-Gen Data")
                .description("We build next-generation data platforms and capabilities that turn enterprise data into a strategic asset — enabling real-time analytics, AI-ready data products, and governed self-service access at scale. Our next-gen data practice spans lakehouse architecture, data mesh implementation, streaming pipelines, and enterprise-wide data quality management.")
                .iconUrl("/images/services/next-gen-data.svg").bannerUrl("/images/services/next-gen-data-banner.jpg")
                .category("Modernization & Data").displayOrder(17).build();
        s17.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Lakehouse Architecture").description("Unified storage and compute platforms combining the flexibility of data lakes with the governance of data warehouses.").service(s17).build(),
                ServiceBenefit.builder().title("Data Mesh Implementation").description("Domain-oriented data ownership models with federated governance enabling scalable, decentralised data products.").service(s17).build(),
                ServiceBenefit.builder().title("Real-Time Streaming").description("Kafka, Flink, and Spark Structured Streaming architectures delivering low-latency data to analytics and AI consumers.").service(s17).build(),
                ServiceBenefit.builder().title("Data Quality at Scale").description("Automated quality rules, anomaly detection, and lineage tracking ensuring trusted data across the entire enterprise.").service(s17).build(),
                ServiceBenefit.builder().title("AI-Ready Data Products").description("Curated, governed feature stores and data products purpose-built for machine learning and advanced analytics workloads.").service(s17).build()
        ));

        // ── 18. Agile IT Operations
        ServiceEntity s18 = ServiceEntity.builder()
                .slug("agile-it-operations").title("Agile IT Operations")
                .description("We transform traditional IT operations into agile, product-centric delivery organisations — embedding SRE practices, platform thinking, and continuous improvement cycles that make IT a business enabler rather than a bottleneck. Our agile IT operations model delivers faster incident resolution, proactive problem prevention, and measurable service quality improvement.")
                .iconUrl("/images/services/agile-it-ops.svg").bannerUrl("/images/services/agile-it-ops-banner.jpg")
                .category("Modernization & Data").displayOrder(18).build();
        s18.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Site Reliability Engineering").description("SRE practices including SLO definition, error budget management, and toil reduction delivering measurable reliability improvements.").service(s18).build(),
                ServiceBenefit.builder().title("AIOps Implementation").description("AI-driven event correlation, anomaly detection, and automated remediation cutting mean time to resolve by up to 60%.").service(s18).build(),
                ServiceBenefit.builder().title("ITSM Modernisation").description("ServiceNow and Jira Service Management implementations aligned to agile delivery and self-service expectations.").service(s18).build(),
                ServiceBenefit.builder().title("Observability Engineering").description("Full-stack observability covering metrics, logs, and traces providing complete production visibility.").service(s18).build(),
                ServiceBenefit.builder().title("Continuous Improvement Culture").description("Blameless retrospectives, game days, and chaos engineering practices building operational resilience over time.").service(s18).build()
        ));

        // ── 19. Product Engineering Services
        ServiceEntity s19 = ServiceEntity.builder()
                .slug("product-engineering-services").title("Product Engineering Services")
                .description("We partner with software product companies and enterprises building internal platforms to deliver high-quality, scalable products through embedded engineering teams that combine product thinking with technical depth. Our product engineering practice covers the full product lifecycle from discovery and architecture through to development, launch, and continuous iteration.")
                .iconUrl("/images/services/product-eng.svg").bannerUrl("/images/services/product-eng-banner.jpg")
                .category("Modernization & Data").displayOrder(19).build();
        s19.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Product Discovery").description("Jobs-to-be-done research, opportunity sizing, and product strategy that ensures engineering investment targets real user value.").service(s19).build(),
                ServiceBenefit.builder().title("Embedded Engineering Teams").description("Fully integrated squads combining product management, design, and engineering working as one team with your stakeholders.").service(s19).build(),
                ServiceBenefit.builder().title("Technical Architecture").description("Scalable, maintainable system designs with clear API contracts, data models, and non-functional requirement coverage.").service(s19).build(),
                ServiceBenefit.builder().title("Continuous Delivery").description("Automated release pipelines enabling frequent, low-risk deployments with feature flag control and rollback safety.").service(s19).build(),
                ServiceBenefit.builder().title("Product Analytics").description("Instrumentation, funnel analysis, and A/B testing frameworks that drive evidence-based product decisions post-launch.").service(s19).build()
        ));

        // ── 20. Platforms & Protocols – XAAP
        ServiceEntity s20 = ServiceEntity.builder()
                .slug("platforms-and-protocols-xaap").title("Platforms & Protocols – XAAP")
                .description("XAAP is our accelerated application platform framework that combines pre-built architecture patterns, reusable microservice components, and integrated DevSecOps toolchains to dramatically reduce time-to-market for new digital products. By eliminating boilerplate infrastructure decisions, XAAP lets engineering teams focus entirely on delivering business-differentiating functionality.")
                .iconUrl("/images/services/xaap.svg").bannerUrl("/images/services/xaap-banner.jpg")
                .category("Modernization & Data").displayOrder(20).build();
        s20.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Pre-Built Architecture Patterns").description("Reference architectures for event-driven, microservices, and API-gateway patterns that eliminate repeated design work.").service(s20).build(),
                ServiceBenefit.builder().title("Reusable Service Library").description("Battle-tested microservice components for auth, notifications, payments, and audit — ready to deploy into new products.").service(s20).build(),
                ServiceBenefit.builder().title("Integrated DevSecOps Toolchain").description("Pre-configured CI/CD, security scanning, and observability tooling bundled into every platform deployment.").service(s20).build(),
                ServiceBenefit.builder().title("Rapid Onboarding").description("New engineering teams reach production-ready delivery within days rather than weeks using XAAP scaffolding and documentation.").service(s20).build(),
                ServiceBenefit.builder().title("Vendor-Neutral Design").description("Cloud-agnostic abstractions enabling deployment across AWS, Azure, and GCP without platform lock-in.").service(s20).build()
        ));

        // ── 21. Salesforce Consulting and Services COE
        ServiceEntity s21 = ServiceEntity.builder()
                .slug("salesforce-consulting-and-services-coe").title("Salesforce Consulting and Services COE")
                .description("Our Salesforce Centre of Excellence delivers end-to-end Salesforce advisory, implementation, and managed services across the full Salesforce platform. We combine certified Salesforce architects and developers with deep CRM domain expertise to maximise the return on your Salesforce investment and continuously evolve your platform as business needs change.")
                .iconUrl("/images/services/salesforce-coe.svg").bannerUrl("/images/services/salesforce-coe-banner.jpg")
                .category("Modernization & Data").displayOrder(21).build();
        s21.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Salesforce Implementation").description("Sales Cloud, Service Cloud, Marketing Cloud, and Experience Cloud implementations delivered by certified architects.").service(s21).build(),
                ServiceBenefit.builder().title("Salesforce Integration").description("MuleSoft and native API integration connecting Salesforce to ERP, data warehouses, and third-party applications.").service(s21).build(),
                ServiceBenefit.builder().title("Platform Optimisation").description("Health checks, technical debt remediation, and performance tuning for existing Salesforce orgs.").service(s21).build(),
                ServiceBenefit.builder().title("Managed Evolution").description("Ongoing enhancement and support services ensuring your Salesforce platform adapts to changing business requirements.").service(s21).build(),
                ServiceBenefit.builder().title("Einstein AI & Analytics").description("Salesforce Einstein, CRM Analytics, and Data Cloud implementations delivering AI-powered insights within the CRM.").service(s21).build()
        ));

        // ── 22. GCP Services
        ServiceEntity s22 = ServiceEntity.builder()
                .slug("gcp-services").title("GCP Services")
                .description("As a Google Cloud Partner, we help enterprises adopt and optimise Google Cloud Platform — from initial architecture design and workload migration through to managed operations, data analytics, and AI implementation. Our GCP-certified engineers bring deep platform expertise in BigQuery, Vertex AI, Anthos, and Google Kubernetes Engine.")
                .iconUrl("/images/services/gcp.svg").bannerUrl("/images/services/gcp-banner.jpg")
                .category("Modernization & Data").displayOrder(22).build();
        s22.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("GCP Architecture & Migration").description("Landing zone design and workload migration to GCP using proven methodology and Google Cloud migration tooling.").service(s22).build(),
                ServiceBenefit.builder().title("BigQuery Analytics").description("Serverless data warehouse implementations enabling petabyte-scale analytics with sub-second query performance.").service(s22).build(),
                ServiceBenefit.builder().title("Vertex AI & ML Platform").description("End-to-end ML pipelines on Vertex AI covering model training, deployment, monitoring, and retraining automation.").service(s22).build(),
                ServiceBenefit.builder().title("GKE & Cloud-Native").description("Containerised application deployment on Google Kubernetes Engine with Anthos for hybrid and multi-cloud management.").service(s22).build(),
                ServiceBenefit.builder().title("GCP Managed Operations").description("Proactive management of your GCP estate with Cloud Operations Suite, incident response, and cost optimisation.").service(s22).build()
        ));

        // ── 23. Cloud
        ServiceEntity s23 = ServiceEntity.builder()
                .slug("cloud").title("Cloud")
                .description("We guide enterprises through every stage of their cloud journey — from strategy and business case development through migration execution and post-migration optimisation. Our cloud practice is hyperscaler-agnostic, combining certified expertise across AWS, Azure, and GCP with proven migration factory methodology to deliver speed, predictability, and commercial efficiency at scale.")
                .iconUrl("/images/services/cloud.svg").bannerUrl("/images/services/cloud-banner.jpg")
                .category("Cloud & Digital").displayOrder(23).build();
        s23.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Cloud Strategy & Business Case").description("Platform-agnostic cloud strategy, TCO modelling, and board-ready business cases that justify and sequence investment.").service(s23).build(),
                ServiceBenefit.builder().title("Migration Factory").description("Repeatable, tooling-automated migration execution moving hundreds of workloads with consistent quality and velocity.").service(s23).build(),
                ServiceBenefit.builder().title("Cloud-Native Development").description("Microservices, serverless, and containerised architectures purpose-built for cloud elasticity and operational efficiency.").service(s23).build(),
                ServiceBenefit.builder().title("FinOps & Cost Governance").description("Tagging policies, reserved capacity planning, and anomaly detection maximising financial return on cloud investment.").service(s23).build(),
                ServiceBenefit.builder().title("Multi-Cloud Management").description("Unified operations and governance across AWS, Azure, and GCP through a single platform and operating model.").service(s23).build()
        ));

        // ── 24. Digital
        ServiceEntity s24 = ServiceEntity.builder()
                .slug("digital").title("Digital")
                .description("We partner with enterprises to build digital capabilities that create lasting competitive advantage — reimagining customer journeys, modernising legacy platforms, and embedding data-driven decision making across the organisation. Our digital practice combines strategic advisory with agile delivery to ensure technology investments produce measurable business outcomes.")
                .iconUrl("/images/services/digital.svg").bannerUrl("/images/services/digital-banner.jpg")
                .category("Cloud & Digital").displayOrder(24).build();
        s24.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Digital Strategy").description("Market-grounded digital roadmaps that sequence investment across channels, platforms, and capabilities for maximum impact.").service(s24).build(),
                ServiceBenefit.builder().title("Customer Journey Redesign").description("End-to-end journey mapping and reimagination that removes friction and creates differentiated digital experiences.").service(s24).build(),
                ServiceBenefit.builder().title("Platform Modernisation").description("Systematic migration from monolith to composable, API-first architectures that enable rapid digital innovation.").service(s24).build(),
                ServiceBenefit.builder().title("Digital Operating Model").description("Organisation design, ways of working, and talent strategies that build sustainable internal digital delivery capability.").service(s24).build(),
                ServiceBenefit.builder().title("Data-Driven Personalisation").description("Real-time personalisation engines and recommendation systems that increase engagement and conversion across digital channels.").service(s24).build()
        ));

        // ── 25. VMware Tanzu Services
        ServiceEntity s25 = ServiceEntity.builder()
                .slug("vmware-tanzu-services").title("VMware Tanzu Services")
                .description("We help enterprises modernise their application portfolio and transform application delivery using the VMware Tanzu platform. Our Tanzu practice covers Kubernetes platform engineering, application containerisation, developer experience tooling, and the operational processes needed to run a production-grade Tanzu environment securely and efficiently.")
                .iconUrl("/images/services/vmware-tanzu.svg").bannerUrl("/images/services/vmware-tanzu-banner.jpg")
                .category("Cloud & Digital").displayOrder(25).build();
        s25.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Tanzu Kubernetes Grid").description("Enterprise Kubernetes platform deployment, configuration, and lifecycle management on TKG across on-premise and cloud.").service(s25).build(),
                ServiceBenefit.builder().title("Application Containerisation").description("Assessment and re-packaging of legacy applications into containers with minimal code changes using Tanzu tooling.").service(s25).build(),
                ServiceBenefit.builder().title("Developer Self-Service").description("Tanzu Application Platform golden paths that give developers a paved route from code to production without infrastructure toil.").service(s25).build(),
                ServiceBenefit.builder().title("Supply Chain Security").description("Software supply chain policies, image scanning, and attestation workflows enforcing security from source to deployment.").service(s25).build(),
                ServiceBenefit.builder().title("Tanzu Managed Operations").description("Ongoing platform operations, patching, and health management ensuring enterprise-grade Tanzu reliability.").service(s25).build()
        ));

        // ── 26. Enterprise Agency Platform – Visionary Inspire TRIA
        ServiceEntity s26 = ServiceEntity.builder()
                .slug("enterprise-agency-platform-tria").title("Enterprise Agency Platform – Visionary Inspire TRIA")
                .description("Visionary Inspire TRIA is our enterprise AI agency platform that orchestrates intelligent agents to automate complex, multi-step business processes end-to-end. Built on a foundation of large language models, tool-use frameworks, and enterprise integration, TRIA deploys autonomous agents that plan, reason, and execute tasks across your existing systems without human intervention.")
                .iconUrl("/images/services/tria.svg").bannerUrl("/images/services/tria-banner.jpg")
                .category("Cloud & Digital").displayOrder(26).build();
        s26.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Autonomous Agent Orchestration").description("Multi-agent pipelines that decompose complex tasks, assign specialist agents, and synthesise results without manual coordination.").service(s26).build(),
                ServiceBenefit.builder().title("Enterprise System Integration").description("Pre-built connectors enabling TRIA agents to read from and write to ERP, CRM, ITSM, and data platforms securely.").service(s26).build(),
                ServiceBenefit.builder().title("Human-in-the-Loop Controls").description("Configurable approval gates and escalation rules ensuring human oversight at critical decision points.").service(s26).build(),
                ServiceBenefit.builder().title("Audit & Explainability").description("Full agent reasoning traces and action logs providing auditability required for regulated enterprise environments.").service(s26).build(),
                ServiceBenefit.builder().title("Rapid Use Case Deployment").description("Pre-built agent templates for common enterprise workflows enabling fast time-to-value without building from scratch.").service(s26).build()
        ));

        // ── 27. Product Line – Visionary Inspire Modernize
        ServiceEntity s27 = ServiceEntity.builder()
                .slug("product-line-visionary-inspire-modernize").title("Product Line – Visionary Inspire Modernize")
                .description("Visionary Inspire Modernize is our proprietary accelerator product line that combines AI-powered code analysis, automated refactoring tooling, and migration factory methodology to industrialise legacy application modernisation. Modernize cuts the cost and risk of large-scale modernisation programmes by automating the most labour-intensive discovery and transformation activities.")
                .iconUrl("/images/services/product-line-visionary-inspire-modernize.svg").bannerUrl("/images/services/product-line-visionary-inspire-modernize-banner.jpg")
                .category("Cloud & Digital").displayOrder(27).build();
        s27.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("AI-Powered Code Analysis").description("Automated scanning of legacy codebases producing dependency maps, complexity scores, and transformation recommendations.").service(s27).build(),
                ServiceBenefit.builder().title("Automated Refactoring").description("Pattern-based code transformation tools that mechanise repetitive refactoring tasks, reducing manual effort by up to 60%.").service(s27).build(),
                ServiceBenefit.builder().title("Test Generation").description("Automated generation of unit and regression test suites providing safety net coverage before and after transformation.").service(s27).build(),
                ServiceBenefit.builder().title("Migration Risk Reduction").description("Continuous validation checkpoints and automated regression testing catching compatibility issues before they reach production.").service(s27).build(),
                ServiceBenefit.builder().title("Programme Velocity").description("Factory-style modernisation throughput that makes large portfolio programmes commercially viable within realistic timelines.").service(s27).build()
        ));

        // ── 28. Product Line – Visionary Inspire Optimize
        ServiceEntity s28 = ServiceEntity.builder()
                .slug("product-line-visionary-inspire-optimize").title("Product Line – Visionary Inspire Optimize")
                .description("Visionary Inspire Optimize is our intelligent operations product line that applies AI, analytics, and automation to continuously optimise enterprise IT and business operations. Optimize embeds continuous intelligence into operational workflows — proactively detecting performance degradation, predicting failures, and automatically implementing improvements before they impact business outcomes.")
                .iconUrl("/images/services/product-line-visionary-inspire-optimize.svg").bannerUrl("/images/services/product-line-visionary-inspire-optimize-banner.jpg")
                .category("Cloud & Digital").displayOrder(28).build();
        s28.getBenefits().addAll(List.of(
                ServiceBenefit.builder().title("Predictive Operations").description("ML models trained on operational telemetry that predict failures and capacity constraints before they impact service.").service(s28).build(),
                ServiceBenefit.builder().title("Automated Remediation").description("Self-healing runbooks triggered by anomaly detection that resolve common issues without human intervention.").service(s28).build(),
                ServiceBenefit.builder().title("Cost Optimisation Intelligence").description("Continuous analysis of infrastructure utilisation and spend patterns with automated right-sizing recommendations.").service(s28).build(),
                ServiceBenefit.builder().title("Performance Engineering").description("Automated performance profiling, bottleneck identification, and tuning recommendations across application and infrastructure layers.").service(s28).build(),
                ServiceBenefit.builder().title("Continuous Compliance").description("Policy drift detection and automated remediation keeping cloud and application configurations continuously compliant.").service(s28).build()
        ));

        serviceRepository.saveAll(List.of(
                s01, s03, s04, s05, s06, s07,
                s08, s09, s10,
                s11, s12, s13, s14, s15,
                s16, s17, s18, s19, s20, s21, s22,
                s23, s24, s25, s26, s27, s28
        ));
    }


    // ─── Industries ───────────────────────────────────────────────────────────

    private void seedIndustries() {
        if (industryRepository.count() > 0) return;

        Industry bfs = Industry.builder()
                .slug("banking-financial-services")
                .title("Banking & Financial Services")
                .description("We help banks, insurers, and financial institutions deliver superior customer experiences while meeting stringent regulatory requirements and managing risk.")
                .iconUrl("/images/industries/bfs.svg")
                .bannerUrl("/images/industries/bfs-banner.jpg")
                .displayOrder(1)
                .build();
        bfs.getSolutions().addAll(List.of(
                IndustrySolution.builder().title("Customer Onboarding").description("Digital-first KYC and account opening journeys that reduce drop-off and meet AML obligations.").industry(bfs).build(),
                IndustrySolution.builder().title("Collections & Recoveries").description("Empathetic, compliant collections processes that maximise recovery while protecting brand.").industry(bfs).build(),
                IndustrySolution.builder().title("Fraud & Risk Operations").description("24/7 analyst teams working alongside AI models to detect and prevent financial crime.").industry(bfs).build(),
                IndustrySolution.builder().title("Regulatory Reporting").description("Accurate, timely regulatory submissions powered by data automation and expert oversight.").industry(bfs).build()
        ));

        Industry health = Industry.builder()
                .slug("healthcare")
                .title("Healthcare")
                .description("We support healthcare providers and payers with operations that improve patient outcomes, streamline claims, and ensure compliance across complex regulatory landscapes.")
                .iconUrl("/images/industries/healthcare.svg")
                .bannerUrl("/images/industries/healthcare-banner.jpg")
                .displayOrder(2)
                .build();
        health.getSolutions().addAll(List.of(
                IndustrySolution.builder().title("Revenue Cycle Management").description("End-to-end RCM services that accelerate reimbursement and reduce denials.").industry(health).build(),
                IndustrySolution.builder().title("Prior Authorisation").description("Faster pre-auth processing reducing delays in patient care.").industry(health).build(),
                IndustrySolution.builder().title("Patient Engagement").description("Proactive outreach and care coordination that improves adherence and satisfaction.").industry(health).build(),
                IndustrySolution.builder().title("Claims Processing").description("High-accuracy claims adjudication with built-in compliance controls.").industry(health).build(),
                IndustrySolution.builder().title("Coding & Documentation").description("Expert medical coding teams ensuring accuracy and audit readiness.").industry(health).build()
        ));

        Industry telecom = Industry.builder()
                .slug("telecommunications")
                .title("Telecommunications")
                .description("We help telecoms operators acquire, retain, and support customers across all channels while driving down cost-to-serve and accelerating digital adoption.")
                .iconUrl("/images/industries/telecom.svg")
                .bannerUrl("/images/industries/telecom-banner.jpg")
                .displayOrder(3)
                .build();
        telecom.getSolutions().addAll(List.of(
                IndustrySolution.builder().title("Subscriber Lifecycle Management").description("Acquisition-to-retention programmes that maximise subscriber lifetime value.").industry(telecom).build(),
                IndustrySolution.builder().title("Technical Support").description("Tier 1 and Tier 2 support reducing truck rolls and improving first-call resolution.").industry(telecom).build(),
                IndustrySolution.builder().title("Billing & Collections").description("Accurate bill management and compliant collections that protect revenue.").industry(telecom).build(),
                IndustrySolution.builder().title("Network Operations Support").description("Back-office operations supporting network provisioning and fault management.").industry(telecom).build()
        ));

        Industry retail = Industry.builder()
                .slug("retail-ecommerce")
                .title("Retail & E-commerce")
                .description("We help retailers and e-commerce players deliver exceptional buying experiences, manage seller ecosystems, and scale operations during peak demand periods.")
                .iconUrl("/images/industries/retail.svg")
                .bannerUrl("/images/industries/retail-banner.jpg")
                .displayOrder(4)
                .build();
        retail.getSolutions().addAll(List.of(
                IndustrySolution.builder().title("Order Management").description("Accurate, fast order processing and exception handling across fulfilment channels.").industry(retail).build(),
                IndustrySolution.builder().title("Seller Onboarding & Support").description("Marketplace seller activation and ongoing support at scale.").industry(retail).build(),
                IndustrySolution.builder().title("Returns & Refunds Management").description("Streamlined reverse logistics and refund processing that protects customer loyalty.").industry(retail).build(),
                IndustrySolution.builder().title("Customer Care").description("Omnichannel support covering pre-purchase, purchase, and post-purchase touchpoints.").industry(retail).build(),
                IndustrySolution.builder().title("Peak Season Scaling").description("Elastic capacity models designed to handle Black Friday and seasonal demand surges.").industry(retail).build()
        ));

        Industry media = Industry.builder()
                .slug("media-entertainment")
                .title("Media & Entertainment")
                .description("We support streaming platforms, publishers, and content companies with moderation, subscriber management, and data-driven audience engagement solutions.")
                .iconUrl("/images/industries/media.svg")
                .bannerUrl("/images/industries/media-banner.jpg")
                .displayOrder(5)
                .build();
        media.getSolutions().addAll(List.of(
                IndustrySolution.builder().title("Content Moderation").description("Policy-compliant review of user-generated content across text, image, and video formats.").industry(media).build(),
                IndustrySolution.builder().title("Subscriber Management").description("Acquisition, billing, and churn prevention for subscription-based media models.").industry(media).build(),
                IndustrySolution.builder().title("Audience Analytics").description("Behavioural insights that inform content strategy and personalisation.").industry(media).build(),
                IndustrySolution.builder().title("Rights & Licensing Support").description("Back-office operations managing content rights, royalties, and licensing compliance.").industry(media).build()
        ));

        industryRepository.saveAll(List.of(bfs, health, telecom, retail, media));
    }

    // ─── Insights ─────────────────────────────────────────────────────────────

    private void seedInsights() {
        if (insightRepository.count() > 0) return;
        insightRepository.saveAll(List.of(
                Insight.builder()
                        .slug("future-of-cx-in-banking")
                        .title("The Future of Customer Experience in Banking")
                        .summary("How leading banks are using AI, automation, and empathy-led design to transform every customer interaction.")
                        .content("Full article content goes here.")
                        .category("Customer Experience")
                        .author("Arjun Mehta")
                        .publishedDate(LocalDateTime.of(2025, 3, 10, 9, 0))
                        .imageUrl("/images/insights/future-of-cx-in-banking.jpg")
                        .isFeatured(true)
                        .build(),
                Insight.builder()
                        .slug("rpa-vs-intelligent-automation")
                        .title("RPA vs Intelligent Automation: What Enterprises Need to Know")
                        .summary("A practical guide to understanding where robotic process automation ends and intelligent automation begins.")
                        .content("Full article content goes here.")
                        .category("Intelligent Automation")
                        .author("Divya Nair")
                        .publishedDate(LocalDateTime.of(2025, 2, 18, 9, 0))
                        .imageUrl("/images/insights/rpa-vs-intelligent-automation.jpg")
                        .isFeatured(true)
                        .build(),
                Insight.builder()
                        .slug("data-driven-healthcare-operations")
                        .title("Data-Driven Operations in Healthcare: From Claims to Care")
                        .summary("Exploring how analytics and intelligent workflows are reshaping revenue cycle management and patient engagement.")
                        .content("Full article content goes here.")
                        .category("Data & Analytics")
                        .author("Rohan Pillai")
                        .publishedDate(LocalDateTime.of(2025, 1, 22, 9, 0))
                        .imageUrl("/images/insights/data-driven-healthcare-operations.jpg")
                        .isFeatured(false)
                        .build(),
                Insight.builder()
                        .slug("trust-safety-scale")
                        .title("Scaling Trust & Safety Without Scaling Costs")
                        .summary("How leading digital platforms are combining AI-assisted moderation with human expertise to protect users at scale.")
                        .content("Full article content goes here.")
                        .category("Trust & Safety")
                        .author("Sneha Kapoor")
                        .publishedDate(LocalDateTime.of(2024, 12, 5, 9, 0))
                        .imageUrl("/images/insights/trust-safety-scale.jpg")
                        .isFeatured(false)
                        .build(),
                Insight.builder()
                        .slug("digital-transformation-telecom")
                        .title("Digital Transformation Imperatives for Telecoms in 2025")
                        .summary("The five technology shifts telecom operators must execute to remain competitive as 5G monetisation accelerates.")
                        .content("Full article content goes here.")
                        .category("Digital Transformation")
                        .author("Kiran Desai")
                        .publishedDate(LocalDateTime.of(2024, 11, 14, 9, 0))
                        .imageUrl("/images/insights/digital-transformation-telecom.jpg")
                        .isFeatured(false)
                        .build(),
                Insight.builder()
                        .slug("generative-ai-contact-centre")
                        .title("Generative AI in the Contact Centre: Opportunity and Risk")
                        .summary("An honest assessment of where large language models add value in customer service operations — and where human oversight remains essential.")
                        .content("Full article content goes here.")
                        .category("Intelligent Automation")
                        .author("Arjun Mehta")
                        .publishedDate(LocalDateTime.of(2024, 10, 30, 9, 0))
                        .imageUrl("/images/insights/generative-ai-contact-centre.jpg")
                        .isFeatured(true)
                        .build(),
                Insight.builder()
                        .slug("esg-in-outsourcing")
                        .title("Why ESG Credentials Are Now a Deal-Breaker in BPO Selection")
                        .summary("Procurement teams are scrutinising supplier ESG performance more closely than ever. Here is what buyers are demanding in 2025.")
                        .content("Full article content goes here.")
                        .category("Industry Trends")
                        .author("Divya Nair")
                        .publishedDate(LocalDateTime.of(2024, 9, 17, 9, 0))
                        .imageUrl("/images/insights/esg-in-outsourcing.jpg")
                        .isFeatured(false)
                        .build(),
                Insight.builder()
                        .slug("retail-peak-season-playbook")
                        .title("The Peak Season Playbook for Retail Operations")
                        .summary("A practical framework for scaling customer care, fulfilment, and returns management without compromising quality during holiday peaks.")
                        .content("Full article content goes here.")
                        .category("Customer Experience")
                        .author("Sneha Kapoor")
                        .publishedDate(LocalDateTime.of(2024, 8, 25, 9, 0))
                        .imageUrl("/images/insights/retail-peak-season-playbook.jpg")
                        .isFeatured(false)
                        .build(),
                Insight.builder()
                        .slug("data-mesh-enterprise")
                        .title("Data Mesh in the Enterprise: Beyond the Hype")
                        .summary("A critical look at how data mesh architecture is being implemented in large enterprises and the lessons learned from early adopters.")
                        .content("Full article content goes here.")
                        .category("Data & Analytics")
                        .author("Rohan Pillai")
                        .publishedDate(LocalDateTime.of(2024, 7, 11, 9, 0))
                        .imageUrl("/images/insights/data-mesh-enterprise.jpg")
                        .isFeatured(false)
                        .build(),
                Insight.builder()
                        .slug("workforce-wellbeing-bpo")
                        .title("Workforce Wellbeing as a Competitive Advantage in BPO")
                        .summary("Why the highest-performing outsourcing providers are investing heavily in agent wellbeing and what measurable impact it has on client outcomes.")
                        .content("Full article content goes here.")
                        .category("Industry Trends")
                        .author("Kiran Desai")
                        .publishedDate(LocalDateTime.of(2024, 6, 3, 9, 0))
                        .imageUrl("/images/insights/workforce-wellbeing-bpo.jpg")
                        .isFeatured(false)
                        .build()
        ));
    }

    // ─── Jobs ─────────────────────────────────────────────────────────────────

    private void seedJobs() {
        // Reassign jobs with old mortgage department names to "US Mortgage"
        List.of("Closing", "Funding", "Loan Setup").forEach(dept -> {
            List<Job> toUpdate = jobRepository.findByDepartmentAndActiveTrue(dept);
            toUpdate.forEach(j -> j.setDepartment("US Mortgage"));
            jobRepository.saveAll(toUpdate);
        });

        if (jobRepository.count() > 0 && jobRepository.findAll().stream().anyMatch(j -> "US Mortgage".equals(j.getDepartment()))) return;
        if (jobRepository.count() == 0) {
        jobRepository.saveAll(List.of(
                Job.builder().title("Senior Customer Service Associate").department("Customer Experience").location("Chennai, India").type("Full-Time")
                        .description("Handle inbound and outbound interactions for a global banking client across voice and chat channels. Drive first-call resolution and CSAT targets.").postedDate(LocalDateTime.of(2025, 5, 1, 9, 0)).active(true).build(),
                Job.builder().title("RPA Developer").department("Intelligent Automation").location("Bangalore, India").type("Full-Time")
                        .description("Design, develop, and deploy UiPath automation workflows for enterprise clients. Collaborate with business analysts to identify process improvement opportunities.").postedDate(LocalDateTime.of(2025, 4, 20, 9, 0)).active(true).build(),
                Job.builder().title("Data Engineer").department("Data & Analytics").location("Hyderabad, India").type("Full-Time")
                        .description("Build and maintain scalable data pipelines on cloud platforms. Work with analytics teams to deliver reliable data products for business intelligence.").postedDate(LocalDateTime.of(2025, 4, 15, 9, 0)).active(true).build(),
                Job.builder().title("Content Moderator").department("Trust & Safety").location("Manila, Philippines").type("Full-Time")
                        .description("Review user-generated content against platform policies across text, image, and short-form video formats. Escalate complex cases to senior reviewers.").postedDate(LocalDateTime.of(2025, 4, 10, 9, 0)).active(true).build(),
                Job.builder().title("Digital Transformation Consultant").department("Digital Transformation").location("London, UK").type("Full-Time")
                        .description("Lead digital strategy engagements for FTSE 100 clients. Develop business cases, operating model designs, and technology roadmaps.").postedDate(LocalDateTime.of(2025, 3, 28, 9, 0)).active(true).build(),
                Job.builder().title("Healthcare Claims Analyst").department("Healthcare Operations").location("New York, USA").type("Full-Time")
                        .description("Process and adjudicate medical claims for US payer clients. Ensure accuracy, compliance with CMS guidelines, and meeting SLA turnaround targets.").postedDate(LocalDateTime.of(2025, 3, 20, 9, 0)).active(true).build(),
                Job.builder().title("Senior Data Scientist").department("Data & Analytics").location("Bangalore, India").type("Full-Time")
                        .description("Build predictive and prescriptive models for clients across financial services and healthcare. Deploy models to production using MLOps best practices.").postedDate(LocalDateTime.of(2025, 3, 10, 9, 0)).active(true).build(),
                Job.builder().title("Fraud Operations Analyst").department("Customer Experience").location("Chennai, India").type("Full-Time")
                        .description("Investigate flagged transactions for a financial services client. Apply risk scoring models and manual review to identify and prevent fraudulent activity.").postedDate(LocalDateTime.of(2025, 2, 25, 9, 0)).active(true).build(),
                Job.builder().title("Automation Business Analyst").department("Intelligent Automation").location("London, UK").type("Full-Time")
                        .description("Work with client stakeholders to map as-is processes, define automation requirements, and manage delivery of RPA and AI solutions.").postedDate(LocalDateTime.of(2025, 2, 14, 9, 0)).active(true).build(),
                Job.builder().title("Trust & Safety Team Lead").department("Trust & Safety").location("Manila, Philippines").type("Full-Time")
                        .description("Lead a team of content reviewers supporting a global social media client. Own quality metrics, coach team members, and manage escalation workflows.").postedDate(LocalDateTime.of(2025, 2, 5, 9, 0)).active(true).build(),
                Job.builder().title("BI Developer").department("Data & Analytics").location("New York, USA").type("Full-Time")
                        .description("Design and build Power BI and Tableau dashboards for enterprise clients. Translate business requirements into clear, actionable visual analytics.").postedDate(LocalDateTime.of(2025, 1, 30, 9, 0)).active(true).build(),
                Job.builder().title("HR Business Partner").department("Human Resources").location("Hyderabad, India").type("Full-Time")
                        .description("Partner with delivery leaders to drive talent management, engagement, and performance programmes across a 2,000-strong operations centre.").postedDate(LocalDateTime.of(2025, 1, 20, 9, 0)).active(true).build()
        ));
        }
        // Ensure all four US Mortgage jobs exist (idempotent by title)
        List<String> existingTitles = jobRepository.findAll().stream().map(j -> j.getTitle()).toList();
        List<Job> mortgageJobs = new java.util.ArrayList<>();
        if (!existingTitles.contains("Closing Sr. Analyst"))
            mortgageJobs.add(Job.builder().title("Closing Sr. Analyst").department("US Mortgage").location("Bangalore, India").type("Full-Time").description("Review and clear closing conditions on residential mortgage loan files, coordinate with title companies and escrow agents, and ensure all closing documents meet investor and regulatory requirements.").postedDate(LocalDateTime.of(2025, 6, 1, 9, 0)).active(true).build());
        if (!existingTitles.contains("Post-closing Analyst"))
            mortgageJobs.add(Job.builder().title("Post-closing Analyst").department("US Mortgage").location("Bangalore, India").type("Full-Time").description("Manage post-closing activities including document review, trailing document follow-up, recording confirmation, and investor package delivery to ensure loan files are complete and investor-ready.").postedDate(LocalDateTime.of(2025, 6, 1, 9, 0)).active(true).build());
        if (!existingTitles.contains("Loansetup - Analyst"))
            mortgageJobs.add(Job.builder().title("Loansetup - Analyst").department("US Mortgage").location("Bangalore, India").type("Full-Time").description("Set up new mortgage loan files in the loan origination system, verify data integrity, order required third-party services, and ensure all initial disclosures are issued within regulatory timeframes.").postedDate(LocalDateTime.of(2025, 6, 1, 9, 0)).active(true).build());
        if (!existingTitles.contains("Funding - Sr. Analyst"))
            mortgageJobs.add(Job.builder().title("Funding - Sr. Analyst").department("US Mortgage").location("Bangalore, India").type("Full-Time").description("Coordinate mortgage loan funding activities, review final closing packages, verify wire instructions, confirm title company receipt, and ensure all conditions are cleared prior to fund disbursement.").postedDate(LocalDateTime.of(2025, 6, 1, 9, 0)).active(true).build());
        if (!mortgageJobs.isEmpty()) jobRepository.saveAll(mortgageJobs);
    }

    // ─── Leaders ──────────────────────────────────────────────────────────────

    private void seedLeaders() {
        if (leaderRepository.count() > 0) return;
        leaderRepository.saveAll(List.of(
                Leader.builder()
                        .name("Rajesh Kumar").title("Chief Executive Officer")
                        .bio("Rajesh Kumar brings over 4 years of technology and services leadership to his role as CEO. Previously a senior leader at a leading IT services firm, he has led digital transformation programmes for clients across financial services, healthcare, and telecommunications. Under his leadership, Visionary Inspire has expanded its hyperscaler partnerships, grown its AI and automation practice, and strengthened delivery operations across India. Rajesh holds an MBA from IIM Ahmedabad and a Bachelor of Engineering in Computer Science from IIT Bombay.")
                        .photoUrl("/images/leaders/rajesh-kumar.jpg")
                        .linkedinUrl("https://www.linkedin.com/in/rajesh-kumar")
                        .displayOrder(1).build(),
                Leader.builder()
                        .name("Sarah Mitchell").title("Chief Technology Officer")
                        .bio("Sarah Mitchell is a seasoned technology executive with experience architecting enterprise platforms and leading engineering organisations. Before joining Visionary Inspire, she served as VP of Engineering at a cloud infrastructure company, where she led the transition to a cloud-native product portfolio. Sarah is a recognised voice in the AI and distributed systems community, holds patents in intelligent document processing, and serves on the advisory board of two university computer science departments. She holds an MEng in Software Engineering from the University of Cambridge.")
                        .photoUrl("/images/leaders/sarah-mitchell.jpg")
                        .linkedinUrl("https://www.linkedin.com/in/sarah-mitchell-cto")
                        .displayOrder(2).build(),
                Leader.builder()
                        .name("Michael Chen").title("Chief Operating Officer")
                        .bio("Michael Chen oversees delivery operations, service quality, and operational excellence across Visionary Inspire's delivery centres in India. With a background spanning management consulting and BPO operations, Michael has led transformation programmes that have delivered measurable improvements in cost efficiency, client satisfaction, and workforce capability. He is a certified Lean Six Sigma Master Black Belt and holds an MBA from INSEAD and a BSc in Industrial Engineering from the University of Michigan.")
                        .photoUrl("/images/leaders/michael-chen.jpg")
                        .linkedinUrl("https://www.linkedin.com/in/michael-chen-coo")
                        .displayOrder(3).build(),
                Leader.builder()
                        .name("Priya Sharma").title("VP, Cloud & Infrastructure")
                        .bio("Priya Sharma leads Visionary Inspire's Cloud and Infrastructure practice, spanning AWS, Azure, GCP, and hybrid infrastructure services. She joined the company following several years at a leading hyperscaler, where she built the enterprise cloud advisory function across South Asia. Priya has personally led cloud migration and modernisation programmes for multiple enterprise clients and is a frequent speaker at industry events. She is an AWS Certified Solutions Architect – Professional and holds a Master's in Computer Networks from the Indian Institute of Science, Bangalore.")
                        .photoUrl("/images/leaders/priya-sharma.jpg")
                        .linkedinUrl("https://www.linkedin.com/in/priya-sharma-cloud")
                        .displayOrder(4).build(),
                Leader.builder()
                        .name("David Wilson").title("VP, AI & Automation")
                        .bio("David Wilson heads the AI and Automation practice at Visionary Inspire, responsible for the company's machine learning, RPA, intelligent document processing, and agentic AI capabilities. A former research scientist turned enterprise practitioner, David has spent his career bridging academic advances in AI with commercial deployment. He founded the company's Responsible AI Centre of Excellence and has co-authored industry frameworks on AI governance adopted by clients in regulated financial services and healthcare verticals. David holds a PhD in Machine Learning from Carnegie Mellon University and is a published contributor to NeurIPS and ICML.")
                        .photoUrl("/images/leaders/david-wilson.jpg")
                        .linkedinUrl("https://www.linkedin.com/in/david-wilson-ai")
                        .displayOrder(5).build(),
                Leader.builder()
                        .name("Anita Rao").title("VP, Digital Transformation")
                        .bio("Anita Rao leads Digital Transformation at Visionary Inspire, helping enterprise clients reimagine their customer journeys, modernise legacy platforms, and build the organisational capabilities needed to sustain digital change. With a background in digital strategy and product management, she has guided transformation programmes for leading banks, insurers, and retail groups. Anita is a recognised advocate for human-centred design and inclusive digital product development, and was named among the Top 50 Women in Tech by a leading industry publication in 2023. She holds an MBA from London Business School and a BA in Economics from St Stephen's College, Delhi.")
                        .photoUrl("/images/leaders/anita-rao.jpg")
                        .linkedinUrl("https://www.linkedin.com/in/anita-rao-digital")
                        .displayOrder(6).build()
        ));
    }

    // ─── Mortgage Services ─────────────────────────────────────────────────────

    private void seedMortgageServices() {
        if (mortgageServiceRepository.count() > 0) return;
        mortgageServiceRepository.saveAll(List.of(
                MortgageService.builder().slug("mortgage-language-model").title("Mortgage Language Model").category("AI & Mortgage Intelligence").description("Purpose-built large language models trained on mortgage domain data — loan documents, regulatory guidelines, servicing notes, and underwriting criteria — delivering AI-powered automation and intelligence across the entire mortgage lifecycle.").overview("Our Mortgage Language Model is a domain-trained AI foundation purpose-built for the mortgage industry. Unlike general-purpose LLMs, it understands mortgage-specific terminology, document structures, regulatory requirements, and workflow patterns. It powers intelligent document processing, automated underwriting support, compliance checking, and conversational servicing — all with the accuracy and reliability that regulated lending demands.").imageUrl("/images/mortgage-services/mortgage-language-model.jpg").bannerUrl("/images/mortgage-services/mortgage-language-model-banner.jpg").displayOrder(1).build(),
                MortgageService.builder().slug("mortgage-loan-origination").title("Mortgage Loan Origination").category("Origination Services").description("End-to-end mortgage loan origination services spanning application intake, processing, underwriting support, and closing coordination — enabling lenders to close more loans faster while maintaining rigorous quality and compliance standards.").overview("We provide comprehensive origination operations support that extends your team capacity, improves cycle times, and ensures every loan file meets investor and regulatory requirements. Our mortgage operations specialists handle the full origination workflow from application through clear-to-close, combining deep process expertise with technology-enabled quality controls.").imageUrl("/images/mortgage-services/mortgage-loan-origination.jpg").bannerUrl("/images/mortgage-services/mortgage-loan-origination-banner.jpg").displayOrder(2).build(),
                MortgageService.builder().slug("retail-and-consumer-direct").title("Retail and Consumer Direct").category("Origination Services").description("Specialised operations support for retail and consumer direct mortgage lending channels — helping lenders maximise loan officer productivity, improve borrower experience, and scale origination capacity without proportional headcount growth.").overview("Our retail and consumer direct support services are designed for lenders operating branch networks and direct-to-consumer digital channels. We handle the high-volume, repetitive operational tasks that consume loan officer time, freeing your team to focus on borrower relationships and revenue generation.").imageUrl("/images/mortgage-services/retail-and-consumer-direct.jpg").bannerUrl("/images/mortgage-services/retail-and-consumer-direct-banner.jpg").displayOrder(3).build(),
                MortgageService.builder().slug("wholesale").title("Wholesale").category("Origination Services").description("Operations and compliance support services for wholesale mortgage lending — enabling wholesale lenders to scale broker relationships, accelerate broker-submitted loan processing, and maintain consistent quality across high-volume pipelines.").overview("Wholesale mortgage lending demands speed, accuracy, and seamless broker experience. Our wholesale operations team handles the complex, high-volume processing demands of broker-submitted loans, ensuring rapid turn times, accurate condition management, and strong compliance posture that protects your approvals and reputation.").imageUrl("/images/mortgage-services/wholesale.jpg").bannerUrl("/images/mortgage-services/wholesale-banner.jpg").displayOrder(4).build(),
                MortgageService.builder().slug("correspondent").title("Correspondent").category("Origination Services").description("Due diligence, quality control, and operations support for correspondent mortgage lending — enabling correspondents and aggregators to manage loan acquisition risk, accelerate purchase decisions, and maintain investor-grade file quality at scale.").overview("Correspondent lending requires rigorous due diligence and rapid purchase review to manage acquisition risk and maintain investor relationships. Our correspondent operations specialists perform comprehensive pre-purchase and post-purchase reviews, delivering the file quality assurance that protects your balance sheet and investor commitments.").imageUrl("/images/mortgage-services/correspondent.jpg").bannerUrl("/images/mortgage-services/correspondent-banner.jpg").displayOrder(5).build(),
                MortgageService.builder().slug("mortgage-processing").title("Mortgage Processing").category("Origination Services").description("Full-service mortgage processing support — managing the complete loan file from application through conditions clearance, coordinating with borrowers, appraisers, title companies, and underwriters to deliver clean, complete files ready for closing.").overview("Our mortgage processing services provide skilled processor support that keeps your pipeline moving. We handle every aspect of the processing workflow — from initial file set-up through final conditions clearance — with the speed, accuracy, and communication quality that borrowers and referral partners expect.").imageUrl("/images/mortgage-services/mortgage-processing.jpg").bannerUrl("/images/mortgage-services/mortgage-processing-banner.jpg").displayOrder(6).build(),
                MortgageService.builder().slug("mortgage-servicing").title("Mortgage Servicing").category("Servicing Operations").description("Comprehensive mortgage servicing operations — covering payment processing, escrow administration, investor reporting, default management, and borrower communications — delivered by experienced servicers with deep regulatory and GSE compliance expertise.").overview("Mortgage servicing is a complex, heavily regulated function that demands operational precision and consistent compliance. Our servicing operations team handles the full servicing lifecycle with the systems expertise, process discipline, and regulatory knowledge required to protect your MSR portfolio and borrower relationships.").imageUrl("/images/mortgage-services/mortgage-servicing.jpg").bannerUrl("/images/mortgage-services/mortgage-servicing-banner.jpg").displayOrder(7).build(),
                MortgageService.builder().slug("performing-servicing").title("Performing Servicing").category("Servicing Operations").description("Specialist performing loan servicing operations focused on maintaining borrower current status, maximising payment collection efficiency, and proactively identifying early payment risk before loans transition to delinquency.").overview("Performing portfolio servicing requires consistent, proactive borrower management to maintain current status and identify risk signals early. Our performing servicing team combines disciplined payment management with data-driven early intervention to protect your portfolio quality and investor performance metrics.").imageUrl("/images/mortgage-services/performing-servicing.jpg").bannerUrl("/images/mortgage-services/performing-servicing-banner.jpg").displayOrder(8).build(),
                MortgageService.builder().slug("mortgage-title-services").title("Mortgage Title Services").category("Title & Closing Services").description("Comprehensive mortgage title services spanning title search, examination, commitment production, curative work, and policy issuance — delivered with the speed, accuracy, and compliance required by lenders and investors in today's competitive market.").overview("Our mortgage title services operation provides lenders and settlement agents with full-service title support that accelerates closings while protecting against title risk. From initial search through final policy issuance, our experienced title professionals deliver the quality and speed that modern mortgage origination demands.").imageUrl("/images/mortgage-services/mortgage-title-services.jpg").bannerUrl("/images/mortgage-services/mortgage-title-services-banner.jpg").displayOrder(9).build(),
                MortgageService.builder().slug("home-equity").title("Home Equity").category("Origination Services").description("End-to-end operations support for home equity loan and HELOC origination — enabling lenders to scale equity lending programmes, accelerate approval cycles, and deliver the fast, convenient experience that today's home equity borrowers expect.").overview("Home equity lending is one of the fastest-growing segments of the mortgage market, driven by record homeowner equity levels and rising demand for renovation and consolidation financing. Our home equity operations team handles the full origination workflow with the speed and efficiency that converts home equity inquiries into closed loans.").imageUrl("/images/mortgage-services/home-equity.jpg").bannerUrl("/images/mortgage-services/home-equity-banner.jpg").displayOrder(10).build(),
                MortgageService.builder().slug("originators-and-servicers").title("Originators and Servicers").category("Title & Closing Services").description("Integrated title and settlement services designed specifically for mortgage originators and servicers — providing seamless title support across origination, servicing transfers, and default-related title requirements.").overview("We serve as a comprehensive title partner for mortgage originators and servicers, providing the full spectrum of title services required across the loan lifecycle. From origination title commitments through servicing-related title updates and default title services, we deliver consistent quality and compliance at every stage.").imageUrl("/images/mortgage-services/originators-and-servicers.jpg").bannerUrl("/images/mortgage-services/originators-and-servicers-banner.jpg").displayOrder(11).build(),
                MortgageService.builder().slug("title-companies").title("Title Companies").category("Title & Closing Services").description("Back-office support services for title companies — providing search, examination, policy typing, endorsement processing, and administrative support that enables title companies to scale operations, reduce costs, and improve throughput.").overview("We are a trusted operations partner for independent title companies and agency networks seeking to expand capacity, reduce cost, and improve turnaround times without adding fixed overhead. Our title back-office team handles the high-volume processing work, freeing your examiners and closers to focus on complex, judgment-intensive tasks.").imageUrl("/images/mortgage-services/title-companies.jpg").bannerUrl("/images/mortgage-services/title-companies-banner.jpg").displayOrder(12).build(),
                MortgageService.builder().slug("mortgage-post-closing").title("Mortgage Post Closing").category("Post-Closing Services").description("Comprehensive mortgage post-closing services — covering document review, shipping, trailing document management, recording follow-up, and final document retrieval — ensuring loan files meet investor delivery requirements and are investor-ready on time.").overview("Post-closing is a critical but often underinvested function that directly impacts investor relationships, warehouse line costs, and balance sheet risk. Our post-closing team provides meticulous, time-sensitive document management that ensures every loan is delivered to investors with complete, accurate documentation.").imageUrl("/images/mortgage-services/mortgage-post-closing.jpg").bannerUrl("/images/mortgage-services/mortgage-post-closing-banner.jpg").displayOrder(13).build(),
                MortgageService.builder().slug("lien-release").title("Lien Release").category("Post-Closing Services").description("Full-service mortgage lien release and satisfaction services — ensuring timely, accurate release of mortgage liens upon payoff, protecting borrowers from title encumbrance and servicers from regulatory liability and state penalty exposure.").overview("Lien release is a compliance-critical servicing function with strict state statutory deadlines and significant penalty exposure for late or inaccurate processing. Our lien release team delivers systematic, timely satisfaction processing that protects your regulatory standing, eliminates penalties, and provides a positive payoff experience for former borrowers.").imageUrl("/images/mortgage-services/lien-release.jpg").bannerUrl("/images/mortgage-services/lien-release-banner.jpg").displayOrder(14).build(),
                MortgageService.builder().slug("services-for-lenders").title("Services for Lenders").category("Title & Closing Services").description("A comprehensive suite of title, settlement, and ancillary services designed to support mortgage lenders across all channels — providing the operational backbone lenders need to close loans efficiently, manage risk, and deliver superior borrower experiences.").overview("We are the operational partner of choice for mortgage lenders seeking to simplify vendor management, reduce per-loan costs, and improve operational consistency across all origination channels. Our lender services combine deep mortgage expertise with scalable delivery infrastructure to support your lending operation from application through close.").imageUrl("/images/mortgage-services/services-for-lenders.jpg").bannerUrl("/images/mortgage-services/services-for-lenders-banner.jpg").displayOrder(15).build(),
                MortgageService.builder().slug("services-for-title-companies").title("Services for Title Companies").category("Title & Closing Services").description("Operational support, technology, and outsourcing services designed specifically for title companies — enabling agents and underwriters to scale capacity, reduce operational costs, and improve quality without compromising the client relationships that drive their business.").overview("Title companies face the dual challenge of managing variable volume demand and rising operational costs while maintaining the quality and relationships that differentiate them in a competitive market. Our title company services provide the back-office infrastructure and expertise that allows title professionals to focus on what they do best.").imageUrl("/images/mortgage-services/services-for-title-companies.jpg").bannerUrl("/images/mortgage-services/services-for-title-companies-banner.jpg").displayOrder(16).build(),
                MortgageService.builder().slug("fraud-management").title("Fraud Management").category("Risk & Fraud Services").description("Comprehensive mortgage fraud detection, prevention, and investigation services — protecting lenders, servicers, and investors from origination fraud, occupancy misrepresentation, identity fraud, and complex mortgage fraud schemes.").overview("Mortgage fraud causes billions in losses annually and poses significant legal, regulatory, and reputational risk. Our fraud management operation combines advanced analytics, AI-powered detection, and experienced mortgage fraud investigators to identify and prevent fraud across all channels and loan types.").imageUrl("/images/mortgage-services/fraud-management.jpg").bannerUrl("/images/mortgage-services/fraud-management-banner.jpg").displayOrder(17).build(),
                MortgageService.builder().slug("title-fraud").title("Title Fraud").category("Risk & Fraud Services").description("Specialised title fraud detection and prevention services protecting property owners, lenders, and title companies from deed fraud, forged releases, fraudulent transfers, and other title-based fraud schemes that threaten real estate transactions.").overview("Title fraud is one of the fastest-growing forms of real estate crime, with fraudsters using forged deeds, fabricated releases, and identity theft to steal equity and disrupt real property ownership. Our title fraud specialists provide the expertise, technology, and investigative capabilities to detect, prevent, and remediate title fraud.").imageUrl("/images/mortgage-services/title-fraud.jpg").bannerUrl("/images/mortgage-services/title-fraud-banner.jpg").displayOrder(18).build(),
                MortgageService.builder().slug("financial-crimes-and-compliance-services").title("Financial Crimes and Compliance Services").category("Risk & Fraud Services").description("Comprehensive financial crimes compliance services for mortgage lenders and servicers — covering BSA/AML compliance, OFAC screening, SAR filing, suspicious activity monitoring, and regulatory examination support.").overview("Mortgage lenders and servicers face complex, evolving financial crimes compliance obligations that require dedicated expertise and robust operational infrastructure. Our financial crimes compliance team provides the programme support, operational execution, and regulatory expertise needed to maintain strong compliance posture.").imageUrl("/images/mortgage-services/financial-crimes-and-compliance-services.jpg").bannerUrl("/images/mortgage-services/financial-crimes-and-compliance-services-banner.jpg").displayOrder(19).build(),
                MortgageService.builder().slug("aml-solution").title("AML Solution").category("Risk & Fraud Services").description("Purpose-built Anti-Money Laundering solutions for the mortgage industry — combining advanced transaction monitoring, AI-powered risk scoring, and expert analysis to detect and report money laundering activity in real estate and mortgage transactions.").overview("Real estate is a well-documented vehicle for money laundering, and mortgage lenders face increasing regulatory scrutiny of their AML programme effectiveness. Our AML solution provides the technology, analytics, and operational expertise to build and maintain a robust, proportionate AML compliance programme.").imageUrl("/images/mortgage-services/aml-solution.jpg").bannerUrl("/images/mortgage-services/aml-solution-banner.jpg").displayOrder(20).build(),
                MortgageService.builder().slug("due-diligence").title("Due Diligence").category("Risk & Fraud Services").description("Comprehensive mortgage loan due diligence services for investors, acquirers, and capital markets participants — providing credit, compliance, valuation, and data integrity review that supports informed investment decisions and risk management.").overview("Mortgage loan due diligence is fundamental to capital markets transactions, portfolio acquisitions, and securitisation. Our due diligence operation provides rigorous, scalable review services that deliver the analysis investors and rating agencies require to price and manage mortgage credit risk accurately.").imageUrl("/images/mortgage-services/due-diligence.jpg").bannerUrl("/images/mortgage-services/due-diligence-banner.jpg").displayOrder(21).build(),
                MortgageService.builder().slug("digital-loan-experience-dlx").title("Digital Loan Experience (DLX)").category("Digital Mortgage").description("A comprehensive digital mortgage platform delivering seamless borrower experiences from application through closing — combining intuitive self-service, intelligent automation, and human expertise to redefine what a modern mortgage journey looks like.").overview("DLX is our digital mortgage experience platform designed to meet the expectations of today's borrowers who demand the convenience of digital combined with the guidance of human expertise. It delivers a fully digital application, automated processing, real-time status transparency, and intelligent handoffs to mortgage professionals when complexity demands it.").imageUrl("/images/mortgage-services/digital-loan-experience-dlx.jpg").bannerUrl("/images/mortgage-services/digital-loan-experience-dlx-banner.jpg").displayOrder(22).build(),
                MortgageService.builder().slug("mortgage-automation-cloud").title("Mortgage Automation Cloud").category("Digital Mortgage").description("A cloud-native mortgage automation platform that orchestrates intelligent workflows across origination, processing, underwriting, and servicing — eliminating manual handoffs, reducing cycle times, and scaling with your business without proportional cost growth.").overview("Mortgage Automation Cloud brings enterprise-grade process automation to every stage of the mortgage lifecycle. Built on a modern cloud architecture with pre-built mortgage workflow templates, it connects your people, systems, and data through intelligent automation that adapts to your processes rather than forcing process change.").imageUrl("/images/mortgage-services/mortgage-automation-cloud.jpg").bannerUrl("/images/mortgage-services/mortgage-automation-cloud-banner.jpg").displayOrder(23).build(),
                MortgageService.builder().slug("first-customer-intelligence").title("First Customer Intelligence").category("Mortgage Analytics").description("Advanced customer intelligence platform for mortgage lenders and servicers — combining behavioural analytics, predictive modelling, and AI-driven insights to understand borrower lifecycle value, predict behaviour, and optimise customer engagement strategies.").overview("First Customer Intelligence transforms borrower data into actionable insight that drives smarter lending, better retention, and more effective marketing. By unifying data across origination, servicing, and third-party sources, it delivers a 360-degree borrower view that powers personalised, data-driven customer engagement.").imageUrl("/images/mortgage-services/first-customer-intelligence.jpg").bannerUrl("/images/mortgage-services/first-customer-intelligence-banner.jpg").displayOrder(24).build(),
                MortgageService.builder().slug("first-learning-intelligence").title("First Learning Intelligence").category("Mortgage Analytics").description("An AI-powered learning and knowledge intelligence platform for mortgage organisations — delivering adaptive training, regulatory update management, and operational knowledge management that keeps mortgage teams current, competent, and compliant.").overview("Mortgage is one of the most knowledge-intensive regulated industries, with constant regulatory change, complex product guidelines, and high training requirements. First Learning Intelligence delivers the adaptive, personalised learning infrastructure that ensures your mortgage professionals have the knowledge they need, when they need it.").imageUrl("/images/mortgage-services/first-learning-intelligence.jpg").bannerUrl("/images/mortgage-services/first-learning-intelligence-banner.jpg").displayOrder(25).build(),
                MortgageService.builder().slug("mortgage-analytics").title("Mortgage Analytics").category("Mortgage Analytics").description("Comprehensive mortgage analytics services — covering origination performance, portfolio risk, servicing operations, and market intelligence — delivered through advanced data platforms and visualisation tools that drive evidence-based decision making.").overview("Data is the most underutilised asset in most mortgage organisations. Our mortgage analytics practice helps lenders, servicers, and investors unlock the full value of their data through robust data engineering, sophisticated analytical models, and intuitive reporting that puts actionable insight in the hands of decision makers.").imageUrl("/images/mortgage-services/mortgage-analytics.jpg").bannerUrl("/images/mortgage-services/mortgage-analytics-banner.jpg").displayOrder(26).build()
        ));
    }

    // ─── Company Settings ─────────────────────────────────────────────────────

    private void seedCompanySettings() {
        if (companySettingsRepository.count() > 0) return;
        companySettingsRepository.save(
                CompanySettings.builder()
                        .companyName("Visionary Inspire")
                        .phone("+91 82967 66781")
                        .email("info@visionaryinspire.com")
                        .headquartersAddress("18, Sy. No. 93/9, Noval MSR Park, Varthur Main Road, Munnekolala, Marathahalli, Bangalore \u2013 560037")
                        .businessDays("Monday \u2013 Friday")
                        .businessHours("9:00 AM \u2013 6:00 PM IST")
                        .responseTime("Within 1 business day")
                        .tagline("Transforming businesses through people, process & technology")
                        .build()
        );
    }

    // ─── Office Locations ─────────────────────────────────────────────────────

    private void seedOfficeLocations() {
        // Intentionally empty. Office locations are managed directly in the database.
    }

    // ─── CTA Sections ─────────────────────────────────────────────────────────

    // --- Hero Sections ---

    private void seedHeroSections() {
        if (heroSectionRepository.count() > 0) return;
        heroSectionRepository.saveAll(List.of(

                HeroSection.builder().pageKey("home").eyebrow("Transforming Global Enterprises").title("People, Process & Technology Working as One").subtitle("Visionary Inspire partners with leading enterprises to deliver customer experience, intelligent automation, and data-driven transformation that creates lasting competitive advantage.").primaryButtonText("Get in Touch").primaryButtonUrl("/contact").secondaryButtonText("Explore Services").secondaryButtonUrl("/services").displayOrder(1).build(),

                HeroSection.builder().pageKey("about").eyebrow("About Us").title("{years}+ Years of Transforming Global Enterprises").subtitle("Visionary Inspire is a business process and technology services company, partnering with leading enterprises to deliver outcomes that matter.").displayOrder(2).build(),

                HeroSection.builder().pageKey("careers").eyebrow("Careers").title("Build a Career That Makes a Global Impact").subtitle("Join a team of growing professionals working at the intersection of people, process, and technology to transform enterprises.").displayOrder(3).build(),

                HeroSection.builder().pageKey("contact").eyebrow("Contact Us").title("Let\u2019s Start a Conversation").subtitle("Whether you are exploring a new partnership, looking for a specific solution, or simply want to learn more \u2014 our team is ready to help.").displayOrder(4).build(),

                HeroSection.builder().pageKey("industries").eyebrow("Industries We Serve").title("Deep Expertise Across the Sectors That Matter Most").subtitle("We bring specialised knowledge, proven delivery frameworks, and dedicated domain teams to the industries driving global economic growth.").displayOrder(5).build(),

                HeroSection.builder().pageKey("services").eyebrow("Our Services").title("End-to-End Solutions for Enterprise Transformation").subtitle("From front-office customer experience to back-office automation and analytics, we deliver services that create lasting value across your entire operation.").displayOrder(6).build(),

                HeroSection.builder().pageKey("insights").eyebrow("Insights & Resources").title("Ideas, Research & Perspectives That Move Business Forward").subtitle("Explore our latest thinking on customer experience, intelligent automation, data analytics, and the trends shaping global enterprise operations.").displayOrder(7).build(),

                HeroSection.builder().pageKey("mortgage_services").eyebrow("Mortgage Services").title("Specialised Financial Services for the Mortgage Industry").subtitle("End-to-end mortgage operations, technology, and analytics services \u2014 covering origination, servicing, title, fraud, compliance, and digital transformation across the entire mortgage lifecycle.").displayOrder(8).build(),

                HeroSection.builder().pageKey("industry_detail").primaryButtonText("Speak with an Expert").primaryButtonUrl("/contact").secondaryButtonText("Request Consultation").secondaryButtonUrl("/contact?type=consultation").displayOrder(9).build(),

                HeroSection.builder().pageKey("service_detail").primaryButtonText("Speak with an Expert").primaryButtonUrl("/contact").secondaryButtonText("Request Consultation").secondaryButtonUrl("/contact?type=consultation").displayOrder(10).build(),

                HeroSection.builder().pageKey("mortgage_service_detail").primaryButtonText("Speak with an Expert").primaryButtonUrl("/contact").secondaryButtonText("Request Consultation").secondaryButtonUrl("/contact?type=consultation").displayOrder(11).build()
        ));
    }

    private void seedCtaSections() {
        if (ctaSectionRepository.count() > 0) return;
        ctaSectionRepository.saveAll(List.of(

                // 1. Home page main CTA
                CtaSection.builder()
                        .pageKey("home_main")
                        .title("Ready to Transform Your Business?")
                        .description("Talk to our experts and discover how Visionary Inspire can help you achieve your goals.")
                        .primaryButtonText("Get in Touch")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Explore Services")
                        .secondaryButtonUrl("/services")
                        .displayOrder(1).build(),

                // 2. About page CTA
                CtaSection.builder()
                        .pageKey("about")
                        .title("Partner With a Team That\u2019s Invested in Your Success")
                        .description("Let\u2019s talk about how Visionary Inspire can help you achieve your transformation goals.")
                        .primaryButtonText("Get in Touch")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Explore Services")
                        .secondaryButtonUrl("/services")
                        .displayOrder(2).build(),

                // 3. Careers page CTA
                CtaSection.builder()
                        .pageKey("careers")
                        .title("Don\u2019t See the Right Role?")
                        .description("Send us your CV and we\u2019ll reach out when a suitable position opens up.")
                        .primaryButtonText("Get in Touch")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("About Us")
                        .secondaryButtonUrl("/about")
                        .displayOrder(3).build(),

                // 4. Industries page CTA
                CtaSection.builder()
                        .pageKey("industries")
                        .title("Ready to Explore What We Can Do in Your Industry?")
                        .description("Our industry specialists are ready to discuss your specific challenges and opportunities.")
                        .primaryButtonText("Get in Touch")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("View Our Services")
                        .secondaryButtonUrl("/services")
                        .displayOrder(4).build(),

                // 5. Services page CTA
                CtaSection.builder()
                        .pageKey("services")
                        .title("Not Sure Which Service Fits Your Needs?")
                        .description("Our experts will help you identify the right solution for your business challenges.")
                        .primaryButtonText("Speak to an Expert")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Explore Industries")
                        .secondaryButtonUrl("/industries")
                        .displayOrder(5).build(),

                // 6. Insights listing page CTA
                CtaSection.builder()
                        .pageKey("insights")
                        .title("Want the Latest Insights Delivered to Your Inbox?")
                        .description("Subscribe to our newsletter and stay ahead of the trends shaping your industry.")
                        .primaryButtonText("Get in Touch")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("View All Services")
                        .secondaryButtonUrl("/services")
                        .displayOrder(6).build(),

                // 7. Insight detail page CTA
                CtaSection.builder()
                        .pageKey("insight_detail")
                        .title("Want More Insights Like This?")
                        .description("Subscribe to our newsletter and get the latest research delivered to your inbox.")
                        .primaryButtonText("Get in Touch")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Browse All Insights")
                        .secondaryButtonUrl("/insights")
                        .displayOrder(7).build(),

                // 8. Mortgage services listing page CTA
                CtaSection.builder()
                        .pageKey("mortgage_services")
                        .title("Looking for a Mortgage Operations Partner?")
                        .description("Our mortgage specialists are ready to discuss your specific challenges and operational requirements.")
                        .primaryButtonText("Speak to an Expert")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Explore Digital Operations")
                        .secondaryButtonUrl("/services")
                        .displayOrder(8).build(),

                // 9. Contact page CTA
                CtaSection.builder()
                        .pageKey("contact")
                        .title("Ready to Transform Your Business?")
                        .description("Our experts are standing by to help you design the right solution for your challenges.")
                        .primaryButtonText("Send a Message")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Explore Services")
                        .secondaryButtonUrl("/services")
                        .displayOrder(9).build(),

                // 10. Industry detail page CTA (dynamic — {name} replaced at render time)
                CtaSection.builder()
                        .pageKey("industry_detail")
                        .title("Ready to Transform Your {name} Operations?")
                        .description("Our industry specialists are ready to design a solution built around your sector\u2019s specific regulatory environment, competitive pressures, and operational requirements.")
                        .primaryButtonText("Speak with an Expert")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Request Consultation")
                        .secondaryButtonUrl("/contact?type=consultation")
                        .displayOrder(10).build(),

                // 11. Service detail page CTA (dynamic — {name} replaced at render time)
                CtaSection.builder()
                        .pageKey("service_detail")
                        .title("Ready to Transform Your {name} Operations?")
                        .description("Our specialists are ready to design a solution tailored to your exact business requirements and commercial objectives.")
                        .primaryButtonText("Speak with an Expert")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Request Consultation")
                        .secondaryButtonUrl("/contact?type=consultation")
                        .displayOrder(11).build(),

                // 12. Mortgage service detail page CTA (dynamic — {name} replaced at render time)
                CtaSection.builder()
                        .pageKey("mortgage_service_detail")
                        .title("Ready to Transform Your {name} Operations?")
                        .description("Our specialists are ready to design a solution tailored to your exact business requirements and commercial objectives.")
                        .primaryButtonText("Speak with an Expert")
                        .primaryButtonUrl("/contact")
                        .secondaryButtonText("Request Consultation")
                        .secondaryButtonUrl("/contact?type=consultation")
                        .displayOrder(12).build()
        ));
    }

    // ─── Navigation Links ─────────────────────────────────────────────────────

    private void seedNavigationLinks() {
        if (navigationLinkRepository.count() > 0) return;
        navigationLinkRepository.saveAll(List.of(

                // ── HEADER (desktop nav) ─────────────────────────────────────
                NavigationLink.builder().section("HEADER").label("About").url("/about").displayOrder(1).build(),
                NavigationLink.builder().section("HEADER").label("Services").url("").displayOrder(2).build(),
                NavigationLink.builder().section("HEADER").label("Digital Operations").url("/services").displayOrder(3).build(),
                NavigationLink.builder().section("HEADER").label("Industries").url("/industries").displayOrder(4).build(),
                NavigationLink.builder().section("HEADER").label("Insights").url("/insights").displayOrder(5).build(),
                NavigationLink.builder().section("HEADER").label("Careers").url("/careers").displayOrder(6).build(),
                NavigationLink.builder().section("HEADER").label("Get in Touch").url("/contact").displayOrder(7).build(),

                // ── INDUSTRIES_DROPDOWN ──────────────────────────────────────
                NavigationLink.builder().section("INDUSTRIES_DROPDOWN").label("Banking & Financial Services").url("/industries/banking-financial-services").displayOrder(1).build(),
                NavigationLink.builder().section("INDUSTRIES_DROPDOWN").label("Healthcare").url("/industries/healthcare").displayOrder(2).build(),
                NavigationLink.builder().section("INDUSTRIES_DROPDOWN").label("Telecommunications").url("/industries/telecommunications").displayOrder(3).build(),
                NavigationLink.builder().section("INDUSTRIES_DROPDOWN").label("Retail & E-commerce").url("/industries/retail-ecommerce").displayOrder(4).build(),
                NavigationLink.builder().section("INDUSTRIES_DROPDOWN").label("Media & Entertainment").url("/industries/media-entertainment").displayOrder(5).build(),

                // ── FOOTER_COMPANY ───────────────────────────────────────────
                NavigationLink.builder().section("FOOTER_COMPANY").label("About Us").url("/about").displayOrder(1).build(),
                NavigationLink.builder().section("FOOTER_COMPANY").label("Leadership").url("/about").displayOrder(2).build(),
                NavigationLink.builder().section("FOOTER_COMPANY").label("Insights").url("/insights").displayOrder(3).build(),
                NavigationLink.builder().section("FOOTER_COMPANY").label("Careers").url("/careers").displayOrder(4).build(),
                NavigationLink.builder().section("FOOTER_COMPANY").label("Contact Us").url("/contact").displayOrder(5).build(),

                // ── FOOTER_SERVICES ──────────────────────────────────────────
                NavigationLink.builder().section("FOOTER_SERVICES").label("Application Services").url("/services/application-services").displayOrder(1).build(),
                NavigationLink.builder().section("FOOTER_SERVICES").label("AI").url("/services/ai").displayOrder(2).build(),
                NavigationLink.builder().section("FOOTER_SERVICES").label("Enterprise Automation").url("/services/enterprise-automation").displayOrder(3).build(),
                NavigationLink.builder().section("FOOTER_SERVICES").label("Cyber Security").url("/services/cyber-security").displayOrder(4).build(),
                NavigationLink.builder().section("FOOTER_SERVICES").label("Next-Gen Data").url("/services/next-gen-data").displayOrder(5).build(),

                // ── FOOTER_INDUSTRIES ────────────────────────────────────────
                NavigationLink.builder().section("FOOTER_INDUSTRIES").label("Banking & Financial Services").url("/industries/banking-financial-services").displayOrder(1).build(),
                NavigationLink.builder().section("FOOTER_INDUSTRIES").label("Healthcare").url("/industries/healthcare").displayOrder(2).build(),
                NavigationLink.builder().section("FOOTER_INDUSTRIES").label("Telecommunications").url("/industries/telecommunications").displayOrder(3).build(),
                NavigationLink.builder().section("FOOTER_INDUSTRIES").label("Retail & E-commerce").url("/industries/retail-ecommerce").displayOrder(4).build(),
                NavigationLink.builder().section("FOOTER_INDUSTRIES").label("Media & Entertainment").url("/industries/media-entertainment").displayOrder(5).build(),

                // ── FOOTER_INSIGHTS ──────────────────────────────────────────
                NavigationLink.builder().section("FOOTER_INSIGHTS").label("Blog & Articles").url("/insights").displayOrder(1).build(),
                NavigationLink.builder().section("FOOTER_INSIGHTS").label("Case Studies").url("/insights").displayOrder(2).build(),
                NavigationLink.builder().section("FOOTER_INSIGHTS").label("White Papers").url("/insights").displayOrder(3).build(),
                NavigationLink.builder().section("FOOTER_INSIGHTS").label("Industry Reports").url("/insights").displayOrder(4).build(),
                NavigationLink.builder().section("FOOTER_INSIGHTS").label("Webinars & Events").url("/insights").displayOrder(5).build(),

                // ── SOCIAL_LINKS ─────────────────────────────────────────────
                NavigationLink.builder().section("SOCIAL_LINKS").label("LinkedIn").url("#").displayOrder(1).build(),
                NavigationLink.builder().section("SOCIAL_LINKS").label("Twitter").url("#").displayOrder(2).build(),
                NavigationLink.builder().section("SOCIAL_LINKS").label("Facebook").url("#").displayOrder(3).build(),
                NavigationLink.builder().section("SOCIAL_LINKS").label("YouTube").url("#").displayOrder(4).build(),

                // ── FOOTER_LEGAL ─────────────────────────────────────────────
                NavigationLink.builder().section("FOOTER_LEGAL").label("Privacy Policy").url("#").displayOrder(1).build(),
                NavigationLink.builder().section("FOOTER_LEGAL").label("Terms of Use").url("#").displayOrder(2).build(),
                NavigationLink.builder().section("FOOTER_LEGAL").label("Cookie Policy").url("#").displayOrder(3).build(),
                NavigationLink.builder().section("FOOTER_LEGAL").label("Accessibility").url("#").displayOrder(4).build(),
                NavigationLink.builder().section("FOOTER_LEGAL").label("Sitemap").url("#").displayOrder(5).build()
        ));
    }
}
