package com.example.superchat.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "bitcoin_rate")
@AllArgsConstructor
@NoArgsConstructor
public class BitcoinRate extends BaseEntity {

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String rate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BitcoinRate)) return false;
        if (!super.equals(o)) return false;
        BitcoinRate that = (BitcoinRate) o;
        return Objects.equals(getCurrency(), that.getCurrency()) && Objects.equals(getRate(), that.getRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCurrency(), getRate());
    }
}
