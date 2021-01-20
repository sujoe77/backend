package com.zs.rule.entity.product;

import com.zs.rule.entity.org.Agent;

public class Video extends PhysicalProduct {
    private final String videoName;

    public Video(Agent agent, String videoName) {
        super(agent);
        this.videoName = videoName;
    }

    @Override
    public String getName() {
        return videoName;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoName='" + videoName + '\'' +
                '}';
    }
}
