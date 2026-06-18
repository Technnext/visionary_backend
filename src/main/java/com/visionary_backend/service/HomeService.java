package com.visionary_backend.service;

import com.visionary_backend.entity.Award;
import com.visionary_backend.entity.Client;
import com.visionary_backend.entity.Stat;
import com.visionary_backend.entity.Testimonial;
import com.visionary_backend.repository.AwardRepository;
import com.visionary_backend.repository.ClientRepository;
import com.visionary_backend.repository.StatRepository;
import com.visionary_backend.repository.TestimonialRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class HomeService {

    private final StatRepository statRepository;
    private final ClientRepository clientRepository;
    private final TestimonialRepository testimonialRepository;
    private final AwardRepository awardRepository;

    public HomeService(StatRepository statRepository,
                       ClientRepository clientRepository,
                       TestimonialRepository testimonialRepository,
                       AwardRepository awardRepository) {
        this.statRepository = statRepository;
        this.clientRepository = clientRepository;
        this.testimonialRepository = testimonialRepository;
        this.awardRepository = awardRepository;
    }

    public List<Stat> getStats() {
        return statRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Stat::getDisplayOrder))
                .toList();
    }

    public List<Stat> getStatsByContext(String context) {
        return statRepository.findByContextOrderByDisplayOrderAsc(context);
    }

    public List<Client> getClients() {
        return clientRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Client::getDisplayOrder))
                .toList();
    }

    public List<Testimonial> getTestimonials() {
        return testimonialRepository.findAll();
    }

    public List<Award> getAwards() {
        return awardRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Award::getDisplayOrder))
                .toList();
    }
}
