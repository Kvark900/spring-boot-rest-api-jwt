package com.kvark900.api.topic.service;

import com.kvark900.api.topic.domain.Topic;
import com.kvark900.api.topic.domain.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    public Topic findById(String id){
        return topicRepository.findOne(id);
    }

    public void save (Topic topic){
        topicRepository.save(topic);
    }

    public void delete(String id){
        topicRepository.delete(id);
    }

    public void update (Topic topic){
        topicRepository.save(topic);
    }
}