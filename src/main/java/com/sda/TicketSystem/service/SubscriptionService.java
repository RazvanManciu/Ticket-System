package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Subscription;
import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<SubscriptionDTO> getAll() {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<>();
        for (Subscription subscription : subscriptionRepository.findAll()) {
            SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
            subscriptionDTO.setCode(subscription.getCode());
            subscriptionDTO.setStartDate(subscription.getStartDate().toString());
            subscriptionDTO.setEndDate(subscription.getEndDate().toString());
            subscriptionDTO.setId(subscription.getId());
            subscriptionDTOS.add(subscriptionDTO);
        }
        return subscriptionDTOS;
    }

    public SubscriptionDTO create(SubscriptionDTO subscriptionDTO) {

        Subscription subscription = new Subscription();
        subscription.setStartDate(subscriptionDTO.getStartDate());
        subscription.setEndDate(subscriptionDTO.getEndDate());

        String generatedSubscriptionCode = "s" + Instant.now().toEpochMilli();

        subscription.setCode(generatedSubscriptionCode);

        subscriptionRepository.save(subscription);

        subscriptionDTO.setCode(subscription.getCode());

        return subscriptionDTO;
    }

    public SubscriptionDTO getByCode(String code) {
        Optional<Subscription> subscription = subscriptionRepository.findByCode(code);
        if (subscription.isPresent()) {
            SubscriptionDTO subscriptionDTO =
                    new SubscriptionDTO(
                            subscription.get().getStartDate().toString(),
                            subscription.get().getEndDate().toString(),
                            subscription.get().getCode()
                    );
            return subscriptionDTO;
        }
        return null;
    }

    public Subscription update(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
