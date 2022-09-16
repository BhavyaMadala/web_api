package com.reward.service;

import com.reward.dto.UserRewards;



public interface RewardService {
    public UserRewards getPoints(Long customerId);

}
