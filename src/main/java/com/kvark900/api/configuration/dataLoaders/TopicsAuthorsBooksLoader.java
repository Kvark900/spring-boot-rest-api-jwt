package com.kvark900.api.configuration.dataLoaders;

import com.kvark900.api.model.Author;
import com.kvark900.api.model.Book;
import com.kvark900.api.model.Topic;
import com.kvark900.api.service.AuthorService;
import com.kvark900.api.service.BookService;
import com.kvark900.api.service.TopicService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Keno&Kemo on 03.03.2018..
 */
@Component
public class TopicsAuthorsBooksLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private BookService bookService;
    private AuthorService authorService;
    private TopicService topicService;


    public TopicsAuthorsBooksLoader(BookService bookService, AuthorService authorService, TopicService topicService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.topicService = topicService;
    }

    // API
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) return;

        //region Authors
        //===============================================================================
        Author gayleLaakmann = createAuthorIfNotFound("Gayle", "Laakmann");
        Author richardFeynman = createAuthorIfNotFound("Richard", "Feynman");
        Author kathySierra = createAuthorIfNotFound("Kathy", "Sierra");
        Author bertBates = createAuthorIfNotFound("Bert", "Bates");
        Author thomasCormen = createAuthorIfNotFound("Thomas",  "Cormen");
        Author charlesLeiserson = createAuthorIfNotFound("Charles","Leiserson");
        Author ronaldRivest = createAuthorIfNotFound("Ronald", "Rivest");
        //===============================================================================
        //endregion


        //region Topics
        //===============================================================================
        Topic computerProgramming = createTopicIfNotFound("Computer programming", "Computer programming books");
        Topic computersTechnology = createTopicIfNotFound("Computers & Technology", " Books related to Computers & Technology");
        Topic carerGuide = createTopicIfNotFound("Carer Guide", "Carer Guide Books");
        Topic java = createTopicIfNotFound("Java", "Java Programming Books");
        Topic science = createTopicIfNotFound("Science", "Popular science books");
        Topic algorithmsDataStructures = createTopicIfNotFound("Data Structures and Algorithms", "Data Structures and Algorithms  books");
        //===============================================================================
        //endregion


        //region Books

            //region Introduction to Algorithms
            //===============================================================================
            Set<Author> authorsIntroductionToAlgorithms = new HashSet<>();
            authorsIntroductionToAlgorithms.add(thomasCormen);
            authorsIntroductionToAlgorithms.add(charlesLeiserson);
            authorsIntroductionToAlgorithms.add(ronaldRivest);

            Set<Topic> topicsIntroductionToAlgorithms = new HashSet<>();
            topicsIntroductionToAlgorithms.add(algorithmsDataStructures);
            topicsIntroductionToAlgorithms.add(computerProgramming);
            topicsIntroductionToAlgorithms.add(computersTechnology);

            createBookIfNotFound(
                    "Introduction to Algorithms", authorsIntroductionToAlgorithms, "2009",
                    "Introduction to Algorithms, the 'bible' of the field, is a comprehensive textbook " +
                            "covering the full spectrum of modern algorithms: from the fastest algorithms and data" +
                            " structures to polynomial-time algorithms for seemingly intractable problems, from " +
                            "classical algorithms in graph theory to special" +
                            " algorithms for string matching, computational geometry, and number theory.",
                    topicsIntroductionToAlgorithms, new BigDecimal(100.17));
            //===============================================================================
            //endregion


            //region "Cracking the Coding Interview"
            //===============================================================================
            Set<Author> authorsCrackingTheCodingInterview = new HashSet<>();
            authorsCrackingTheCodingInterview.add(gayleLaakmann);

            Set<Topic> topicsCrackingTheCodingInterview = new HashSet<>();
            topicsCrackingTheCodingInterview.add(computerProgramming);
            topicsCrackingTheCodingInterview.add(carerGuide);
            topicsCrackingTheCodingInterview.add(computersTechnology);

            createBookIfNotFound(
                    "Cracking the Coding Interview: 150 Programming Questions and Solutions 4th Edtion",
                    authorsCrackingTheCodingInterview, "2010", "Cracking the Coding Interview " +
                    "gives you the interview preparation you need to get the top software developer jobs.",
                    topicsCrackingTheCodingInterview, new BigDecimal(100));

            //===============================================================================
            //endregion


            //region "Head First Java"
            //===============================================================================
            Set<Author> authorsHeadFirstJava = new HashSet<>();
            authorsHeadFirstJava.add(kathySierra);
            authorsHeadFirstJava.add(bertBates);

            Set<Topic> topicsHeadFirstJava = new HashSet<>();
            topicsHeadFirstJava.add(computerProgramming);
            topicsHeadFirstJava.add(computersTechnology);
            topicsHeadFirstJava.add(java);

            createBookIfNotFound(
                    "Head First Java", authorsHeadFirstJava, "2005",
                    "By exploiting how your brain works, Head First Java compresses the time it takes to " +
                    "learn and retain--complex information. Its unique approach not only shows you what you " +
                    "need to know about Java syntax, it teaches you to think like a Java programmer.", topicsHeadFirstJava, new BigDecimal(60));
            //===============================================================================
            //endregion


            //region "Surely You're Joking, Mr. Feynman!"
            //===============================================================================
            Set<Author> authorsSurelyYoureJokingMrFeynman = new HashSet<>();
            authorsSurelyYoureJokingMrFeynman.add(richardFeynman);

            Set<Topic> topicsSurelyYoureJokingMrFeynman = new HashSet<>();
            topicsSurelyYoureJokingMrFeynman.add(science);

            createBookIfNotFound(
                    "Surely You're Joking, Mr. Feynman!: Adventures of a Curious Character", authorsSurelyYoureJokingMrFeynman,
                    "1985", "This is an edited collection of reminiscences by the Nobel Prize-winning" +
                    " physicist Richard Feynman. The book, released in 1985, covers a variety of instances in Feynman's life.",
                    topicsSurelyYoureJokingMrFeynman, new BigDecimal(100));
            //===============================================================================
            //endregion

        //===============================================================================
        //endregion


        alreadySetup = true;
    }

    @Transactional
    Author createAuthorIfNotFound(String name, String surname) {
        Author author = authorService.findByNameAndSurname(name, surname);
        if (author == null) {
            author = new Author(name, surname);
            authorService.save(author);
        }
        return author;
    }

    @Transactional
    Topic createTopicIfNotFound(String name, String description) {
        Topic topic = topicService.findByName(name);
        if (topic == null) {
            topic = new Topic(name, description);
            topicService.save(topic);
        }
        return topic;
    }

    @Transactional
    void createBookIfNotFound(String title, Set<Author> authors,
                              String yearOfPublication, String description,
                              Set<Topic> topics, BigDecimal price) {
        Book book = bookService.findByTitle(title);
        if (book == null) {
            book = new Book();
            book.setTitle(title);
            book.setAuthors(authors);
            book.setYearOfPublication(yearOfPublication);
            book.setDescription(description);
            book.setTopics(topics);
            book.setPrice(price);

            bookService.save(book);
        }
    }

}
