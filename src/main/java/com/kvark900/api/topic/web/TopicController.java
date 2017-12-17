package com.kvark900.api.topic.web;

import com.kvark900.api.topic.domain.Topic;
import com.kvark900.api.topic.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @RequestMapping ("/topics")
    public List<Topic> getAllTopics(){
        return topicService.findAll();
    }

    @RequestMapping ("/topics/{id}")
    public Topic getTopic(@PathVariable String id){
        return topicService.findById(id);
    }

    @RequestMapping (method = RequestMethod.POST, value = "/topics")
    public void addTopic(@ModelAttribute("topic") Topic topic){
        topicService.save(topic);
    }

    @RequestMapping (method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id){
        topicService.delete(id);
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/topics/{id}")
    public void updateTopic(@PathVariable String id, @RequestBody Topic topic){
        topicService.update(topic);
    }

}
