package com.example.superchat.entity;

import com.example.superchat.entity.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "channels")
@NoArgsConstructor
@AllArgsConstructor
public class Channel extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChannelType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        if (!super.equals(o)) return false;
        Channel channel = (Channel) o;
        return Objects.equals(getContact(), channel.getContact()) && getType() == channel.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getContact(), getType());
    }
}
