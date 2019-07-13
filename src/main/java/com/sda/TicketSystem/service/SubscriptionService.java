package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Subscription;
import com.sda.TicketSystem.model.SubscriptionDTO;
import com.sda.TicketSystem.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription create(Subscription subscription) {
        return subscriptionRepository.save(subscription);
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
