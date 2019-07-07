package com.sda.TicketSystem.service;

import com.sda.TicketSystem.model.Subscription;
import com.sda.TicketSystem.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription create(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public Subscription getByCode(String code){
        Optional<Subscription> subscription = subscriptionRepository.findByCode(code);
        return subscription.orElse(null);
    }

    public Subscription update(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long id){
        subscriptionRepository.deleteById(id);
    }
}
