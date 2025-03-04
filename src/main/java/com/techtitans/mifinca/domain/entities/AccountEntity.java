package com.techtitans.mifinca.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.techtitans.mifinca.domain.dtos.RegisterAccountDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE accounts SET status = 1 WHERE id=?")
@SQLRestriction("status = 0")
@Table(name = "accounts")   //we add the unqiue constraint at db layer just in case, howeber I'll keep it in service also
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String names;
    private String lastNames;
    @Column(unique = true)
    private String email;
    private String password;
    private String number;
    private boolean active;
    private byte status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AccountEntity fromRegisterDTO(RegisterAccountDTO dto){
        var acc = new AccountEntity();
        acc.setNames(dto.names());
        acc.setLastNames(dto.lastNames());
        acc.setEmail(dto.email());
        acc.setPassword(dto.password());
        acc.setNumber(dto.number());
        return acc;
    }
}
