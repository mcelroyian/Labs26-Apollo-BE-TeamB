package com.lambdaschool.apollo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@ApiModel(value = "TopicUser", description = "Relationship between topics and users(members)")
@Entity
@Table(name = "topicusers", uniqueConstraints = {@UniqueConstraint(columnNames = {"topicid", "userid"})})
public class TopicUsers extends Auditable implements Serializable {

    /**
     * 1/2 of the primary key (long) for topicusers.
     * Also is a foreign key into the topics table
     */
    @ApiModelProperty(name = "Topic id", value = "Topic id")
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topicid")
    @JsonIgnoreProperties(value = {"users", ""}, allowSetters = true)
    private Topic topic;

    /**
     * 1/2 of the primary key (long) for topicusers.
     * Also is a foreign key into the users table
     */
    @ApiModelProperty(name = "User id (member)", value = "User id")
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = {"topics", "ownedtopics","roles","primaryemail"}, allowSetters = true)
    private User user;

    /**
     * Default constructor used primarily by the JPA.
     */
    public TopicUsers() {
    }

    /**
     * Given the params, create a new topic user combination object
     *
     * @param topic The topic object of this relationship
     * @param user  The user object of this relationship
     */
    public TopicUsers(Topic topic, User user) {
        this.topic = topic;
        this.user = user;
    }

    /**
     * Getter for topic
     *
     * @return the complete topic object associated with this topic user combination
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Setter for topic
     *
     * @param topic change role object associated with this topic user combination to this one.
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * Getter for user
     *
     * @return the complete user object associated with topic user combination
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for user
     *
     * @param user change the user object associated with this topic user combination to this one.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicUsers that = (TopicUsers) o;
        return getTopic().equals(that.getTopic()) &&
                getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTopic(), getUser());
    }

    @Override
    public String toString() {
        return "TopicUsers{" +
                "topic=" + topic +
                ", user=" + user +
                '}';
    }
}
