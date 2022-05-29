package com.example.superchat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Column(nullable = false)
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        if (!super.equals(o)) return false;
        Message message = (Message) o;
        return Objects.equals(getContact(), message.getContact()) && Objects.equals(getChannel(), message.getChannel()) && Objects.equals(getText(), message.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getContact(), getChannel(), getText());
    }
}
